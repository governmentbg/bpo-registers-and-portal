package bg.duosoft.bpo.registers.repository.ipobject.custom.impl;

import bg.duosoft.bpo.registers.entity.ipobject.EIpObject;

import bg.duosoft.bpo.registers.nonentity.filter.EIpObjectFilter;
import bg.duosoft.bpo.registers.repository.ipobject.custom.IpObjectRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.11.2021
 * Time: 15:34
 */
public class IpObjectRepositoryCustomImpl implements IpObjectRepositoryCustom {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<EIpObject> filterObjects(EIpObjectFilter filter) {
        TypedQuery<EIpObject> query = createQuery(filter, false, EIpObject.class);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer countObjects(EIpObjectFilter filter) {
        TypedQuery<Number> query = createQuery(filter, true, Number.class);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    private <T> TypedQuery<T> createQuery(EIpObjectFilter filter, boolean isCount, Class<T> clazz) {
//        Map<String, Object> queryParameters = new HashMap<>();
//        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount? " COUNT(DISTINCT(o)) ": " DISTINCT(o) ")
//                .append(" FROM EIpObject o ");
//
//        if(StringUtils.hasText(filter.getUsername())){
//            queryBuilder.append(" AND e.id.username = :username");
//            queryParameters.put("username", filter.getUsername());
//        }
//        if(StringUtils.hasText(filter.getId())){
//            queryBuilder.append(" AND lower(o.id) LIKE :id");
//            queryParameters.put("id", "%"+filter.getId().toLowerCase()+"%");
//        }
//
//        if(filter.getFilingDateFrom() != null){
//            queryBuilder.append(" AND o.filingDate >= :filingDateFrom");
//            queryParameters.put("filingDateFrom", filter.getFilingDateFrom());
//        }
//        if(filter.getFilingDateTo() != null){
//            queryBuilder.append(" AND o.filingDate <= :filingDateTo");
//            queryParameters.put("filingDateTo", filter.getFilingDateTo());
//        }
//        if(filter.getRegistrationDateFrom() != null){
//            queryBuilder.append(" AND o.registrationDate >= :registrationDateFrom");
//            queryParameters.put("registrationDateFrom", filter.getRegistrationDateFrom());
//        }
//        if(filter.getRegistrationDateTo() != null){
//            queryBuilder.append(" AND o.registrationDate <= :registrationDateTo");
//            queryParameters.put("registrationDateTo", filter.getRegistrationDateTo());
//        }
//        if(StringUtils.hasText(filter.getRegistrationNumber())){
//            queryBuilder.append(" AND lower(o.registrationNumber) LIKE :registrationNumber ");
//            queryParameters.put("registrationNumber", "%"+filter.getRegistrationNumber().toLowerCase()+"%");
//        }
//        if(filter.getStatusId() != null){
//            queryBuilder.append(" AND o.status.id = :status ");
//            queryParameters.put("status", filter.getStatusId());
//        }
//        if(StringUtils.hasText(filter.getTitle())){
//            queryBuilder.append(" AND ");
//            queryBuilder.append(" (lower(o.title) LIKE :title OR lower(o.titleEn) LIKE :title) ");
//            queryParameters.put("title", "%"+filter.getTitle().toLowerCase()+"%");
//        }
//        if(filter.getEntitlementDateFrom() != null){
//            queryBuilder.append(" AND o.entitlementDate >= :entitlementDateFrom ");
//            queryParameters.put("entitlementDateFrom", filter.getEntitlementDateFrom());
//        }
//        if(filter.getEntitlementDateTo() != null){
//            queryBuilder.append(" AND o.entitlementDate <= :entitlementDateTo ");
//            queryParameters.put("entitlementDateTo", filter.getEntitlementDateTo());
//        }
//        if(filter.getExpirationDateFrom() != null){
//            queryBuilder.append(" AND o.expirationDate >= :expirationDateFrom ");
//            queryParameters.put("expirationDateFrom", filter.getExpirationDateFrom());
//        }
//        if(filter.getExpirationDateTo() != null){
//            queryBuilder.append(" AND o.expirationDate <= :expirationDateTo ");
//            queryParameters.put("expirationDateTo", filter.getExpirationDateTo());
//        }
//
//        TypedQuery<T> tTypedQuery = em.createQuery(queryBuilder.toString(), clazz);
//        queryParameters.keySet().stream().forEach(key -> tTypedQuery.setParameter(key, queryParameters.get(key)));
//        return tTypedQuery;
        return null;
    }
}
