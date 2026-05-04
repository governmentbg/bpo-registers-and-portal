package bg.duosoft.bpo.portal.entity;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "admin_panel", schema = "portal")
public class AdminPanelEntity implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "access_roles")
    private String accessRoles;

    @Column(name = "position")
    private Integer position;

    @Column(name = "url")
    private String url;

    @Column(name = "parent_id")
    private Integer parentId;

    @OneToMany(mappedBy = "parentId")
    @OrderBy("position ASC")
    private List<AdminPanelEntity> children;
}
