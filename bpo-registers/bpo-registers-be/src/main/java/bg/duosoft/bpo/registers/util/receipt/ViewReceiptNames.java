package bg.duosoft.bpo.registers.util.receipt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ViewReceiptNames {
    MARK("mark_view_receipt_"),
    GEO_INDICATION("geo_view_receipt_"),
    PATENT("patent_view_receipt_"),
    UTILITY_MODEL("utility_model_view_receipt_"),
    EU_PATENT("eu_patent_view_receipt_"),
    PLANT_BREED("plant_breed_view_receipt_"),
    SPC("spc_view_receipt_"),
    DESIGN("design_view_receipt_"),
    AGENT("agent_view_receipt_"),
    PARTNERSHIP("partnership_view_receipt_");

    private final String prefix;

    public String generateFileNamePdf(String id) {
        return this.prefix + id + ".pdf";
    }
}
