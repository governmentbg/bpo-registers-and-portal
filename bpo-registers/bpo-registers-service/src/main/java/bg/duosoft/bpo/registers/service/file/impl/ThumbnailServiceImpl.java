package bg.duosoft.bpo.registers.service.file.impl;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentDTO;
import bg.duosoft.bpo.publik.core.enums.ObjectType;
import bg.duosoft.bpo.registers.service.file.ThumbnailService;
import bg.duosoft.bpo.registers.service.ipobject.IpMarkAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ThumbnailServiceImpl implements ThumbnailService {
    private final FileStoreService fileStoreService;
    private final IpMarkAttachmentService ipMarkAttachmentService;

    @Override
    public FileStoreEntryBaseDTO getThumbnailByObjectIdAndType(String objectId, String objectType) {
        ObjectType objectTypeEnum = ObjectType.selectByCode(objectType);
        if (objectTypeEnum == ObjectType.MARK) {
            return getMarkThumbnailFileStoreEntry(objectId);
        }

        return null;
    }

    private FileStoreEntryBaseDTO getMarkThumbnailFileStoreEntry(String objectId) {
        IpMarkAttachmentDTO ipMarkAttachment = ipMarkAttachmentService.selectMarkAttachmentByObjectId(objectId, null);
        if (Objects.nonNull(ipMarkAttachment)) {
            return getThumbnailFileStoreEntry(ipMarkAttachment.getThumbnail());
        }

        return null;
    }

    private FileStoreEntryBaseDTO getThumbnailFileStoreEntry(AttachmentDTO thumbnailAttachment) {
        if (Objects.nonNull(thumbnailAttachment)) {
            return fileStoreService.getFileStoreEntryDetailsAndContent(thumbnailAttachment.getBucketName(), thumbnailAttachment.getFileLocation());
        }

        return null;
    }
}
