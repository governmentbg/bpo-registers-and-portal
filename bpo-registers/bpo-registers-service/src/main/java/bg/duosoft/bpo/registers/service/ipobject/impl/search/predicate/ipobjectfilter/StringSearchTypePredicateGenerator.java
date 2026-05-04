package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.getTermPredicate;
import static bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.BasePredicateUtils.getWildcardPredicate;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 16:31
 */
abstract class StringSearchTypePredicateGenerator implements BaseIpObjectFilterPredicateGenerator {
    protected Optional<SearchPredicate> generateQuery(SearchPredicateFactory qb, TextSearchType searchType, String searchString, String containsWordsField, String wholeWordsField, String exactlyWordsField) {
        if (!ObjectUtils.isEmpty(searchString)) {
            if (searchType == TextSearchType.CONTAINS_WORDS) {
                searchString = normalizeString(searchString);

                String[] splitted = searchString.split(" ");


                PredicateFinalStep res = qb.bool(f -> {
                    for (String str : splitted) {
                        SearchPredicate query = getWildcardPredicate(
                                qb,
                                containsWordsField,
                                "*" + str + "*", true);
                        f.must(query);
                    }
                });

                return Optional.of(res.toPredicate());

            } else if (searchType == TextSearchType.WHOLE_WORDS) {
                searchString = normalizeString(searchString);


                SearchPredicate query = getTermPredicate(
                        qb,
                        wholeWordsField,
                        Arrays.stream(searchString.split(" ")).toList(),
                        false,
                        ValueConvert.YES);
                return Optional.of(query);
            } else if (searchType == TextSearchType.EXACTLY) {
                searchString = normalizeString(searchString);

                SearchPredicate query = getTermPredicate(
                        qb,
                        exactlyWordsField,
                        searchString,
                        ValueConvert.YES);

                return Optional.of(query);
            }
        }
        return Optional.empty();
    }
}
