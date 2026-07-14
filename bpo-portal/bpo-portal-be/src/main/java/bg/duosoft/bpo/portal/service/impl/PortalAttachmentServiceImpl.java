package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.portal.dto.PortalAttachmentShort;
import bg.duosoft.bpo.portal.mapper.PortalAttachmentMapper;
import bg.duosoft.bpo.portal.mapper.PortalAttachmentShortMapper;
import bg.duosoft.bpo.portal.repository.PortalAttachmentRepository;
import bg.duosoft.bpo.portal.service.PortalAttachmentsService;
import bg.duosoft.bpo.publik.core.dto.common.AttachmentDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class PortalAttachmentServiceImpl implements PortalAttachmentsService {

    private final PortalAttachmentRepository portalAttachmentRepository;
    private final PortalAttachmentMapper portalAttachmentMapper;
    private final PortalAttachmentShortMapper portalAttachmentShortMapper;
    private final FileStoreService fileStoreService;

    @Override
    public List<PortalAttachmentShort>  getAllPortalAttachments() {
        return this.portalAttachmentShortMapper
                .toDtoList(this.portalAttachmentRepository
                        .getAllPortalAttachmentsOrderByPosition());
    }

    @Override
    public FileStoreEntryBaseDTO getFileStoreEntryById(String id) {
        AttachmentDTO portalAttachment = this.portalAttachmentMapper
                .toDto(this.portalAttachmentRepository
                        .getPortalAttachmentById(id))
                .getAttachment();

        if (Objects.isNull(portalAttachment)) {
            throw  new ResourceNotFoundException();
        }

        return fileStoreService
                .getFileStoreEntryDetailsAndContent(portalAttachment.getBucketName(),
                        portalAttachment.getFileLocation());
    }
}
