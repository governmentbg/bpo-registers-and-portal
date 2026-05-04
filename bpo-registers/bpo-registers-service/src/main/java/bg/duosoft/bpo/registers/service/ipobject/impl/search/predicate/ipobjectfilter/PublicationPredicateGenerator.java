package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.NestedPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 14:43
 */
public class PublicationPredicateGenerator extends FromToPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        List<Optional<SearchPredicate>> parts = new ArrayList<>();


        NestedPredicateClausesStep<?> x = qb.nested("ipObject.publications");

        parts.add(generateQuery(qb, IpObjectSearchFields.FIELD_OBJECT_PUBLICATION_NUMBER, filter.getPublicationNumber(), filter.getPublicationNumber(), ValueConvert.YES));
        parts.add(generateQuery(qb, IpObjectSearchFields.FIELD_OBJECT_PUBLICATION_SECTION, filter.getPublicationSection(), filter.getPublicationSection(), ValueConvert.YES));
        parts.add(generateQuery(qb, IpObjectSearchFields.FIELD_OBJECT_PUBLICATION_YEAR, filter.getPublicationYear(), filter.getPublicationYear(), ValueConvert.YES));
        Optional<SearchPredicate> pubDateFromTo = BasePredicateUtils.generateFromToPredicate(qb, IpObjectSearchFields.FIELD_OBJECT_PUBLICATION_DATE, filter.getPublicationDateFrom(), filter.getPublicationDateTo(), ValueConvert.YES);
        if (pubDateFromTo.isPresent()) {
            parts.add(pubDateFromTo);
        }

        List<SearchPredicate> queries = parts
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        if (queries.size() != 0) {
            queries.forEach(q -> x.add(q));
            return Optional.of(x.toPredicate());
        } else {
            return Optional.empty();
        }


    }
}
