package bg.duosoft.bpo.registers.service.file;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;

public interface ThumbnailService {
    FileStoreEntryBaseDTO getThumbnailByObjectIdAndType(String objectId, String objectType);
}
