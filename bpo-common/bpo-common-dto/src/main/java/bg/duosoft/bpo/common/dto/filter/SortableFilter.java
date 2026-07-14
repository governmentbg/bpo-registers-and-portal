package bg.duosoft.bpo.common.dto.filter;

public interface SortableFilter {
    String ASC_ORDER = "ASC";
    String DESC_ORDER = "DESC";

    String getOrder();

    String getOrderBy();
}
