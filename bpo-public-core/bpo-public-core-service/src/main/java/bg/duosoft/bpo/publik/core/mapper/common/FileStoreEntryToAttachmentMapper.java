package bg.duosoft.bpo.publik.core.mapper.common;


import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AttachmentTypeDTO;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileStoreEntryToAttachmentMapper {

    private static final String ATTACHMENT_TYPE_OTHER = "OTHER";

    @Autowired
    private AttachmentMapper attachmentMapper;

    private AttachmentDTO toAttachmentDTO(FileStoreEntryDTO fileStoreEntryDTO) {
        if (Objects.isNull(fileStoreEntryDTO))
            return null;

        AttachmentDTO attachment = new AttachmentDTO();
        FileStoreEntryBaseDTO fileStoreEntry = fileStoreEntryDTO.getFileStoreEntry();
        attachment.setId(fileStoreEntryDTO.getId());
        attachment.setFileName(fileStoreEntry.getFileName());
        attachment.setContentType(fileStoreEntry.getContentType());
        attachment.setFileSize(fileStoreEntry.getFileSize().intValue());
        attachment.setBucketName(fileStoreEntry.getBucket());
        attachment.setFileLocation(fileStoreEntry.getFullPath());
        attachment.setDescription(fileStoreEntryDTO.getDescription());
        if (Objects.isNull(fileStoreEntryDTO.getType()) || Objects.isNull(fileStoreEntryDTO.getType().getId())) {
            AttachmentTypeDTO type = new AttachmentTypeDTO();
            type.setId(ATTACHMENT_TYPE_OTHER);
            attachment.setAttachmentType(type);
        } else {
            attachment.setAttachmentType(fileStoreEntryDTO.getType());
        }

        return attachment;
    }

    private FileStoreEntryDTO toFileStoreEntryDTO(AttachmentDTO attachment) {
        if (Objects.isNull(attachment))
            return null;

        FileStoreEntryDTO fileStoreEntryDTO = new FileStoreEntryDTO();
        FileStoreEntryBaseDTO fileStoreEntry = new FileStoreEntryBaseDTO();
        fileStoreEntryDTO.setId(attachment.getId());
        fileStoreEntryDTO.setDescription(attachment.getDescription());
        fileStoreEntryDTO.setType(attachment.getAttachmentType());
        fileStoreEntry.setFileName(attachment.getFileName());
        fileStoreEntry.setContentType(attachment.getContentType());
        fileStoreEntry.setFileSize(attachment.getFileSize().longValue());
        fileStoreEntry.setBucket(attachment.getBucketName());
        fileStoreEntry.setFullPath(attachment.getFileLocation());
        fileStoreEntryDTO.setFileStoreEntry(fileStoreEntry);

        return fileStoreEntryDTO;
    }

    public List<EAttachment> toAttachmentEntities(List<FileStoreEntryDTO> fileStoreEntries) {
        List<EAttachment> result = new ArrayList();
        if (!CollectionUtils.isEmpty(fileStoreEntries)) {
            for (FileStoreEntryDTO fileStoreEntry : fileStoreEntries) {
                AttachmentDTO attachmentDTO = toAttachmentDTO(fileStoreEntry);
                result.add(attachmentMapper.toEntity(attachmentDTO));
            }
        }

        return result;
    }

    public List<FileStoreEntryDTO> toFileStoreEntries(List<EAttachment> attachments) {
        List<FileStoreEntryDTO> result = new ArrayList();
        if (!CollectionUtils.isEmpty(attachments)) {
            for (EAttachment attachment : attachments) {
                AttachmentDTO attachmentDTO = attachmentMapper.toDto(attachment);
                result.add(toFileStoreEntryDTO(attachmentDTO));
            }
        }
        return result;
    }
}
