package bg.duosoft.bpo.fileservice.service.impl;


import bg.duosoft.bpo.common.service.validator.BadRequestValidator;
import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.bpo.fileservice.dto.FileConstants;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.dto.FileStreamResult;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.fileservice.utils.MinioIdGenerator;
import bg.duosoft.bpo.fileservice.validator.FileStoreEntryValidator;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Supplier;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 11:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioFileStoreServiceImpl implements FileStoreService {
    private final FileStoreEntryValidator fileStoreEntryValidator;
    private final MinioClient minioClient;

    @Override
    public FileStoreEntryBaseDTO saveNewFileWithCustomFileId(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry) {
        BadRequestValidator.validateRequest(fileStoreEntryValidator, fileStoreEntry, fileGroup, pointer);
        return saveNewFile(fileGroup, pointer, fileStoreEntry, fileStoreEntry::getFileId);
    }

    @Override
    public FileStoreEntryBaseDTO saveNewFile(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry) {
        return saveNewFile(fileGroup, pointer, fileStoreEntry, () -> {
            String generatedFileId;
            try {
                do {
                    generatedFileId = MinioIdGenerator.generateFileId();
                } while (objectExists(fileStoreEntry.getBucket(), buildFullPath(fileStoreEntry.getRelativePath(), generatedFileId)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return generatedFileId;
        });
    }

    private FileStoreEntryBaseDTO saveNewFile(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry, Supplier<String> fileIdGetter) {
        BadRequestValidator.validateRequest(fileStoreEntryValidator, fileStoreEntry, fileGroup, pointer);
        try {
            String generatedFileId = fileIdGetter.get();
            return saveFileInStore(fileStoreEntry, generatedFileId);
        } catch (Exception e) {
            log.error("Error saving new file in file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileStoreEntryBaseDTO getFileStoreEntryDetailsAndContent(String bucket, String fullPath) {
        try (GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(fullPath).build())) {

            StatObjectResponse statObject = minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucket).object(fullPath).build()
            );

            FileStoreEntryBaseDTO entry = new FileStoreEntryBaseDTO();
            entry.setContent(response.readAllBytes());

            fillEntryDetails(statObject, bucket, fullPath, entry);
            return entry;
        } catch (Exception e) {
            log.error("Error getting file details and content from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileStoreEntryBaseDTO getFileStoreEntryDetailsOnly(String bucket, String fullPath) {
        try {
            StatObjectResponse statObject = minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucket).object(fullPath).build()
            );

            FileStoreEntryBaseDTO entry = new FileStoreEntryBaseDTO();
            fillEntryDetails(statObject, bucket, fullPath, entry);
            return entry;
        } catch (Exception e) {
            log.error("Error getting file details from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileStoreEntryBaseDTO moveFile(String rootDirectoryNew, String relativePathNew, FileStoreEntryBaseDTO entryToMove, Boolean removeOriginal) {
        try {
            FileStoreEntryBaseDTO oldEntry = getFileStoreEntryDetailsAndContent(entryToMove.getBucket(), getFullPath(entryToMove));
            FileStoreEntryBaseDTO newFileEntry = (FileStoreEntryBaseDTO) oldEntry.clone();
            while (objectExists(rootDirectoryNew, getFullPath(newFileEntry))) {
                String generatedFileId = MinioIdGenerator.generateFileId();
                newFileEntry.setFileId(generatedFileId);
            }
            newFileEntry.setBucket(rootDirectoryNew);
            newFileEntry.setRelativePath(relativePathNew);

            FileStoreEntryBaseDTO result = saveFileInStore(newFileEntry, newFileEntry.getFileId());
            if (removeOriginal != null && removeOriginal) {
                removeFile(oldEntry.getBucket(), oldEntry.getFullPath());
            }
            return result;
        } catch (Exception e) {
            log.error("Error moving file in file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void copyFile(String newBucket, String relativePathNew, String oldBucket, String relativePathOld, String fileId) {
        String objectOld = buildFullPath(relativePathOld, fileId);
        String objectNew = buildFullPath(relativePathNew, fileId);
        try {
            if (objectExists(oldBucket, objectOld)) {
                CopyObjectArgs copyObjectArgs = CopyObjectArgs.builder().bucket(newBucket).object(objectNew).source(CopySource.builder().bucket(oldBucket).object(objectOld).build()).build();
                minioClient.copyObject(copyObjectArgs);
            } else {
                throw new ResourceNotFoundException("Copy source not found! Copy source file path: " + oldBucket + FileConstants.PATH_SEPARATOR + relativePathOld + FileConstants.PATH_SEPARATOR + fileId);
            }
        } catch (Exception e) {
            log.error("Error with file copy in file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeFile(String rootDirectory, String fullPath) {
        try {
            if (objectExists(rootDirectory, fullPath)) {
                minioClient.removeObject(
                        RemoveObjectArgs.builder().bucket(rootDirectory).object(fullPath).build()
                );
            }
        } catch (RuntimeException | ServerException | InsufficientDataException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException | ErrorResponseException e) {
            log.error("Error removing file from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> removeFilesFromDirectory(String rootDirectory, String filesDirectory) {
        List<String> removeErrors = new ArrayList<>();
        try {
            if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(rootDirectory).build())) {
                Iterable<Result<Item>> listObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(rootDirectory).prefix(filesDirectory).recursive(true).build());
                List<DeleteObject> deletes = new ArrayList<>();
                listObjects.forEach(obj -> {
                    try {
                        deletes.add(new DeleteObject(obj.get().objectName()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                Iterable<Result<DeleteError>> results = minioClient.removeObjects(
                        RemoveObjectsArgs.builder().bucket(rootDirectory).objects(deletes).build()
                );
                results.forEach(error -> {
                    try {
                        removeErrors.add("Error in deleting object " + error.get().objectName() + "; " + error.get().message());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                removeErrors.add("Bucket does not exist "+rootDirectory);
            }
            return removeErrors;
        } catch (RuntimeException | ServerException | InsufficientDataException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException | ErrorResponseException e
        ) {
            log.error("Error removing file from file store", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean fileExists(String bucket, String fullPath) {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                return false;
            } else {
                StatObjectResponse statObject = minioClient.statObject(
                        StatObjectArgs.builder().bucket(bucket).object(fullPath).build()
                );
                return true;
            }
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            return false;
        }
    }

    @Override
    public void emptyBucket(String bucket) {
        try {
            Iterable<Result<Item>> objectList = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucket).recursive(true).build()); // recursive = true for getting objects in subdirectories

            for (Result<Item> itemResult : objectList) {
                Item item = itemResult.get();
                String objectName = item.objectName();

                log.info("Attempting to delete object: " + objectName);
                minioClient.removeObject(
                        RemoveObjectArgs.builder().bucket(bucket).object(objectName).build()
                );
                log.info("Deleted object: " + objectName);
            }
            log.info("Bucket '{}' successfully emptied.", bucket);
        } catch (Exception e) {
            log.error("Error emptying bucket: " + bucket, e);
            throw new RuntimeException("Failed to empty bucket: " + bucket, e);
        }
    }

    @Override
    public FileStreamResult readFileStream(String bucket, String fullPath) {
        try {
            // First get metadata
            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucket)
                            .object(fullPath)
                            .build());

            // Then get the stream
            GetObjectResponse response = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(fullPath)
                            .build());

            // Extract filename from metadata or fallback to object key
            String filename = Optional.ofNullable(stat.userMetadata())
                    .map(meta -> meta.get(FileConstants.META_FILE_NAME))
                    .orElseGet(() -> fullPath.substring(fullPath.lastIndexOf('/') + 1));

            return new FileStreamResult(
                    response,
                    stat.contentType(),
                    filename,
                    stat.size()
            );
        } catch (Exception e) {
            log.error("Error to read file stream: {} Filename:{}", bucket, fullPath, e);
            throw new RuntimeException("Failed to read file stream: " + bucket + " FileName: " + fullPath, e);
        }
    }


    private FileStoreEntryBaseDTO saveFileInStore(FileStoreEntryBaseDTO fileStoreEntry, String fileId) throws Exception {
        Map<String, String> meta = createMetadata(fileStoreEntry, fileId);
        String fullPath = buildFullPath(fileStoreEntry.getRelativePath(), fileId);
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(fileStoreEntry.getBucket())
                .object(fullPath)
                .contentType(fileStoreEntry.getContentType())
                .stream(new ByteArrayInputStream(fileStoreEntry.getContent()), fileStoreEntry.getFileSize(), -1)
                .userMetadata(meta)
                .build();

        minioClient.putObject(putObjectArgs);
        FileStoreEntryBaseDTO result = (FileStoreEntryBaseDTO) fileStoreEntry.clone();
        result.setFileId(fileId);
        result.setFullPath(fullPath);
        result.setContent(null);
        return result;
    }

    private Map<String, String> createMetadata(FileStoreEntryBaseDTO fileStoreEntry, String fileId) {
        Map<String, String> meta = new HashMap<>();
        meta.put(FileConstants.META_FILE_ID, fileId);
        if (fileStoreEntry.getFileSize() != null) {
            meta.put(FileConstants.META_FILE_SIZE, fileStoreEntry.getFileSize().toString());
        } else if (fileStoreEntry.getContent() != null) {
            meta.put(FileConstants.META_FILE_SIZE, fileStoreEntry.getContent().length + "");
        }
        if (fileStoreEntry.getFileName() != null) {
            meta.put(FileConstants.META_FILE_NAME, fileStoreEntry.getFileName());
        }
        if (fileStoreEntry.getAdditionalMetadata() != null) {
            meta.putAll(fileStoreEntry.getAdditionalMetadata());
        }
        return meta;
    }

    private void fillEntryDetails(StatObjectResponse statObject, String bucket, String fullPath, FileStoreEntryBaseDTO entry) {
        entry.setContentType(statObject.contentType());
        entry.setBucket(bucket);
        String relativePath = "";
        String fileId = fullPath;
        if (fullPath.lastIndexOf("/") >= 0) {
            relativePath = fullPath.substring(0, fullPath.lastIndexOf("/"));
            fileId = fullPath.substring(fullPath.lastIndexOf("/") + 1);
        }
        entry.setRelativePath(relativePath);
        entry.setFullPath(fullPath);
        entry.setFileId(fileId);

        fillEntryDetailsFromMinioMeta(statObject.userMetadata(), entry);
    }

    private void fillEntryDetailsFromMinioMeta(Map<String, String> meta, FileStoreEntryBaseDTO entry) {
        if (meta != null && !meta.isEmpty()) {
            Map<String, String> additionalMeta = new HashMap<>(meta);
            if (meta.containsKey(FileConstants.META_FILE_NAME)) {
                entry.setFileName(meta.get(FileConstants.META_FILE_NAME));
                additionalMeta.remove(FileConstants.META_FILE_NAME);
            }
            if (meta.containsKey(FileConstants.META_FILE_SIZE)) {
                entry.setFileSize(Long.parseLong(meta.get(FileConstants.META_FILE_SIZE)));
                additionalMeta.remove(FileConstants.META_FILE_SIZE);
            }
            if (meta.containsKey(FileConstants.META_FILE_ID)) {
                additionalMeta.remove(FileConstants.META_FILE_ID);
            }
            entry.setAdditionalMetadata(additionalMeta);
        } else {
            entry.setFileName(entry.getFileId());
        }
    }

    private boolean objectExists(String rootDirectory, String fullPath) throws ServerException, InsufficientDataException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try (GetObjectResponse existing = minioClient.getObject(GetObjectArgs.builder().bucket(rootDirectory).object(fullPath).build())) {
            if (existing != null) {
                return true;
            } else {
                return false;
            }
        } catch (ErrorResponseException e) {
            return false;
        }
    }

    private String buildFullPath(String relativePath, String fileId) {
        return relativePath + FileConstants.PATH_SEPARATOR + fileId;
    }

    private String getFullPath(FileStoreEntryBaseDTO entry) {
        if (entry.getFullPath() != null && "".equals(entry.getFullPath())) {
            return entry.getFullPath();
        }
        return buildFullPath(entry.getRelativePath(), entry.getFileId());
    }

}
