package bg.duosoft.bpo.registers.entity.ipobject;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class EIpSingleDesignLocarnoId implements Serializable {
    @Column(name = "single_design_id")
    private String singleDesignId;

    @Column(name = "locarno_class_code")
    @KeywordField(name = "locarnoClassCode", projectable = Projectable.YES, searchable = Searchable.YES)
    private String locarnoClassCode;
}
