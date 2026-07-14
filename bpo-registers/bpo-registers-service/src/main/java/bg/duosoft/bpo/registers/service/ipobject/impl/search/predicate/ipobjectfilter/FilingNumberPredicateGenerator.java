package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_OBJECT_FILE_ID;
import static bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields.FIELD_SINGLE_DESIGN_FILE_ID;


/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 10:35
 */
class FilingNumberPredicateGenerator extends StringToIntFromToPredicateGenerator {
    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        if (!ObjectUtils.isEmpty(filter.getFilingNumberFrom()) || !ObjectUtils.isEmpty(filter.getFilingNumberTo())) {
            String from = splitFilingNumber(filter.getFilingNumberFrom());
            String to = splitFilingNumber(filter.getFilingNumberTo());
            if ((from != null && from.contains("-")) || (to != null && to.contains("-"))) {
                //it's design  - searching by the design number
                from = from == null ? null : from.replace("-", "");
                to = to == null ? null : to.replace("-", "");
                return generateQuery(qb, FIELD_SINGLE_DESIGN_FILE_ID, from, to, ValueConvert.NO);
            } else {
                return generateQuery(qb, FIELD_OBJECT_FILE_ID, from, to, ValueConvert.NO);
            }
        }
        return Optional.empty();
    }
    private String splitFilingNumber(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return null;
        }
        String[] parts = str.split("/");
        if (parts.length == 4) {
            return parts[3];
        } else if (parts.length == 1) {
            return str;
        } else {//unknown number
            throw new RuntimeException("Unknown filing number format for " + str);
        }
    }
}
