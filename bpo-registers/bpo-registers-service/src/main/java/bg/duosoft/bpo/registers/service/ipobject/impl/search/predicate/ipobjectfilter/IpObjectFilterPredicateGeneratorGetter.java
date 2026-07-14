package bg.duosoft.bpo.registers.service.ipobject.impl.search.predicate.ipobjectfilter;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 12:47
 */

public class IpObjectFilterPredicateGeneratorGetter {
    public static BaseIpObjectFilterPredicateGenerator getViennaClassPredicateGenerator() {
        return new ViennaClassPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getApplicantPredicateGenerator() {
        return new ApplicantPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getObjectTypePredicateGenerator() {
        return new ObjectTypePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getObjectNamePredicateGenerator() {
        return new ObjectNamePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getFilingNumberPredicateGenerator() {
        return new FilingNumberPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getFilingDatePredicateGenerator() {
        return new FilingDatePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getRegistrationNumberPredicateGenerator() {
        return new RegistrationNumberPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getExpirationDatePredicateGenerator() {
        return new ExpirationDatePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getEntitlementDatePredicateGenerator() {
        return new EntitlementDatePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getObjectSubtypePredicateGenerator() {
        return new ObjectSubtypePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getRepresentativeNamePredicateGenerator() {
        return new RepresentativeNamePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getRepresentativeCodePredicateGenerator() {
        return new RepresentativeCodePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getInventorPersonNamePredicateGenerator() {
        return new InventorPersonNamePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getMarkKindPredicateGenerator() {
        return new MarkKindPredicateGenerator();
    }

    public static BaseIpObjectFilterPredicateGenerator getPublicationPredicateGenerator() {
        return new PublicationPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getPriorityPredicateGenerator() {
        return new PriorityPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getIpcClassPredicateGenerator() {
        return new IpcClassPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getIpcCodePredicateGenerator() {
        return new IpcCodePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getCpcClassPredicateGenerator() {
        return new CpcClassPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getCpcCodePredicateGenerator() {
        return new CpcCodePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getObjectStatusPredicateGenerator() {
        return new ObjectStatusPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getPatentMainAbstractPredicateGenerator() {
        return new PatentOrPlantMainAbstractPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getPlantTaxonBgClassificationPredicateGenerator() {
        return new PlantTaxonBgClassificationPredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getPlantTaxonLatinClassificationPredicateGenerator() {
        return new PlantTaxonLatinClassificationPredicateGenerator();
    }

    public static BaseIpObjectFilterPredicateGenerator getNiceClassPredicateGenerator() {
        return new NiceClassPredicateGenerator();
    }

    public static BaseIpObjectFilterPredicateGenerator getNiceClassSpecificationPredicateGenerator() {
        return new NiceClassSpecificationPredicateGenerator();
    }

    public static BaseIpObjectFilterPredicateGenerator getLocarnoClassCodePredicateGenerator() {
        return new LocarnoClassCodePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getSingleDesignNamePredicateGenerator() {
        return new SingleDesignNamePredicateGenerator();
    }
    public static BaseIpObjectFilterPredicateGenerator getSingleDesignVerbalElementPredicateGenerator() {
        return new SingleDesignVerbalElementPredicateGenerator();
    }
}
