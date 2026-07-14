package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.NestedPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_PERSON_REPRESENTATIVE_TYPE;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:52
 */
class RepresentativeNamePredicateGenerator extends PersonNamePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {

        NestedPredicateClausesStep<?> res = generateQuery(qb, filter.getRepresentativeNameSearchType(), filter.getRepresentativeName(), PersonRole.REPRESENTATIVE, null);
        if (res == null) {
            return Optional.empty();
        }

        if (!ObjectUtils.isEmpty(filter.getRepresentativeTypeCodes())) {
            res.add(BasePredicateUtils.matchAny(qb, FIELD_OBJECT_PERSON_REPRESENTATIVE_TYPE, filter.getRepresentativeTypeCodes()));
        }
        return Optional.of(res.toPredicate());
    }
}
