package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.autocomplete;

import bg.duosoft.bpo.registers.dto.filter.ObjectAutocompleteFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 09.02.2024
 * Time: 17:50
 */
public interface AutocompletePredicateGenerator {
    Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, ObjectAutocompleteFilter filter);
}
