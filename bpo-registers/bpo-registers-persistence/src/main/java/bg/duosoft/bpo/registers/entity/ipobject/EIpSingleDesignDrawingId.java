package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class EIpSingleDesignDrawingId implements Serializable {
    @Column(name = "single_design_id")
    private String singleDesignId;

    @Column(name = "drawing_nbr")
    private Integer drawingNbr;

}
