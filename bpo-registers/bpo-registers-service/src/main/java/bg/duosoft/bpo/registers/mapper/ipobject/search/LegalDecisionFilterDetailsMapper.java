package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionGroundTypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.dto.filter.LegalDecisionFilter;
import bg.duosoft.bpo.registers.nonentity.filter.ELegalDecisionDocumentDataFilter;
import bg.duosoft.bpo.registers.nonentity.history.agent.PartnershipType;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class LegalDecisionFilterDetailsMapper {

    @Mapping(target = "documentType", source = "documentType.id")
    @Mapping(target = "documentDateFrom", source = "documentDate.from")
    @Mapping(target = "documentDateTo", source = "documentDate.to")
    @Mapping(source = "legalGroundTypes", target = "legalGroundTypes", ignore = true)
    public abstract ELegalDecisionDocumentDataFilter toEIpDocumentData(LegalDecisionFilter legalDecisionFilter);

    @AfterMapping
    protected void afterToE(LegalDecisionFilter source, @MappingTarget ELegalDecisionDocumentDataFilter target) {
        target.setLegalGroundTypes(new ArrayList<>());
        for (LegalDecisionGroundTypeDTO x : source.getLegalGroundTypes()) {
            target.getLegalGroundTypes().add(x.getId());
        }
    }
}
