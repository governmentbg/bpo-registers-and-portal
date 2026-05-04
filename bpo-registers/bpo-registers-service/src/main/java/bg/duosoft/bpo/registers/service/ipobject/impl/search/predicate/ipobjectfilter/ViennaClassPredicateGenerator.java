package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_MARK_VIENNA_CLASSES;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:22
 */
class ViennaClassPredicateGenerator extends StringSearchOperatorTypePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generatePredicate(qb, FIELD_MARK_VIENNA_CLASSES, filter.getViennaClassCodes(), filter.getViennaClassCodeType());
    }
}
