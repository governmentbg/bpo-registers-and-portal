package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;


import bg.duosoft.bpo.common.dto.filter.TextSearchType;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class IpcCodePredicateGenerator extends StringSearchTypePredicateGenerator {

    @Override
    public Optional<SearchPredicate> generatePredicate(SearchPredicateFactory qb, IpObjectFilter filter) {
        String ipcCodeFormatted = filter.getIpcCode();
        if (StringUtils.hasText(ipcCodeFormatted)) {
            ipcCodeFormatted = ipcCodeFormatted.replaceAll("[^a-zA-Z0-9]", "");
        }
        return generateQuery(qb, TextSearchType.CONTAINS_WORDS, ipcCodeFormatted, IpObjectSearchFields.FIELD_PATENT_IPC_CLASS, IpObjectSearchFields.FIELD_PATENT_IPC_CLASS, IpObjectSearchFields.FIELD_PATENT_IPC_CLASS);
    }

}
