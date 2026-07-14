package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.dto.ipobject.IpPlantDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPlant;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectBaseMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.IpPlantMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpPlantRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:21
 */
@Service
public class IpPlantServiceImpl extends IpObjectServiceBaseImpl<EIpPlant, IpPlantDTO> implements IpPlantService {

    @Autowired
    private IpPlantRepository ipPlantRepository;

    @Autowired
    private IpPlantMapper ipPlantMapper;


    @Override
    public BaseRepository<String, EIpPlant> getRepository() {
        return ipPlantRepository;
    }

    @Override
    public IpObjectBaseMapper<EIpPlant, IpPlantDTO> getObjectMapper() {
        return ipPlantMapper;
    }
}
