package bg.duosoft.bpo.registers.repository.ipobject.custom.impl;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.nonentity.filter.EIpAgentDataFilter;
import bg.duosoft.bpo.registers.nonentity.filter.sort.AgentsSortUtils;
import bg.duosoft.bpo.registers.repository.ipobject.custom.IpAgentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class IpAgentRepositoryCustomImpl implements IpAgentRepositoryCustom {
    @Autowired
    private EntityManager bpoRegistersEntityManager;

    @Override
    public List<EIpPerson> filterAgents(EIpAgentDataFilter filter) {
        TypedQuery<EIpPerson> query = createQuery(filter, false, EIpPerson.class);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer countAgents(EIpAgentDataFilter filter) {
        TypedQuery<Number> query = createQuery(filter, true, Number.class);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    private <T> TypedQuery<T> createQuery(EIpAgentDataFilter filter, boolean isCount, Class<T> clazz) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount ? " COUNT(ag) " : " ag ")
                .append(" FROM EIpPerson ag where ag.agent is not null ");

        if (!CollectionUtils.isEmpty(filter.getRepresentativeTypeList())) {
            queryBuilder.append(" AND ag.agent.representativeType.id in (:representativeTypeList)");
            queryParameters.put("representativeTypeList", filter.getRepresentativeTypeList().stream().map(RepresentativeType::code).collect(Collectors.toList()));
        }

        if (StringUtils.hasText(filter.getAgentCode())) {
            queryBuilder.append(" AND ag.agent.agentCode = :agentCode");
            queryParameters.put("agentCode", filter.getAgentCode());
        }

        if (StringUtils.hasText(filter.getIpoArea())) {
            queryBuilder.append(" AND ag.agent.agentSpeciality.id = :ipoArea");
            queryParameters.put("ipoArea", filter.getIpoArea());
        }

        if (Objects.nonNull(filter.getStatus())) {
            queryBuilder.append(" AND ag.agent.agentStatus.id = :status");
            queryParameters.put("status", filter.getStatus());
        }

        if (StringUtils.hasText(filter.getAgentSpeciality())) {
            queryBuilder.append(" AND (lower(ag.agent.speciality) LIKE :agentSpeciality or lower(ag.agent.specialityEn) LIKE :agentSpeciality) ");
            queryParameters.put("agentSpeciality", "%" + filter.getAgentSpeciality().toLowerCase() + "%");
        }

        if (StringUtils.hasText(filter.getName())) {
            queryBuilder.append(" AND (lower(ag.name) LIKE :name or lower(ag.agent.nameEn) LIKE :name) ");
            queryParameters.put("name", "%" + filter.getName().toLowerCase() + "%");
        }


        if (StringUtils.hasText(filter.getCity())) {
            queryBuilder.append(" AND (lower(ag.address.cityName) LIKE :city or lower(ag.agent.address.cityNameEn) LIKE :city) ");
            queryParameters.put("city", "%" + filter.getCity().toLowerCase() + "%");
        }

        if (!isCount && !ObjectUtils.isEmpty(filter.getOrderBy())) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();

            if (!StringUtils.hasText(filter.getLanguage()))
                filter.setLanguage("bg");

            String sortField = AgentsSortUtils.sorterColumnMap().get(sortColumn.concat("-").concat(filter.getLanguage()));
            if (StringUtils.hasText(sortField)) {
                String order = String.join(" " + sortOrder + " , ", sortField) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }

        TypedQuery<T> tTypedQuery = bpoRegistersEntityManager.createQuery(queryBuilder.toString(), clazz);
        queryParameters.keySet().stream().forEach(key -> tTypedQuery.setParameter(key, queryParameters.get(key)));
        return tTypedQuery;
    }
}
