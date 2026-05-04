package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpFtpFileUpload;
import bg.duosoft.bpo.registers.entity.ipobject.EIpFtpFileUploadStatus;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 08.02.2022
 * Time: 11:56
 */
public interface IpFtpFileUploadStatusRepository extends BaseRepository<Integer, EIpFtpFileUploadStatus> {
    @Query("select s from EIpFtpFileUploadStatus s where s.status = 0")
    public List<EIpFtpFileUploadStatus> getNotImported();
}
