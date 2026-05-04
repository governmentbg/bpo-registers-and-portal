package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpSingleDesignReplicationDetail;

import java.util.List;

public interface IpSingleDesignReplicationDetailRepository extends BaseRepository<Integer, EIpSingleDesignReplicationDetail> {
    public List<EIpSingleDesignReplicationDetail> getByMainDesignReplicationDetailId(Integer mainDesignReplicationDetailId);
}
