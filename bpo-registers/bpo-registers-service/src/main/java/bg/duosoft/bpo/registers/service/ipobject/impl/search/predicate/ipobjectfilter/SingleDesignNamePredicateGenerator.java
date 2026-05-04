package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.*;

class SingleDesignNamePredicateGenerator extends StringSearchTypePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        if (StringUtils.hasText(filter.getSingleDesignName())) {
            Optional<SearchPredicate> searchPredicateName = generateQuery(qb, filter.getSingleDesignNameSearchType(), filter.getSingleDesignName(), FIELD_SINGLE_DESIGN_NAME_CUSTOM, FIELD_SINGLE_DESIGN_NAME, FIELD_SINGLE_DESIGN_NAME_CUSTOM);
            Optional<SearchPredicate> searchPredicateNameEn = generateQuery(qb, filter.getSingleDesignNameSearchType(), filter.getSingleDesignName(), FIELD_SINGLE_DESIGN_NAME_EN_CUSTOM, FIELD_SINGLE_DESIGN_NAME_EN, FIELD_SINGLE_DESIGN_NAME_EN_CUSTOM);
//            SearchPredicate res = qb.bool(b -> {
//                searchPredicateName.ifPresent(b::should);
//                searchPredicateNameEn.ifPresent(b::should);
//            }).toPredicate();
            if (searchPredicateName.isPresent() && searchPredicateNameEn.isPresent()) {
                SearchPredicate res = qb.or(searchPredicateName.get(), searchPredicateNameEn.get()).toPredicate();
                return Optional.of(res);
            }
        }
        return Optional.empty();
    }
}
