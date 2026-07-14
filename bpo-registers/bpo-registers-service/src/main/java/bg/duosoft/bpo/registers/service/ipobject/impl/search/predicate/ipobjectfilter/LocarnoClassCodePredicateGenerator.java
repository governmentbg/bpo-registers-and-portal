package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_SINGLE_DESIGN_LOCARNO_CLASS_CODE;

class LocarnoClassCodePredicateGenerator extends StringSearchOperatorTypePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generatePredicate(qb, FIELD_SINGLE_DESIGN_LOCARNO_CLASS_CODE, filter.getLocarnoClassCodes(), filter.getLocarnoClassCodeType());
    }
}
