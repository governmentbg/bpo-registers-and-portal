package bg.duosoft.bpo.registers.entity.seniority;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * User: ggeorgiev
 * Date: 09.02.2026
 * Time: 17:35
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "seniority_bg", schema = "seniority")
public class ESeniorityBg implements BaseEntity<Integer> {
    @Id
    @Column(name = "seniority_id")
    private Integer id;
    @Column(name = "eutm_idappli")
    private String eutmIdappli;
    @Column(name = "eutm_appdate")
    private LocalDate eutmAppdate;
    @Column(name = "eutm_regdate")
    private LocalDate eutmRegdate;
    @Column(name = "eutm_deno")
    private String eutmDeno;
    @Column(name = "idappli")
    private String idappli;
    @Column(name = "idmark")
    private String idmark;
    @Column(name = "idappli_st")
    private String idappliSt;
    @Column(name = "seniority_source_id")
    private Integer senioritySourceId;
    @Column(name = "object_id")
    private String objectId;
}
