package bg.duosoft.bpo.registers.util.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateNames {
    VIEW_MARK("view/mark.ftl"),
    VIEW_GEO_INDICATION("view/geo.ftl"),
    VIEW_PATENT("view/patent.ftl"),
    VIEW_UTILITY_MODEL("view/utility_model.ftl"),
    VIEW_EU_PATENT("view/eu_patent.ftl"),
    VIEW_PLANT_BREED("view/plant_breed.ftl"),
    VIEW_SPC("view/spc.ftl"),
    VIEW_DESIGN("view/design.ftl"),
    VIEW_AGENT("view/agent.ftl"),
    VIEW_PARTNERSHIP("view/partnership.ftl"),
    SEARCH_MARK("search/mark.ftl"),
    SEARCH_GEO_INDICATION("search/geo.ftl"),
    SEARCH_PATENT("search/patent.ftl"),
    SEARCH_EU_PATENT("search/eu_patent.ftl"),
    SEARCH_UTILITY_MODEL("search/utility_model.ftl"),
    SEARCH_PLANT_BREED("search/plant_breed.ftl"),
    SEARCH_SPC("search/spc.ftl"),
    SEARCH_DESIGN("search/design.ftl"),
    SEARCH_COMBINED("search/combined.ftl"),
    SEARCH_AGENT("search/agent.ftl"),
    SEARCH_PARTNERSHIP("search/partnership.ftl"),
    SEARCH_LEGAL_DECISION("search/decision.ftl");

    private final String templatePath;

}
