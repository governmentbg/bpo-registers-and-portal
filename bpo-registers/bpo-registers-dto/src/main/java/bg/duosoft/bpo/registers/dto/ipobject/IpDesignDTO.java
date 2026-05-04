package bg.duosoft.bpo.registers.dto.ipobject;

import bg.duosoft.bpo.common.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 16:58
 */
@Data
public class IpDesignDTO implements IpObjectBaseDTO, BaseDTO<String> {

    private String id;
    private String applicationComment;
    private LocalDate publicationDate;
    private LocalDate effectiveDate;
    private LocalDate defermentExpirationDate;
    private IpObjectDTO ipObject;
    @JsonManagedReference
    private List<IpSingleDesignDTO> singleDesigns;

    public boolean isDeferredPublication() {
        return (getDefermentExpirationDate() != null && getDefermentExpirationDate().isAfter(LocalDate.now())) || Objects.equals(getIpObject().getStatus().getDsViewStatusMap().getDeferredPublication(), 1);
    }
}
