package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 15:57
 */
public abstract class SearchOperatorTypePredicateGenerator<T> implements BaseIpObjectFilterPredicateGenerator {


    protected Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, String field, List<T> codes, SearchOperatorType operatorType) {
        if (!CollectionUtils.isEmpty(codes)) {
            SearchPredicate predicate = qb.bool(b -> {
                for (T code : codes) {
                    SearchPredicate wildCardQuery = generatePredicate(qb, field, code);
                    switch (operatorType) {
                        case AND_NOT:
                            b.mustNot(wildCardQuery);
                            break;
                        case OR:
                            b.should(wildCardQuery);
                            break;
                        default:
                            b.must(wildCardQuery);
                            break;
                    }
                }
            }).toPredicate();

            if (operatorType == SearchOperatorType.AND_NOT) {
                /*SearchPredicate predicate2 = qb.bool(b -> {
                    b.must(predicate).must(getQueryWildcard(qb, field, "*", false));
                }).toPredicate();
                return Optional.of(predicate2);*/
                return Optional.of(predicate);
            } else {
                return Optional.of(predicate);
            }
        }
        return Optional.empty();
    }
    protected abstract SearchPredicate generatePredicate(SearchPredicateFactory qb, String field, T code);

}
