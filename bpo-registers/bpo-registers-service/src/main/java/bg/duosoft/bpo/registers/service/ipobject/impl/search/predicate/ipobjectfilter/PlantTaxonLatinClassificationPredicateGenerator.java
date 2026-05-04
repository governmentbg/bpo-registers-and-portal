package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import java.util.Optional;


/**
 * User: ggeorgiev
 * Date: 05.05.2022
 * Time: 11:52
 */
public class PlantTaxonLatinClassificationPredicateGenerator extends PlantTaxonBgClassificationPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        return generatePredicate(qb, IpObjectSearchFields.PLANT_TAXON_LATIN_CLASSIFICATION, filter.getLatinClassification());
    }
}
