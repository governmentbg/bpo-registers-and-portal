package bg.duosoft.bpo.registers.dto.ipobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class IpSingleDesignDrawingId implements Serializable {
    private String singleDesignId;

    private Integer drawingNbr;

}
