package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.dto.ipobject.IpDesignDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpDesign;
import bg.duosoft.bpo.registers.mapper.ipobject.IpDesignMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectBaseMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpDesignRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:20
 */
@Service
public class IpDesignServiceImpl extends IpObjectServiceBaseImpl<EIpDesign, IpDesignDTO> implements IpDesignService {

    @Autowired
    private IpDesignRepository ipDesignRepository;

    @Autowired
    private IpDesignMapper ipDesignMapper;

    @Override
    public BaseRepository<String, EIpDesign> getRepository() {
        return ipDesignRepository;
    }

    @Override
    public IpObjectBaseMapper<EIpDesign, IpDesignDTO> getObjectMapper() {
        return ipDesignMapper;
    }
}
