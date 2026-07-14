package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.autocomplete;

import bg.duosoft.bpo.registers.dto.filter.ObjectAutocompleteFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 09.02.2024
 * Time: 14:36
 */
class ObjectAutocompleteFilingNumberGenerator implements AutocompletePredicateGenerator {
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory factory, ObjectAutocompleteFilter filter) {
        if (!ObjectUtils.isEmpty(filter.getFilingNumber())) {
            PredicateFinalStep res = factory.bool(b -> {
                SearchPredicate predicate = BasePredicateUtils.getWildcardPredicate(
                        factory,
                        IpObjectSearchFields.FIELD_OBJECT_FILE_ID_TEXT,
                        "*" + filter.getFilingNumber() + "*", true);
                b.must(predicate);
            });
            return Optional.ofNullable(res.toPredicate());
        }
        return Optional.empty();

    }
}
