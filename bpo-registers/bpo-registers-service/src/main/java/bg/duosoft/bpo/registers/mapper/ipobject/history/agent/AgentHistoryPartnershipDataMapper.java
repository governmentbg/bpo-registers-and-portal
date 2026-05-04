package bg.duosoft.bpo.registers.mapper.ipobject.history.agent;

import bg.duosoft.bpo.registers.dto.ipobject.history.agent.AgentHistoryPartnershipDataDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.publik.core.enums.PartnershipMemberKind;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.nonentity.history.agent.*;
import bg.duosoft.bpo.publik.core.service.nomenclature.RepresentativeTypeService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        AgentHistoryNameAddressMapper.class,
        AgentHistoryPartnershipMemberMapper.class,
        AgentHistoryMapperHelper.class
})
public abstract class AgentHistoryPartnershipDataMapper {

    @Autowired
    private RepresentativeTypeService representativeTypeService;

    @Mapping(source = "partnershipCode", target = "agentCode")
    @Mapping(source = "partnershipType", target = "representativeType", qualifiedByName = "partnershipTypeMapping")
    @Mapping(source = "formattedNameAddress", target = "nameAddress")
    @Mapping(source = "ipObjects", target = "agentSpeciality", qualifiedByName = "agentSpecialityMapping")
    @Mapping(source = "partnershipRepresentativeKind", target = "partnershipRepresentativeKind", qualifiedByName = "partnershipRepresentativeKindMapping")
    @Mapping(source = "partnershipMembers.partnershipMember", target = "partnershipMembers")
    public abstract AgentHistoryPartnershipDataDTO toDto(HistoryPartnershipDataType e);

    @Named("partnershipTypeMapping")
    public RepresentativeTypeDTO partnershipTypeMapping(PartnershipType e) {
        if (Objects.isNull(e)) {
            return null;
        }

        RepresentativeType representativeType = RepresentativeType.selectByName(e.value());
        if (Objects.isNull(representativeType)) {
            return null;

        }

        return representativeTypeService.getById(representativeType.code());
    }

    @Named("partnershipRepresentativeKindMapping")
    public PartnershipMemberKind partnershipRepresentativeKindMapping(PartnershipRepresentativeKindType e) {
        if (Objects.isNull(e)) {
            return null;
        }

        return PartnershipMemberKind.selectByName(e.value());
    }

}
