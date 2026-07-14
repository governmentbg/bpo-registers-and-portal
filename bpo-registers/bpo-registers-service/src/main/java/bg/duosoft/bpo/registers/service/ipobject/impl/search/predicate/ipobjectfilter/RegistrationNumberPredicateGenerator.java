package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_REGISTRATION_NUMBER;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:37
 */
class RegistrationNumberPredicateGenerator extends FromToPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generateQuery(qb, FIELD_OBJECT_REGISTRATION_NUMBER, filter.getRegistrationNumberFrom(), filter.getRegistrationNumberTo(), ValueConvert.NO);
    }
}
