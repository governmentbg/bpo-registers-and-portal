package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryNameAddressDTO;
import bg.duosoft.bpo.registers.nonentity.history.agent.AddressType;
import bg.duosoft.bpo.registers.nonentity.history.agent.FormattedNameAddressType;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class AgentHistoryNameAddressMapper {

    @Autowired
    private AgentHistoryAddressMapper agentHistoryAddressMapper;

    @Mapping(source = "name", target = "name")
    @Mapping(source = "nameEn", target = "nameEn")
    @Mapping(source = "address", target = "address", ignore = true)
    public abstract AgentHistoryNameAddressDTO toDto(FormattedNameAddressType e);

    @AfterMapping
    protected void afterMapping(FormattedNameAddressType source, @MappingTarget AgentHistoryNameAddressDTO target) {
        List<AddressType> addressList = source.getAddress();
        if (!CollectionUtils.isEmpty(addressList)) {
            AddressType addressType = addressList.get(0);

            if (Objects.nonNull(addressType)) {
                target.setAddress(agentHistoryAddressMapper.toDto(addressType));
            }
        }
    }

}
