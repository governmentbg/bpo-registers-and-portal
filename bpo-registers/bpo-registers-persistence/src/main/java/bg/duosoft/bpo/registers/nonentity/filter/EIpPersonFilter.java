package bg.duosoft.bpo.registers.nonentity.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 15:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EIpPersonFilter extends EExtraDataFilter {
    private String name;
    private Integer legalType;
}
