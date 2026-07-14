package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.autocomplete;

import bg.duosoft.bpo.registers.dto.filter.ObjectAutocompleteFilter;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.generateListOfTermsPredicate;
import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_OBJECT_TYPE;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:30
 */
class ObjectAutocompleteObjectTypePredicateGenerator implements AutocompletePredicateGenerator{
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, ObjectAutocompleteFilter filter) {
        return generateListOfTermsPredicate(qb, FIELD_OBJECT_OBJECT_TYPE, filter.getObjectRange(), true, ValueConvert.YES);
    }
}
