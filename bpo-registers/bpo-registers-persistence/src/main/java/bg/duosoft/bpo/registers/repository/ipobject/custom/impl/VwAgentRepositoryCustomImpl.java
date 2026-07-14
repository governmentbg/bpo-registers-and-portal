package bg.duosoft.bpo.registers.repository.ipobject.custom.impl;

import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.entity.ipobject.VwAgent;
import bg.duosoft.bpo.registers.nonentity.filter.VwAgentFilter;
import bg.duosoft.bpo.registers.nonentity.filter.sort.VwAgentSortUtils;
import bg.duosoft.bpo.registers.repository.ipobject.custom.VwAgentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 14:05
 */
@Repository
@RequiredArgsConstructor
public class VwAgentRepositoryCustomImpl implements VwAgentRepositoryCustom {
    private final EntityManager em;

    public List<VwAgent> findAgents(VwAgentFilter filter) {
        TypedQuery<VwAgent> query = createQuery(filter, false, VwAgent.class);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    public Integer countAgents(VwAgentFilter filter) {
        TypedQuery<Number> query = createQuery(filter, true, Number.class);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    public List<VwAgent> getMatchingAgents(EIpPerson agent, Integer maxCount, boolean strongMatch) {
        StringBuilder queryBuilder = new StringBuilder(" SELECT p FROM VwAgent p WHERE p.representativeType in :representativeTypes ");
        Map<String, Object> params = new HashMap();

        params.put("representativeTypes", RepresentativeType.REAL_REPRESENTATIVE_TYPES.stream().map(r -> r.code()).toList());
        if (StringUtils.hasText(agent.getName())) {
            queryBuilder.append(" AND LOWER(p.person.name) like :name ");
            params.put("name", PersonSearchUtils.buildMatchingSequentialWords(agent.getName()));
        }

        if (agent.getNationalityCountryCode() != null && StringUtils.hasText(agent.getNationalityCountryCode().getId()) && strongMatch) {
            queryBuilder.append(" AND p.person.nationalityCountryCode.id like :country ");
            params.put("country", "%" + agent.getNationalityCountryCode() + "%");
        }

        if (agent.getAddress() != null && StringUtils.hasText(agent.getAddress().getCityName()) && strongMatch) {
            queryBuilder.append(" AND LOWER(p.person.address.cityName) like :city ");
            params.put("city", PersonSearchUtils.buildMatchingSequentialWords(agent.getAddress().getCityName()));
        }

        if (agent.getAddress() != null && StringUtils.hasText(agent.getAddress().getZipCode()) && strongMatch) {
            queryBuilder.append(" AND p.person.address.zipCode like :postCode ");
            params.put("postCode", "%" + agent.getAddress().getZipCode() + "%");
        }

        if (agent.getAddress() != null && StringUtils.hasText(agent.getAddress().getAddressStreet())) {
            String[] normalizedAddressEntries = PersonSearchUtils.normalizeString(agent.getAddress().getAddressStreet()).split(" ");
            if (strongMatch) {
                for (int i = 0; i < normalizedAddressEntries.length; ++i) {
                    queryBuilder.append(" AND LOWER(p.person.address.addressStreet) like :addrEntry").append(i).append(" ");
                    params.put("addrEntry" + i, "%" + normalizedAddressEntries[i].trim() + "%");
                }
            } else {
                String word = PersonSearchUtils.getLongestWord(normalizedAddressEntries);
                queryBuilder.append(" AND LOWER(p.person.address.addressStreet) like :addrEntry ");
                params.put("addrEntry", "%" + word + "%");
            }
        }

        TypedQuery<VwAgent> query = em.createQuery(queryBuilder.toString(), VwAgent.class);
        params.forEach((k, v) -> query.setParameter(k, v));

        query.setMaxResults(maxCount);
        return query.getResultList();
    }

    private <T> TypedQuery<T> createQuery(VwAgentFilter filter, boolean isCount, Class<T> clazz) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount ? " COUNT(ag) " : " ag ")
                .append(" FROM VwAgent ag where 1 = 1 ");

        if (!CollectionUtils.isEmpty(filter.getRepresentativeTypeList())) {
            queryBuilder.append(" AND ag.representativeType in (:representativeTypeList)");
            queryParameters.put("representativeTypeList", filter.getRepresentativeTypeList().stream().map(RepresentativeType::code).collect(Collectors.toList()));
        }

        if (Objects.nonNull(filter.getStatus())) {
            queryBuilder.append(" AND ag.agentStatus = :status");
            queryParameters.put("status", filter.getStatus());
        }

        if (StringUtils.hasText(filter.getName())) {
            queryBuilder.append(" AND (lower(ag.person.name) LIKE :name ) ");
            queryParameters.put("name", "%" + filter.getName().toLowerCase() + "%");
        }

        if (StringUtils.hasText(filter.getAgentCode())) {
            queryBuilder.append(" AND ag.agentCode = :agentCode ");
            queryParameters.put("agentCode", filter.getAgentCode());
        }

        if (!isCount && !ObjectUtils.isEmpty(filter.getOrderBy())) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();

            String sortField = VwAgentSortUtils.sorterColumnMap().get(sortColumn);
            if (StringUtils.hasText(sortField)) {
                String order = String.join(" " + sortOrder + " , ", sortField) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }

        TypedQuery<T> tTypedQuery = em.createQuery(queryBuilder.toString(), clazz);
        queryParameters.keySet().stream().forEach(key -> tTypedQuery.setParameter(key, queryParameters.get(key)));
        return tTypedQuery;
    }
}
