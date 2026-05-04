package bg.duosoft.bpo.registers.entity.recordal;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERecordalType;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EStatusMap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.10.2021
 * Time: 12:01
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "recordals", schema = "ip_object_recordals")
public class ERecordal implements BaseEntity<String> {

    @Id
    private String id;

    @Column(name = "ip_object_id")
    private String objectId;

    @Column(name = "recordal_number")
    private String recordalNumber;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "recordal_type_id")
    private ERecordalType recordalType;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "recordal_id")
    private ERecordal parentRecordal;

    @Column(name = "filing_date")
    private LocalDate filingDate;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "invalidation_date")
    private LocalDate invalidationDate;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "status_code", referencedColumnName = "code", updatable = false)}
    )
    @IndexedEmbedded
    private EStatusMap status;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recordal_id", referencedColumnName = "id", updatable = false, insertable = false)
    private List<ERecordalPersons> persons;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "recordal")
    private List<ERecordalDetail> details;

}
