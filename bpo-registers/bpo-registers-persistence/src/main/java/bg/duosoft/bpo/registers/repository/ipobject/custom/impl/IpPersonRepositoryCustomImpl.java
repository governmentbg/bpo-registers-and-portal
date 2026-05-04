package bg.duosoft.bpo.registers.repository.ipobject.custom.impl;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.nonentity.filter.EIpPersonFilter;
import bg.duosoft.bpo.registers.nonentity.filter.sort.PersonSortUtils;
import bg.duosoft.bpo.registers.repository.ipobject.custom.IpPersonRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 15:39
 */
@Repository
public class IpPersonRepositoryCustomImpl implements IpPersonRepositoryCustom {
    @Autowired
    private EntityManager em;
    @Override
    public List<EIpPerson> findPersons(EIpPersonFilter filter) {
        TypedQuery<EIpPerson> query = createFindPersonsQuery(filter, false, EIpPerson.class);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer countPersons(EIpPersonFilter filter) {
        TypedQuery<Number> query = createFindPersonsQuery(filter, true, Number.class);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    @Override
    public List<EIpPerson> getMatchingPersons(EIpPerson person, int maxCount, boolean strongMatch) {
        StringBuilder queryBuilder = new StringBuilder(" SELECT p FROM EIpPerson p WHERE 1 = 1 ");
        Map<String, Object> params = new HashMap();
        if (person.getLegalType() != null) {
            queryBuilder.append(" AND p.legalType = :legalType");
            params.put("legalType", person.getLegalType());
        }

        if (StringUtils.hasText(person.getName())) {
            queryBuilder.append(" AND LOWER(p.name) like :name");
            params.put("name", PersonSearchUtils.buildMatchingSequentialWords(person.getName()));
        }

        if (person.getNationalityCountryCode() != null && StringUtils.hasText(person.getNationalityCountryCode().getId()) && strongMatch) {
            queryBuilder.append(" AND p.nationalityCountryCode.id = :country ");
            params.put("country", person.getNationalityCountryCode().getId());
        }

        if (person.getAddress() != null && StringUtils.hasText(person.getAddress().getCityName()) && strongMatch) {
            queryBuilder.append(" AND LOWER(p.address.cityName) like :city ");
            params.put("city", PersonSearchUtils.buildMatchingSequentialWords(person.getAddress().getCityName()));
        }

        if (person.getAddress() != null && StringUtils.hasText(person.getAddress().getZipCode()) && strongMatch) {
            queryBuilder.append(" AND p.address.zipCode like :postCode ");
            params.put("postCode", "%" + person.getAddress().getZipCode() + "%");
        }

        if (person.getAddress() != null && StringUtils.hasText(person.getAddress().getAddressStreet())) {
            String[] normalizedAddressEntries = PersonSearchUtils.normalizeString(person.getAddress().getAddressStreet()).split(" ");
            if (strongMatch) {
                for(int i = 0; i < normalizedAddressEntries.length; ++i) {
                    queryBuilder.append(" AND LOWER(p.address.addressStreet) like :addrEntry").append(i).append(" ");
                    params.put("addrEntry" + i, "%" + normalizedAddressEntries[i].trim() + "%");
                }
            } else {
                String word = PersonSearchUtils.getLongestWord(normalizedAddressEntries);
                queryBuilder.append(" AND LOWER(p.address.addressStreet) like :addrEntry ");
                params.put("addrEntry", "%" + word + "%");
            }
        }

        TypedQuery<EIpPerson> query = this.em.createQuery(queryBuilder.toString(), EIpPerson.class);
        params.forEach((k, v) -> query.setParameter(k, v));

        query.setMaxResults(maxCount);
        return query.getResultList();
    }

    private <T> TypedQuery<T> createFindPersonsQuery(EIpPersonFilter filter, boolean isCount, Class<T> clazz) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount ? " COUNT(r) " : " r ")
                .append(" FROM EIpPerson r WHERE 1 = 1 ");

        if (StringUtils.hasText(filter.getName())) {
            String[] parts = filter.getName().split(" +");
            int cnt = 0;
            for (String p : parts) {
                String paramName = "name" + cnt;
                queryBuilder.append("AND lower(r.name) LIKE :%s ".formatted(paramName));
                queryParameters.put(paramName, "%" + p.toLowerCase() + "%");
                cnt++;
            }
        }
        if (filter.getLegalType() != null) {
            queryBuilder.append(" AND r.legalType = :legalType ");
            queryParameters.put("legalType", filter.getLegalType());
        }
        if (!isCount && !ObjectUtils.isEmpty(filter.getOrderBy())) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();

            String sortField = PersonSortUtils.sorterColumnMap().get(sortColumn);
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
