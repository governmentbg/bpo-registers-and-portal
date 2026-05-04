package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate;

import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.predicate.dsl.TermsPredicateFieldMoreStep;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * User: ggeorgiev
 * Date: 29.04.2022
 * Time: 17:45
 */
public class BasePredicateUtils {

    public static SearchPredicate getWildcardPredicate(SearchPredicateFactory pf, String field, String searchStr, boolean toLowerCase) {
        return pf.wildcard().field(field).matching(toLowerCase ? searchStr.toLowerCase() : searchStr).toPredicate();
    }

    public static SearchPredicate getTermPredicate(SearchPredicateFactory pf, String field, String queryStr, ValueConvert valueConvert) {
        return getTermPredicate(pf, field, Arrays.asList(queryStr), false, valueConvert);
    }

    public static SearchPredicate getTermPredicate(SearchPredicateFactory pf, String field, List<String> queryStr, boolean anyMatch, ValueConvert valueConvert) {
        TermsPredicateFieldMoreStep<?, ?> step = pf
                .terms()
                .field(field);
        return anyMatch ? step.matchingAny(queryStr, valueConvert).toPredicate() : step.matchingAll(queryStr, valueConvert).toPredicate();
    }

    public static Optional<SearchPredicate> generateFromToPredicate(SearchPredicateFactory pf, String field, Object from, Object to, ValueConvert valueConvert) {
        if (!ObjectUtils.isEmpty(from) || !ObjectUtils.isEmpty(to)) {
            SearchPredicate predicate;
            if (from != null && to != null) {
                predicate = getRangePredicate(pf,
                        field,
                        from,
                        to,
                        valueConvert);
            } else if (from != null) {
                predicate = getAbovePredicate(pf,
                        field,
                        from,
                        valueConvert);
            } else {//toNbr != null
                predicate = getBelowPredicate(pf,
                        field,
                        to,
                        valueConvert);
            }
            return Optional.of(predicate);
        }
        return Optional.empty();
    }

    public static Optional<SearchPredicate> generateListOfTermsPredicate(SearchPredicateFactory qb, String field, List<String> terms, boolean anyMatch, ValueConvert valueConvert) {
        if (!CollectionUtils.isEmpty(terms)) {
            SearchPredicate res = BasePredicateUtils.getTermPredicate(qb, field, terms, anyMatch, valueConvert);
            return Optional.of(res);
        }
        return Optional.empty();
    }

    private static SearchPredicate getRangePredicate(SearchPredicateFactory pf, String field, Object from, Object to, ValueConvert valueConvert) {
        if (to instanceof Date) {
            Calendar c = Calendar.getInstance();
            c.setTime((Date) to);
            c.add(Calendar.DATE, 1);
            c.add(Calendar.MINUTE, -1);
            to = c.getTime();
        }

        if (to instanceof java.sql.Date) {
            Calendar c = Calendar.getInstance();
            c.setTime((java.sql.Date) to);
            c.add(Calendar.DATE, 1);
            c.add(Calendar.MINUTE, -1);
            to = c.getTime();
        }

        if (to instanceof Calendar) {
            ((Calendar) to).add(Calendar.DATE, 1);
            ((Calendar) to).add(Calendar.MINUTE, -1);
        }

        return pf
                .range()
                .field(field)
                .between(from, to, valueConvert)
                .toPredicate();
    }

    private static SearchPredicate getBelowPredicate(SearchPredicateFactory pf, String field, Object to, ValueConvert valueConvert) {
        if (to instanceof Date) {
            Calendar c = Calendar.getInstance();
            c.setTime((Date) to);
            c.add(Calendar.DATE, 1);
            c.add(Calendar.MINUTE, -1);
            to = c.getTime();
        }

        if (to instanceof java.sql.Date) {
            Calendar c = Calendar.getInstance();
            c.setTime((java.sql.Date) to);
            c.add(Calendar.DATE, 1);
            c.add(Calendar.MINUTE, -1);
            to = c.getTime();
        }

        if (to instanceof Calendar) {
            ((Calendar) to).add(Calendar.DATE, 1);
            ((Calendar) to).add(Calendar.MINUTE, -1);
        }

        return pf
                .range()
                .field(field)
                .atMost(to, valueConvert)
                .toPredicate();
    }

    private static SearchPredicate getAbovePredicate(SearchPredicateFactory pf, String field, Object from, ValueConvert valueConvert) {
        return pf
                .range()
                .field(field)
                .atLeast(from, valueConvert)
                .toPredicate();
    }

    public static SearchPredicate matchAny(SearchPredicateFactory f, String field, Collection<?> values) {
        return f.bool(b -> {
                    for(Object toMatch : values) {
                        b.should(f.match().field(field).matching(toMatch));
                    }
                })
                .toPredicate();
    }
}
