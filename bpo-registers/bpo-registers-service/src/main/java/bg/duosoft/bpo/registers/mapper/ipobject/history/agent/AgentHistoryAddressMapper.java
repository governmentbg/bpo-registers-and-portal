package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryAddressDTO;
import bg.duosoft.bpo.registers.nonentity.history.agent.AddressType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AgentHistoryAddressMapper {

    @Mapping(source = "postalCode", target = "zipCode")
    @Mapping(source = "correspondenceAddress", target = "caAddress")
    @Mapping(source = "correspondenceAddressEn", target = "caAddressEn")
    @Mapping(source = "correspondenceAddressPostalCode", target = "caZipCode")
    @Mapping(source = "correspondenceAddressPhone", target = "caPhone")
    @Mapping(source = "correspondenceAddressEmail", target = "caEmail")
    @Mapping(source = "correspondenceAddressFax", target = "caFax")
    @Mapping(source = "correspondenceAddressCityName", target = "caCityName")
    @Mapping(source = "correspondenceAddressCityNameEn", target = "caCityNameEn")
    public abstract AgentHistoryAddressDTO toDto(AddressType e);
}
