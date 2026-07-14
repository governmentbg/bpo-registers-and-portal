package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.matchAny;
import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_STATUS_ID;

/**
 * User: ggeorgiev
 * Date: 12.02.2024
 */
class ObjectStatusPredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        if (!ObjectUtils.isEmpty(filter.getStatusCodes())) {
            return Optional.ofNullable(matchAny(qb, FIELD_OBJECT_STATUS_ID, filter.getStatusCodes()));
        } else {
            return Optional.empty();
        }

    }
}
