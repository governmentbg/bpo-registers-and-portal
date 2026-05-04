package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.NestedPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.*;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:25
 */
abstract class PersonNamePredicateGenerator extends StringSearchTypePredicateGenerator {
    protected NestedPredicateClausesStep<?> generateQuery(SearchPredicateFactory qb, TextSearchType searchType, String personName, PersonRole personRole, String nationalityCountryCode) {

        List<SearchPredicate> result = new ArrayList<>();
        Optional<SearchPredicate> res = generateQuery(qb, searchType, personName, FIELD_OBJECT_PERSON_NAME_CUSTOM, FIELD_OBJECT_PERSON_NAME, FIELD_OBJECT_PERSON_NAME_CUSTOM);
        res.ifPresent(r -> result.add(r));
        if (!ObjectUtils.isEmpty(nationalityCountryCode)) {
            result.add(BasePredicateUtils.getTermPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_PERSON_NATIONALITY_COUNTRY, nationalityCountryCode, ValueConvert.YES));
        }
        if (result.size() > 0) {
            NestedPredicateClausesStep<?> x = qb.nested("ipObject.persons");
            result.forEach(x::add);
            x.add(BasePredicateUtils.getTermPredicate(qb, FIELD_OBJECT_PERSON_ROLE, personRole.code(), ValueConvert.YES));
            return x;
        }
        return null;
    }


}
