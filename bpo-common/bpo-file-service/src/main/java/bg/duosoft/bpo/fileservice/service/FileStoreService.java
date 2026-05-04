package bg.duosoft.bpo.fileservice.service;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.dto.FileStreamResult;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 29.01.2024
 * Time: 11:43
 */
public interface FileStoreService {
    FileStoreEntryBaseDTO saveNewFileWithCustomFileId(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry);
    FileStoreEntryBaseDTO saveNewFile(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry);
    FileStoreEntryBaseDTO getFileStoreEntryDetailsAndContent(String bucket, String fullPath);
    FileStoreEntryBaseDTO getFileStoreEntryDetailsOnly(String bucket, String fullPath);
    FileStoreEntryBaseDTO moveFile(String rootDirectoryNew, String relativePathNew, FileStoreEntryBaseDTO entryToMove, Boolean removeOriginal);
    void copyFile(String rootDirectoryNew, String relativePathNew, String rootDirectoryOld, String relativePathOld, String fileId);
    void removeFile(String bucket, String fullPath);
    List<String> removeFilesFromDirectory(String rootDirectory, String filesDirectory);
    boolean fileExists(String bucket, String fullPath);
    void emptyBucket(String bucket);

    FileStreamResult readFileStream(String bucket, String fullPath);

}
