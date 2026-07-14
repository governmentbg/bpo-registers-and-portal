package bg.duosoft.bpo.registers.util.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SearchReceiptNames {
    MARK("mark_search_receipt"),
    GEO_INDICATION("geo_search_receipt"),
    PATENT("patent_search_receipt"),
    UTILITY_MODEL("utility_model_search_receipt"),
    EU_PATENT("eu_patent_search_receipt"),
    PLANT_BREED("plant_breed_search_receipt"),
    SPC("spc_search_receipt"),
    DESIGN("design_search_receipt"),
    COMBINED("combined_search_receipt"),
    AGENT("agent_search_receipt"),
    PARTNERSHIP("partnership_search_receipt"),
    PERSON_OBJECT_RELATIONS("person_object_relations_receipt"),
    LEGAL_DECISION("legal_decision_search_receipt");

    private final String prefix;

    public String generateFileNamePdf() {
        return this.prefix + ".pdf";
    }

    public String generateFileNameXlsx() {
        return this.prefix + ".xlsx";
    }
}
