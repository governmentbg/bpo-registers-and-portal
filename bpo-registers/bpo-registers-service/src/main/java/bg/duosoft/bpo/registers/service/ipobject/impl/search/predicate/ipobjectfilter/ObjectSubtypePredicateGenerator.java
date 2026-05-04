package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.generateListOfTermsPredicate;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:47
 */
class ObjectSubtypePredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generateListOfTermsPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_SUBTYPE_ID, filter.getObjectSubType(), true, ValueConvert.YES);
    }
}
