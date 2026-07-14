package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EDrawingViewType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_single_design_drawing", schema = "ip_objects")
public class EIpSingleDesignDrawing implements BaseEntity<EIpSingleDesignDrawingId> {

    @EmbeddedId
    private EIpSingleDesignDrawingId id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "view_type_id", referencedColumnName = "id", updatable = false)}
    )
    private EDrawingViewType viewType;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private EAttachment attachment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
    private EAttachment thumbnail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dsview_picture_id", referencedColumnName = "id")
    private EAttachment dsviewPicture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EIpSingleDesignDrawing that = (EIpSingleDesignDrawing) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
