package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EAgentSpeciality;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EAgentStatus;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ECountry;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERepresentativeType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_agent", schema = "ip_objects")
@EqualsAndHashCode
public class EIpAgent implements BaseEntity<Integer> {

    @Id
    @Column(name = "person_id")
    private Integer id;

    @Column(name = "agent_code")
    private String agentCode;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "representative_type")
    private ERepresentativeType representativeType;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "agent_speciality")
    private EAgentSpeciality agentSpeciality;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "first_name_en")
    private String firstNameEn;

    @Column(name = "second_name_en")
    private String secondNameEn;

    @Column(name = "last_name_en")
    private String lastNameEn;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "qualif_country_code")
    private ECountry qualifCountryCode;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "speciality_en")
    private String specialityEn;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "agent_status")
    private EAgentStatus agentStatus;

    @Column(name = "represent_persons")
    private String representPersons;

    @OneToOne(mappedBy = "agent", cascade = CascadeType.ALL)
    private EIpAgentAddress address;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("agent")
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", updatable = false, insertable = false)
    @EqualsAndHashCode.Exclude
    private EIpPerson person;

}
