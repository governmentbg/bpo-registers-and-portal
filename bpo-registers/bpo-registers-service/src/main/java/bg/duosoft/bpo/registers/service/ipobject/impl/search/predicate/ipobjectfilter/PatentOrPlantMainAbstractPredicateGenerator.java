package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:32
 */
class PatentOrPlantMainAbstractPredicateGenerator extends StringSearchTypePredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generateQuery(qb, filter.getAbstractSearchType(), filter.getAbstractText(), IpObjectSearchFields.FIELD_PATENT_OR_PLANT_MAIN_ABSTRACT_CUSTOM, IpObjectSearchFields.FIELD_PATENT_OR_PLANT_MAIN_ABSTRACT, IpObjectSearchFields.FIELD_PATENT_OR_PLANT_MAIN_ABSTRACT_CUSTOM);
    }
}
