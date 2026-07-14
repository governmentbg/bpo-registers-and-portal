package bg.duosoft.bpo.registers.dto.seniority;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;

import java.time.LocalDate;

/**
 * User: ggeorgiev
 * Date: 09.02.2026
 * Time: 17:40
 */
@Data
public class SeniorityBgDTO implements BaseDTO<Integer> {
    private Integer id;
    private String eutmIdappli;
    private LocalDate eutmAppdate;
    private LocalDate eutmRegdate;
    private String eutmDeno;
    private String idappli;
    private String idmark;
    private String idappliSt;
    private Integer senioritySourceId;
    private String objectId;
}
