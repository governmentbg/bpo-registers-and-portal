package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_single_design_locarno", schema = "ip_objects")
public class EIpSingleDesignLocarno implements BaseEntity<EIpSingleDesignLocarnoId> {
    @EmbeddedId
    @IndexedEmbedded
    private EIpSingleDesignLocarnoId id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EIpSingleDesignLocarno that = (EIpSingleDesignLocarno) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
