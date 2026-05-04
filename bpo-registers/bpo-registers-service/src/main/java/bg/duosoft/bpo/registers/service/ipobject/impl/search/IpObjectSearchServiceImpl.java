package bg.duosoft.bpo.registers.service.ipobject.impl.search;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.entity.ipobject.*;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter.IpObjectFilterPredicateGeneratorGetter;
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


@Service
@Transactional
public class IpObjectSearchServiceImpl extends IpObjectSearchServiceBaseImpl implements IpObjectSearchService {

    @Autowired
    private EntityManager bpoRegistersEntityManager;

    @Override
    public Page<IpObjectSearchResult> search(IpObjectFilter filter) {
        SearchSession session = Search.session(bpoRegistersEntityManager);
        List<Class<? extends IpObjectBase>> classes = Arrays.asList(EIpMark.class, EIpPatent.class, EIpPlant.class, EIpDesign.class);
        SearchScope<IpObjectBase> scope = session.scope(classes);
        SearchPredicateFactory qb = scope.predicate();

        List<Optional<SearchPredicate>> queryParts = new ArrayList<>();
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getApplicantPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getObjectTypePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getObjectNamePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getFilingNumberPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getFilingDatePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getRegistrationNumberPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getExpirationDatePredicateGenerator().generatePredicate(qb, filter));
//        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getEntitlementDatePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getObjectSubtypePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getRepresentativeNamePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getRepresentativeCodePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getPublicationPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getPriorityPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getObjectStatusPredicateGenerator().generatePredicate(qb, filter));


        //mark related fields

        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getViennaClassPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getMarkKindPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getNiceClassPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getNiceClassSpecificationPredicateGenerator().generatePredicate(qb, filter));
        //end of mark related fields

        //patent related fields

        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getInventorPersonNamePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getIpcClassPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getIpcCodePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getCpcClassPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getCpcCodePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getPatentMainAbstractPredicateGenerator().generatePredicate(qb, filter));

        //plant related fields
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getPlantTaxonBgClassificationPredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getPlantTaxonLatinClassificationPredicateGenerator().generatePredicate(qb, filter));

        //design related fields
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getLocarnoClassCodePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getSingleDesignNamePredicateGenerator().generatePredicate(qb, filter));
        queryParts.add(IpObjectFilterPredicateGeneratorGetter.getSingleDesignVerbalElementPredicateGenerator().generatePredicate(qb, filter));
        return searchIpObjects(session, classes, scope, qb, queryParts, filter);

    }
}
