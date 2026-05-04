package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;

import java.util.List;

public interface AttachmentService {
    FileStoreEntryBaseDTO uploadTempFile(String fileName, byte[] content);
    void processAttachments(List<FileStoreEntryDTO> attachments, String rootDirectoryNew, String relativePathNew);
}
