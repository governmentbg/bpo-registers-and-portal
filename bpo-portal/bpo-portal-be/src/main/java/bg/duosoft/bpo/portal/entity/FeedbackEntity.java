package bg.duosoft.bpo.portal.entity;

import static bg.duosoft.bpo.common.service.validator.ValidatorConstants.*;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.portal.enums.MessageType;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "feedback", schema = "portal", catalog = "bpo_registers")
public class FeedbackEntity implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = MAX_INPUT_LENGTH_XXS)
    private String firstName;

    @Column(name = "last_name", length = MAX_INPUT_LENGTH_XXS)
    private String lastName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "email", length = MAX_INPUT_LENGTH_S)
    private String email;

    @ManyToOne
    @JoinColumn(name = "object_type", referencedColumnName = "code")
    private EObjectType objectType;

    @Column(name = "application_number")
    private String applicationNumber;

    @NotNull
    @Column(name = "message_type")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackAttachmentEntity> attachments;

}
