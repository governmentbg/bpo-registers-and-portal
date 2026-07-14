package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.*;

class SingleDesignVerbalElementPredicateGenerator extends StringSearchTypePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        if (StringUtils.hasText(filter.getSingleDesignVerbalElement())) {
            Optional<SearchPredicate> searchPredicateVerbalElement = generateQuery(qb, filter.getSingleDesignVerbalElementSearchType(), filter.getSingleDesignVerbalElement(), FIELD_SINGLE_DESIGN_VERBAL_ELEMENT_CUSTOM, FIELD_SINGLE_DESIGN_VERBAL_ELEMENT, FIELD_SINGLE_DESIGN_VERBAL_ELEMENT_CUSTOM);
            Optional<SearchPredicate> searchPredicateVerbalElementEn = generateQuery(qb, filter.getSingleDesignVerbalElementSearchType(), filter.getSingleDesignVerbalElement(), FIELD_SINGLE_DESIGN_VERBAL_ELEMENT_EN_CUSTOM, FIELD_SINGLE_DESIGN_VERBAL_ELEMENT_EN, FIELD_SINGLE_DESIGN_VERBAL_ELEMENT_EN_CUSTOM);
            if (searchPredicateVerbalElement.isPresent() && searchPredicateVerbalElementEn.isPresent()) {
                SearchPredicate res = qb.or(searchPredicateVerbalElement.get(), searchPredicateVerbalElementEn.get()).toPredicate();
                return Optional.of(res);
            }
        }
        return Optional.empty();
    }
}
