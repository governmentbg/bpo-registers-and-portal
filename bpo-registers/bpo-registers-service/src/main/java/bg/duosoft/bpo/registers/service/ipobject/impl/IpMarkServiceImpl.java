package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMark;
import bg.duosoft.bpo.registers.mapper.ipobject.IpMarkMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectBaseMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpMarkRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:44
 */
@Service
public class IpMarkServiceImpl extends IpObjectServiceBaseImpl<EIpMark, IpMarkDTO> implements IpMarkService {

    @Autowired
    private IpMarkMapper ipMarkMapper;

    @Autowired
    private IpMarkRepository ipMarkRepository;

    @Override
    public BaseRepository<String, EIpMark> getRepository() {
        return ipMarkRepository;
    }

    @Override
    public IpObjectBaseMapper<EIpMark, IpMarkDTO> getObjectMapper() {
        return ipMarkMapper;
    }


    @Override
    public Page<IpMarkDTO> getMarksPaged(Pageable pageable) {
        Page<EIpMark> marksPage = ipMarkRepository.findAll(pageable);
        return marksPage.map(ipMark -> ipMarkMapper.toDto(ipMark, true));
    }
}
