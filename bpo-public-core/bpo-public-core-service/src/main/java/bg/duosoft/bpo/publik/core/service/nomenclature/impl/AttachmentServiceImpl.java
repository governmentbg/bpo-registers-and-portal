package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.common.util.mimetype.MimeTypeUtils;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.publik.core.config.MinioConfigTemp;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final FileStoreService fileStoreService;
    private final MinioConfigTemp minioConfigTemp;

    @Override
    public FileStoreEntryBaseDTO uploadTempFile(String fileName, byte[] content) {
        FileStoreEntryBaseDTO fileStoreEntryBaseDTO = new FileStoreEntryBaseDTO();
        fileStoreEntryBaseDTO.setBucket(minioConfigTemp.getTempBucket());
        fileStoreEntryBaseDTO.setFileName(fileName);
        fileStoreEntryBaseDTO.setContent(content);
        fileStoreEntryBaseDTO.setFileSize(Long.valueOf(content.length));
        fileStoreEntryBaseDTO.setRelativePath("temp");
        String contentType = MimeTypeUtils.guessMimeFromBytes(content, fileName);
        fileStoreEntryBaseDTO.setContentType(contentType);

        return fileStoreService.saveNewFile("temp", "file", fileStoreEntryBaseDTO);
    }

    @Override
    public void processAttachments(List<FileStoreEntryDTO> attachments, String rootDirectoryNew, String relativePathNew) {
        if (!CollectionUtils.isEmpty(attachments)) {
            for (FileStoreEntryDTO attachment : attachments) {
                if (Objects.isNull(attachment.getId())) {
                    FileStoreEntryBaseDTO newEntry = fileStoreService.moveFile(rootDirectoryNew, relativePathNew, attachment.getFileStoreEntry(), false);
                    attachment.setFileStoreEntry(newEntry);
                }
            }
        }
    }
}
