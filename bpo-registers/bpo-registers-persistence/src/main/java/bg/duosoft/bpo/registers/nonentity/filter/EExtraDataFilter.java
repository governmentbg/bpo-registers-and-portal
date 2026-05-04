package bg.duosoft.bpo.registers.nonentity.filter;

import lombok.Data;

@Data
public class EExtraDataFilter {
    private Integer page;
    private Integer pageSize;
    private String order;
    private String orderBy;
}
