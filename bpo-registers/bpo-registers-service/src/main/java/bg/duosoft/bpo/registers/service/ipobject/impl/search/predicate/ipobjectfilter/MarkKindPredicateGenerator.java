package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 14:21
 */
public class MarkKindPredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory factory, IpObjectFilter filter) {
        if (!ObjectUtils.isEmpty(filter.getMarkKind() )) {
            return Optional.of(BasePredicateUtils.getTermPredicate(factory, IpObjectSearchFields.FIELD_MARK_MARK_KIND, filter.getMarkKind(), ValueConvert.YES));
        }
        return Optional.empty();
    }
}
