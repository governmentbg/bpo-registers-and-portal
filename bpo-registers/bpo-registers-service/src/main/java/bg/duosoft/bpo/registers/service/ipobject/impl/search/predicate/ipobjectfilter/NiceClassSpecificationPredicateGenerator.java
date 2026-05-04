package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

public class NiceClassSpecificationPredicateGenerator extends StringSearchTypePredicateGenerator {

    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generateQuery(qb, filter.getNiceClassSpecificationSearchType(), filter.getNiceClassSpecificationText(), IpObjectSearchFields.FIELD_MARK_NICE_CLASSES_SPECIFICATION_CUSTOM, IpObjectSearchFields.FIELD_MARK_NICE_CLASSES_SPECIFICATION, IpObjectSearchFields.FIELD_MARK_NICE_CLASSES_SPECIFICATION_CUSTOM);
    }

}
