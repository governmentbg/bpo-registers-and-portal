package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.NestedPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:28
 */
class InventorPersonNamePredicateGenerator extends PersonNamePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        NestedPredicateClausesStep<?> res = generateQuery(qb, filter.getInventorPersonSearchType(), filter.getInventor(), PersonRole.INVENTOR, filter.getInventorCountry());
        return res == null ? Optional.empty() : Optional.ofNullable(res.toPredicate());
    }
}
