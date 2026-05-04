package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_agent_addresses", schema = "ip_objects")
@EqualsAndHashCode
public class EIpAgentAddress implements BaseEntity<Integer> {

    @Id
    @Column(name = "person_id")
    private Integer id;


    @Column(name = "city_name_en")
    private String cityNameEn;

    @Column(name = "address_street_en")
    private String addressStreetEn;

    @Column(name = "website")
    private String website;

    @Column(name = "address_street_ca")
    private String addressStreetCa;

    @Column(name = "address_street_ca_en")
    private String addressStreetCaEn;

    @Column(name = "zip_code_ca")
    private String zipCodeCa;

    @Column(name = "phone_ca")
    private String phoneCa;

    @Column(name = "email_ca")
    private String emailCa;

    @Column(name = "fax_ca")
    private String faxCa;

    @Column(name = "city_name_ca")
    private String cityNameCa;

    @Column(name = "city_name_ca_en")
    private String cityNameCaEn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", updatable = false, insertable = false)
    @MapsId("address")
    @EqualsAndHashCode.Exclude
    private EIpAgent agent;

}
