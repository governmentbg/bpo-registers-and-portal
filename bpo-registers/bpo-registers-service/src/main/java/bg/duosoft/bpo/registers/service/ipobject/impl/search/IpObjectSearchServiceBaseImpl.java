package bg.duosoft.bpo.registers.service.ipobject.impl.search;

import bg.duosoft.bpo.common.dto.filter.BaseFilterDTO;
import bg.duosoft.bpo.registers.service.search.SearchServiceBase;
import bg.duosoft.bpo.common.util.integer.IntegerUtils;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.entity.ipobject.*;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchFields;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchSortUtil;
import bg.duosoft.bpo.publik.core.service.nomenclature.StatusMapService;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.query.dsl.SearchQueryOptionsStep;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.mapper.orm.scope.SearchScope;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 09.02.2024
 * Time: 14:26
 */
public abstract class IpObjectSearchServiceBaseImpl extends SearchServiceBase {
    @Autowired
    private IpObjectSearchSortUtil objectSearchSortUtil;
    @Autowired
    private StatusMapService statusMapService;

    protected PageImpl<IpObjectSearchResult> searchIpObjects(SearchSession session, List<Class<? extends IpObjectBase>> classes, SearchScope<IpObjectBase> scope,
                                                             SearchPredicateFactory qb, List<Optional<SearchPredicate>> queryParts, BaseFilterDTO filter) {
        SearchPredicate topLevelPredicate = qb.bool(b -> {
            queryParts
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(p -> b.must(p));
        }).toPredicate();
        SearchSortFactory sf = scope.sort();
        Map<String, String> customSearchParams = null;
        if (IpObjectSearchSortUtil.SORT_COLUMN_STATUS.equals(filter.getOrderBy())) {
            customSearchParams = statusMapService.getBpoOnlineStatusNamesMap().entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().toLowerCase()));
        }

        SearchQueryOptionsStep<?, List<?>, SearchLoadingOptionsStep, ?, ?> search = session
                .search(classes)
                .select(f -> f.composite(
                        f.field(IpObjectSearchFields.FIELD_OBJECT_OBJECT_ID, String.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_OBJECT_TYPE, String.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_SUBTYPE_ID, String.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_FILE_ID, String.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_FILING_DATE, LocalDate.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_REGISTRATION_NUMBER_TEXT, String.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_MAIN_OWNER_NAME, String.class, ValueConvert.NO),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_TITLE, String.class),
                        f.field(IpObjectSearchFields.FIELD_MARK_MARK_KIND, String.class),
                        f.field(IpObjectSearchFields.FIELD_IMG, Boolean.class, ValueConvert.NO),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_REGISTRATION_DATE, LocalDate.class),
                        f.field(IpObjectSearchFields.FIELD_OBJECT_STATUS_ID, String.class),
                        f.field(IpObjectSearchFields.FIELD_MARK_NICE_CLASSES, Integer.class).multi()
                ))
                .where(topLevelPredicate);
        getSort(sf, filter, objectSearchSortUtil, customSearchParams).ifPresent(s -> search.sort(s));


        //TODO:Pageable!!!!
        Pageable pageable = getPage(filter);

//        if (pageable.isPaged()) {
//            jpaQuery.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
//            jpaQuery.setMaxResults(pageable.getPageSize());
//        }


        SearchResult<List<?>> resultList = pageable.isUnpaged() ? search.fetchAll() : search.fetch(pageable.getPageSize() * pageable.getPageNumber(), pageable.getPageSize());

        List<IpObjectSearchResult> ipos = new ArrayList<>(resultList.hits().size());
        for (List<?> obj : resultList.hits()) {

            int arg = 0;
            String objectId = (String) obj.get(arg++);
            String objectType = (String) obj.get(arg++);
            String objectSubtype = (String) obj.get(arg++);
            Integer fileId = IntegerUtils.parseInteger((String) obj.get(arg++), null);
            LocalDate filingDate = (LocalDate) obj.get(arg++);
            String registrationNbr = (String) obj.get(arg++);
            String mainOwnerName = (String) obj.get(arg++);
            String title = (String) obj.get(arg++);
            String markKind = (String) obj.get(arg++);
            Boolean img = (Boolean) obj.get(arg++);
            LocalDate registrationDate = (LocalDate) obj.get(arg++);
            String status = (String) obj.get(arg++);
            List<Integer> niceClasses = (List<Integer>) obj.get(arg++);

//            LuceneDocumentReference document = (LuceneDocumentReference) obj.get(arg++);
//            List<Integer> niceClasses = getFieldValues(document, FIELD_MARK_NICE_CLASSES).stream().map(r -> Integer.parseInt(r)).collect(Collectors.toList());

            IpObjectSearchResult res = IpObjectSearchResult.builder().
                    objectId(objectId)
                    .objectType(objectType)
                    .objectSubtype(objectSubtype)
                    .filingNumber(fileId)
                    .filingDate(filingDate)
                    .registrationNbr(registrationNbr)
                    .registrationDate(registrationDate)
                    .status(status)
                    .statusName(Objects.nonNull(status) ? statusMapService.getStatusNameByBackofficeCode(status) : null)
                    .niceClasses(niceClasses)
                    .title(title)
                    .hasImg(img == null ? false : img)
                    .mainOwner(mainOwnerName)
                    .markKind(markKind)
                    .build();
            ipos.add(res);
        }
        return new PageImpl<>(ipos, pageable, resultList.total().hitCount());
    }
}
