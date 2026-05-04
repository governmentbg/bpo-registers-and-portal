package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_ENTITLEMENT_DATE;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:37
 */
class EntitlementDatePredicateGenerator extends FromToPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
//        return generateQuery(qb, FIELD_OBJECT_ENTITLEMENT_DATE, filter.getFilingDateFrom(), filter.getFilingDateTo(), ValueConvert.YES);
        throw new RuntimeException("Not implemented. (The old version of the generator were searching by filingDateFrom-To which was wrong)");
    }
}
