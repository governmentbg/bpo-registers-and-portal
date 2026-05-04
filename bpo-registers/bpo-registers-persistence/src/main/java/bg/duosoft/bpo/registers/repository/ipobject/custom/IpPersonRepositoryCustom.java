package bg.duosoft.bpo.registers.repository.ipobject.custom;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.nonentity.filter.EIpPersonFilter;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 15:33
 */
public interface IpPersonRepositoryCustom {
    public List<EIpPerson> findPersons(EIpPersonFilter filter);
    public Integer countPersons(EIpPersonFilter filter);
    public List<EIpPerson> getMatchingPersons(EIpPerson person, int maxCount, boolean strongMatch);
}
