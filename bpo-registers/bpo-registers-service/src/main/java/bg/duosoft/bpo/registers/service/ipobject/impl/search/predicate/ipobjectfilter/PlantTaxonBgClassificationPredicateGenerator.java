package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import java.util.Optional;


/**
 * User: ggeorgiev
 * Date: 05.05.2022
 * Time: 11:52
 */
public class PlantTaxonBgClassificationPredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory factory, IpObjectFilter filter) {
        return generatePredicate(factory, IpObjectSearchFields.PLANT_TAXON_BG_CLASSIFICATION, filter.getBgClassification());
    }
    protected Optional<SearchPredicate> generatePredicate(SearchPredicateFactory factory, String field, String searchString) {
        if (ObjectUtils.isEmpty(searchString)) {
            return Optional.empty();
        }

        searchString = normalizeString(searchString);

        String[] splitted = searchString.split(" ");

        PredicateFinalStep res = factory.bool(b -> {
            for (String str : splitted) {
                SearchPredicate predicate = BasePredicateUtils.getWildcardPredicate(
                        factory,
                        field,
                        "*" + str + "*", true);
                b.must(predicate);
            }
        });

        return Optional.ofNullable(res.toPredicate());
    }
}
