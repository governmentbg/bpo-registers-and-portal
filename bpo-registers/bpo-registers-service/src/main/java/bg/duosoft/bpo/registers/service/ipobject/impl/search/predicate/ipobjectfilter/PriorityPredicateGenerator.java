package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
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


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 15:03
 */
public class PriorityPredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {


        List<SearchPredicate> parts = new ArrayList<>();
        BasePredicateUtils.generateFromToPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_PRIORITY_DATE, filter.getPriorityDateFrom(), filter.getPriorityDateTo(), ValueConvert.YES).ifPresent(q -> parts.add(q));


        if (!ObjectUtils.isEmpty(filter.getPriorityNumber())) {
            parts.add(BasePredicateUtils.getTermPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_PRIORITY_NUMBER, normalizeString(filter.getPriorityNumber()), ValueConvert.YES));
        }
        if (!ObjectUtils.isEmpty(filter.getPriorityCountry())) {
            parts.add(BasePredicateUtils.getTermPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_PRIORITY_COUNTRY, filter.getPriorityCountry(), ValueConvert.YES));
        }
        NestedPredicateClausesStep<?> x = qb.nested("ipObject.priorities");
        if (!parts.isEmpty()) {
            Optional<SearchPredicate> acceptedStatusPredicate = BasePredicateUtils.generateFromToPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_PRIORITY_STATUS, 1, 1, ValueConvert.YES);
            acceptedStatusPredicate.ifPresent(parts::add);
            parts.forEach(x::add);
            return Optional.of(x.toPredicate());
        }

        return Optional.empty();
    }
}
