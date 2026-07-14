package bg.duosoft.bpo.registers.repository.legaldecision.custom.impl;

import bg.duosoft.bpo.registers.entity.legaldecision.ELegalDecisionDocument;
import bg.duosoft.bpo.registers.nonentity.filter.ELegalDecisionDocumentDataFilter;
import bg.duosoft.bpo.registers.nonentity.filter.ESearchOperatorType;
import bg.duosoft.bpo.registers.nonentity.filter.sort.LegalDecisionSortUtils;
import bg.duosoft.bpo.registers.repository.legaldecision.custom.LegalDecisionDocumentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class LegalDecisionDocumentRepositoryCustomImpl implements LegalDecisionDocumentRepositoryCustom {

    @Autowired
    private EntityManager bpoRegistersEntityManager;

    @Override
    public List<ELegalDecisionDocument> filterLegalDecisions(ELegalDecisionDocumentDataFilter filter) {
        TypedQuery<ELegalDecisionDocument> query = this.createQuery(filter, false, ELegalDecisionDocument.class);
        query.setMaxResults(filter.getPageSize());
        query.setFirstResult((filter.getPage()) * filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer countDocuments(ELegalDecisionDocumentDataFilter filter) {
        TypedQuery<Number> query = createQuery(filter, true, Number.class);
        Number result = query.getSingleResult();
        return result.intValue();
    }

    private <T> TypedQuery<T> createQuery(ELegalDecisionDocumentDataFilter filter, boolean isCount, Class<T> clazz) {
        Map<String, Object> queryParameters = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT ").append(isCount ? " COUNT(ld) " : " ld ")
                .append(" FROM ELegalDecisionDocument ld where 1=1 ");

        if (StringUtils.hasText(filter.getObjectId())) {
            queryBuilder.append(" AND ld.objectId like (:objectId)");
            queryParameters.put("objectId", "%" + filter.getObjectId() + "%");
        }

        if (Objects.nonNull(filter.getDocumentType())) {
            queryBuilder.append(" AND ld.documentType.id=:documentType");
            queryParameters.put("documentType", filter.getDocumentType());
        }

        if (Objects.nonNull(filter.getDocumentDateFrom())) {
            queryBuilder.append(" AND ld.documentDate>=:documentDateFrom");
            queryParameters.put("documentDateFrom", filter.getDocumentDateFrom());
        }

        if (Objects.nonNull(filter.getDocumentDateTo())) {
            queryBuilder.append(" AND ld.documentDate<=:documentDateTo");
            queryParameters.put("documentDateTo", filter.getDocumentDateTo());
        }


        if (Objects.nonNull(filter.getDocumentNumber())) {
            queryBuilder.append(" AND ld.documentNumber=:documentNumber");
            queryParameters.put("documentNumber", filter.getDocumentNumber());
        }

        if (StringUtils.hasText(filter.getObjectType())) {
            queryBuilder.append(" AND ld.objectType.id=:objectType");
            queryParameters.put("objectType", filter.getObjectType());
        }

        if (!CollectionUtils.isEmpty(filter.getLegalGroundTypes())) {
            if (filter.getLegalGroundTypesOperatorType().equals(ESearchOperatorType.OR)) {
                queryBuilder.append(" AND EXISTS (SELECT 1 FROM ld.legalGroundTypes lgt WHERE lgt.id IN (:legalGroundTypeIds))");
            } else if (filter.getLegalGroundTypesOperatorType().equals(ESearchOperatorType.AND_NOT)) {
                queryBuilder.append(" AND NOT EXISTS (SELECT 1 FROM ld.legalGroundTypes lgt WHERE lgt.id IN (:legalGroundTypeIds))");
            } else {
                queryBuilder.append(" AND (SELECT COUNT(DISTINCT lgt.id) FROM ld.legalGroundTypes lgt WHERE lgt.id IN (:legalGroundTypeIds)) = :legalGroundTypesCount");
                queryParameters.put("legalGroundTypesCount", filter.getLegalGroundTypes().size());
            }
            queryParameters.put("legalGroundTypeIds", filter.getLegalGroundTypes());
        }

        if (!isCount && StringUtils.hasText(filter.getOrderBy())) {
            String sortColumn = filter.getOrderBy();
            String sortOrder = filter.getOrder();

            String sortField = LegalDecisionSortUtils.sorterColumnMap().get(sortColumn.concat(""));
            if (StringUtils.hasText(sortField)) {
                String order = String.join(" " + sortOrder + " , ", sortField) + " " + sortOrder;
                queryBuilder.append(" ORDER BY ").append(order);
            }
        }

        TypedQuery<T> tTypedQuery = this.bpoRegistersEntityManager.createQuery(queryBuilder.toString(), clazz);
        queryParameters.keySet().stream().forEach(key -> tTypedQuery.setParameter(key, queryParameters.get(key)));
        return tTypedQuery;
    }
}
