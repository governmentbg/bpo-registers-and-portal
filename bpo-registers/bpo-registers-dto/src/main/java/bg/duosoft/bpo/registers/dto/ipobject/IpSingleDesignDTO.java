package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectSubtypeDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.StatusMapDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:59
 */
@Data
@ToString(exclude = "mainDesign")
public class IpSingleDesignDTO implements BaseDTO<String> {
    private String id;
    private String name;
    private String nameEn;
    private String verbalElement;
    private String verbalElementEn;
    private ObjectSubtypeDTO designType;
    private StatusMapDTO status;
    private List<IpSingleDesignDrawingDTO> drawings;
    private List<IpSingleDesignLocarnoDTO> locarnos;
    private String alternateKey;
    @JsonBackReference
    private IpDesignDTO mainDesign;
    private String st13;
    private Integer designNumber;
}
