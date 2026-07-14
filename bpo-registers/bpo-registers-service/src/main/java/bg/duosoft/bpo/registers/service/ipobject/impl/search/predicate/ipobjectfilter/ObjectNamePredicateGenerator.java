package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_TITLE;
import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_TITLE_CUSTOM;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:32
 */
class ObjectNamePredicateGenerator extends StringSearchTypePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generateQuery(qb, filter.getNameSearchType(), filter.getName(), FIELD_OBJECT_TITLE_CUSTOM, FIELD_OBJECT_TITLE, FIELD_OBJECT_TITLE_CUSTOM);
    }
}
