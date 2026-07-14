package bg.duosoft.bpo.registers.service.ipobject.impl.search;

import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.filter.ObjectAutocompleteFilter;
import bg.duosoft.bpo.registers.entity.ipobject.*;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectAutocompleteService;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.autocomplete.ObjectAutocompletePredicateGeneratorGetter;
import jakarta.persistence.EntityManager;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.scope.SearchScope;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 09.02.2024
 * Time: 14:21
 */
@Service
@Transactional
public class IpObjectAutocompleteServiceImpl extends IpObjectSearchServiceBaseImpl implements IpObjectAutocompleteService {
    @Autowired
    private EntityManager bpoRegistersEntityManager;

    public Page<IpObjectSearchResult> search(ObjectAutocompleteFilter filter) {
        SearchSession session = Search.session(bpoRegistersEntityManager);
        List<Class<? extends IpObjectBase>> classes = Arrays.asList(EIpMark.class, EIpPatent.class, EIpPlant.class, EIpDesign.class);
        SearchScope<IpObjectBase> scope = session.scope(classes);
        SearchPredicateFactory qb = scope.predicate();
        List<Optional<SearchPredicate>> queryParts = new ArrayList<>();
        queryParts.add(ObjectAutocompletePredicateGeneratorGetter.getObjectTypePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(ObjectAutocompletePredicateGeneratorGetter.getFilingNumberPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(ObjectAutocompletePredicateGeneratorGetter.getRegistrationNumberPredicateGenerator().generatePredicate(qb, filter));

        return searchIpObjects(session, classes, scope, qb, queryParts, filter);
    }
}
