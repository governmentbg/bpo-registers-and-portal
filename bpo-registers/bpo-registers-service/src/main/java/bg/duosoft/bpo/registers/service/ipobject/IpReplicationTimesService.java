package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.IpReplicationTimeDTO;

import java.util.List;

public interface IpReplicationTimesService {
    List<IpReplicationTimeDTO> getNonExportedMarkReplicationTimes();

    void saveAll(List<IpReplicationTimeDTO> entries);

    void setExportedTrueForAll();
}
