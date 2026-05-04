package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ECountry;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_person_addresses", schema = "ip_objects")
@EqualsAndHashCode
public class EIpPersonAddress implements BaseEntity<Integer> {

    @Id
    @Column(name = "person_id")
    private Integer id;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "residence_country_code")
    private ECountry residenceCountryCode;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("address")
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", updatable = false, insertable = false)
    @EqualsAndHashCode.Exclude
    private EIpPerson person;
}