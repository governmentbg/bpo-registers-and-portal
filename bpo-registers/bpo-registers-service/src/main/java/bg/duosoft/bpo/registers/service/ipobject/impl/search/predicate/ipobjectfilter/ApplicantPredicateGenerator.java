package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.NestedPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:28
 */
class ApplicantPredicateGenerator extends PersonNamePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        NestedPredicateClausesStep<?> res = generateQuery(qb, filter.getApplicantOwnerPersonSearchType(), filter.getApplicantOwner(), PersonRole.OWNER, filter.getApplicantOwnerCountry());
        return res == null ? Optional.empty() : Optional.ofNullable(res.toPredicate());
    }
}
