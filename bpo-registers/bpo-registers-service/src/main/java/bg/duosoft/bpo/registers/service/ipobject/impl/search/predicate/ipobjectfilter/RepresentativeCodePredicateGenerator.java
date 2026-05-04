package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.CollectionUtils;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_PERSON_AGENT_CODE_EXACT;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:57
 */
class RepresentativeCodePredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        if (!CollectionUtils.isEmpty(filter.getRepresentativeCodes())) {
            SearchPredicate res = qb.bool(b -> {
                for (String code : filter.getRepresentativeCodes()) {
                    b.should(BasePredicateUtils.getTermPredicate(qb, FIELD_OBJECT_PERSON_AGENT_CODE_EXACT, code, ValueConvert.YES));
                }
            }).toPredicate();

            return Optional.of(res);
        }
        return Optional.empty();
    }
}
