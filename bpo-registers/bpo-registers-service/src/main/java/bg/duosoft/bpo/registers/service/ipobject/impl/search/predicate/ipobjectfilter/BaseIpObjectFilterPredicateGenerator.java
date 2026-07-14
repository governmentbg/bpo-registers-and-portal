package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:21
 */
public interface BaseIpObjectFilterPredicateGenerator {
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory factory, IpObjectFilter filter);

    public default String normalizeString(String str) {

        str = str.replace("\"", "")
                .replace("'", "")
                .replace("„", "")
                .replace("*", "\\*")
                .replaceAll("\\s{2,}", " ")  // Fixed: use replaceAll for regex pattern
                .toLowerCase().trim()
                .replaceAll("\\.$", "")
                // Normalize Greek final sigma (ς) to regular sigma (σ) for consistent matching
                // This ensures that searches work regardless of whether the index contains ς or σ
                .replace("ς", "σ");
        return str;
    }
}
