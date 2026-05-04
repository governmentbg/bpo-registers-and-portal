package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;


/**
 * User: ggeorgiev
 * Date: 10.05.2022
 * Time: 12:59
 */
public abstract class IntegerSearchOperatorTypePredicateGenerator extends SearchOperatorTypePredicateGenerator<Integer> {

    @Override
    protected SearchPredicate generatePredicate(SearchPredicateFactory qb, String field, Integer code) {
        return BasePredicateUtils.generateFromToPredicate(qb, field, code, code, ValueConvert.YES).get();
    }
}
