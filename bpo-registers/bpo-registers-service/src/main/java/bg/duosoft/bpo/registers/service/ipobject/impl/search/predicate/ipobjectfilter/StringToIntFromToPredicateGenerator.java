package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.generateFromToPredicate;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 11:35
 */
abstract class StringToIntFromToPredicateGenerator extends FromToPredicateGenerator {
    protected Optional<SearchPredicate> generateQuery(SearchPredicateFactory qb, String field, Object from, Object to, ValueConvert valueConvert) {
        Integer f = parseInteger((String) from, null);
        Integer t = parseInteger((String)to, null);
        return generateFromToPredicate(qb, field, f, t, valueConvert);
    }
    private Integer parseInteger(String val, Integer defaultValue) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
