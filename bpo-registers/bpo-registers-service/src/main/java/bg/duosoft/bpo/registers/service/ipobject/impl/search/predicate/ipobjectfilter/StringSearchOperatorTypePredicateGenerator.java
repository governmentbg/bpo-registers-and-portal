package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.getWildcardPredicate;


/**
 * User: ggeorgiev
 * Date: 10.05.2022
 * Time: 12:59
 */
public abstract class StringSearchOperatorTypePredicateGenerator extends SearchOperatorTypePredicateGenerator<String> {

    @Override
    protected SearchPredicate generatePredicate(SearchPredicateFactory qb, String field, String code) {
        return getWildcardPredicate(qb, field, code + "*", false);
    }
}
