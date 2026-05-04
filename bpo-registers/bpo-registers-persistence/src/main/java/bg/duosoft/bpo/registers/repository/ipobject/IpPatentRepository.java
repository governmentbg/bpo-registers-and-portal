package bg.duosoft.bpo.registers.repository.ipobject;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface IpPatentRepository extends BaseRepository<String, EIpPatent> {

    @Query(value = "SELECT COUNT(*) FROM ip_objects.ip_patent e WHERE e.patent_id = ?1", nativeQuery = true)
    Integer countById(String id);

    @Query(value = "select ip_objects.delete_patent_object(?1)", nativeQuery = true)
    Integer deletePatent(String id);
    @Query("UPDATE EIpPatent p set p.renewalFeesLastPaid = :renewalFeesLastPaid, p.renewalFeesPaidTo = :renewalFeesPaidTo, p.lastPaidYear = :lastPaidYear where p.id = :id")
    @Modifying
    void updateAnnuities(String id, LocalDate renewalFeesLastPaid, LocalDate renewalFeesPaidTo, Integer lastPaidYear);
}
