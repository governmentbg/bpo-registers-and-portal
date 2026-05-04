package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.IpMarkAttachmentMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpMarkAttachmentRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpMarkAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class IpMarkAttachmentServiceImpl implements IpMarkAttachmentService {

    private final IpMarkAttachmentRepository ipMarkAttachmentRepository;
    private final IpMarkAttachmentMapper ipMarkAttachmentMapper;

    public IpMarkAttachmentDTO selectMarkAttachmentByObjectId(String objectId, Integer sequenceNumber) {
        return ipMarkAttachmentMapper.toDto(ipMarkAttachmentRepository.selectByMarkIdAndSequenceNumber(objectId, sequenceNumber == null || sequenceNumber == 0 ? 1 : sequenceNumber));
    }
}
