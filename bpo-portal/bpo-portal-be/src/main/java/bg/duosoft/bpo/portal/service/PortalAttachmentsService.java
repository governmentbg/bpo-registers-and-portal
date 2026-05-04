package bg.duosoft.bpo.portal.service;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.portal.dto.PortalAttachmentShort;

import java.util.List;

public interface PortalAttachmentsService {

    List<PortalAttachmentShort> getAllPortalAttachments();

    FileStoreEntryBaseDTO getFileStoreEntryById(String id);
}