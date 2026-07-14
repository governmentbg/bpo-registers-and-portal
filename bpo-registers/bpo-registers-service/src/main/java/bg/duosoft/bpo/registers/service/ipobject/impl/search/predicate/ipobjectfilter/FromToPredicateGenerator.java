package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.generateFromToPredicate;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:41
 */
abstract class FromToPredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    protected Optional<SearchPredicate> generateQuery(SearchPredicateFactory qb, String field, Object from, Object to, ValueConvert valueConvert) {
        return generateFromToPredicate(qb, field, from, to, valueConvert);
    }
}
