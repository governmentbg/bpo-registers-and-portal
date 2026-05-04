package bg.duosoft.bpo.registers.service.search;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.common.service.util.SearchSortUtil;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.sort.SearchSort;
import org.hibernate.search.engine.search.sort.dsl.FieldSortOptionsStep;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.search.sort.dsl.SortThenStep;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchServiceBase {

    protected Optional<SearchSort> getSort(SearchSortFactory sf, BaseFilterDTO filter, SearchSortUtil searchSortUtil,
                                           Map<String, String> customSortParams) {
        String sortColumn = filter.getOrderBy();
        String sortDirection = filter.getOrder();
        if (sortColumn != null) {
            if (!ObjectUtils.isEmpty(customSortParams)) {
                String params = customSortParams.entrySet().stream().map(r -> "\"" + escapeJavascript(r.getKey()) + "\":\"" + escapeJavascript(r.getValue()) + "\"").collect(Collectors.joining(",\n"));
                String script = """
                        {
                        	"_script": {
                        		"type": "string",
                        		"script": {
                        			"source": "params[doc['%s'].value]",
                                    "params": {
                                        %s
                                    }
                        		},
                        		"order": "%s"
                        	}
                        }
                        """.formatted(searchSortUtil.toSearchSortField(sortColumn), params, ObjectUtils.isEmpty(sortDirection) ? "asc" : sortDirection);


                SortThenStep res = sf.extension(ElasticsearchExtension.get())
                        .fromJson(script);
                return Optional.ofNullable(res.toSort());
            } else {
                FieldSortOptionsStep<?, ? extends SearchPredicateFactory> sortFieldContext = sf
                        .field(searchSortUtil.toSearchSortField(sortColumn));
                if (sortColumn != null) {
                    if (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) {
                        sortFieldContext.desc();
                    } else {
                        sortFieldContext.asc();
                    }
                }
                return Optional.ofNullable(sortFieldContext.toSort());
            }


        }
        return Optional.empty();
    }
    private static String escapeJavascript(String input) {
        return input == null ? "" : input.replaceAll("\"", "\\\\\"");
    }

    public Pageable getPage(BaseFilterDTO filter) {
        if (filter.getPage() != null && filter.getPageSize() != null && filter.getPageSize() != 0) {
            return PageRequest.of(filter.getPage(), filter.getPageSize());
        } else {
            return Pageable.unpaged();
        }
    }
}
