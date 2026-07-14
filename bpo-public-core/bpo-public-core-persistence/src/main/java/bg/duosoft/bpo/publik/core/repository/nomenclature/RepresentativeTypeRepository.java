package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.ERepresentativeType;
import bg.duosoft.bpo.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepresentativeTypeRepository extends BaseRepository<String, ERepresentativeType> {
    @Query("SELECT rt from ERepresentativeType rt where rt.ipasRepresentativeType = :ipasRepresentativeType and ((:ipasPartnershipType is null and rt.ipasPartnershipType is null) OR rt.ipasPartnershipType = :ipasPartnershipType) and (:agentCode is null or ((rt.agentCodeFrom is null or rt.agentCodeFrom <= :agentCode ) and (rt.agentCodeTo is null or rt.agentCodeTo >= :agentCode ))) ")
    public List<ERepresentativeType> getRepresentativeTypes(@Param( "ipasRepresentativeType")String ipasRepresentativeType, @Param( "ipasPartnershipType")String ipasPartnershipType, @Param("agentCode")Integer agentCode);
}

