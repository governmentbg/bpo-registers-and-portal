package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatent;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectBaseMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.IpPatentMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpPatentRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:41
 */
@Service
public class IpPatentServiceImpl extends IpObjectServiceBaseImpl<EIpPatent, IpPatentDTO> implements IpPatentService {

    @Autowired
    private IpPatentRepository ipPatentRepository;

    @Autowired
    private IpPatentMapper ipPatentMapper;

    @Override
    public BaseRepository<String, EIpPatent> getRepository() {
        return ipPatentRepository;
    }

    @Override
    public IpObjectBaseMapper<EIpPatent, IpPatentDTO> getObjectMapper() {
        return ipPatentMapper;
    }
}
