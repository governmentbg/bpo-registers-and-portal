package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:37
 */
class FilingDatePredicateGenerator extends FromToPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generateQuery(qb, IpObjectSearchFields.FIELD_OBJECT_FILING_DATE, filter.getFilingDateFrom(), filter.getFilingDateTo(), ValueConvert.YES);
    }
}
