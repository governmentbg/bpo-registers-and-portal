package bg.duosoft.bpo.registers.repository.ipobject.custom;

import bg.duosoft.bpo.registers.entity.ipobject.EIpObject;
import bg.duosoft.bpo.registers.nonentity.filter.EIpObjectFilter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.11.2021
 * Time: 15:34
 */
public interface IpObjectRepositoryCustom {

    List<EIpObject> filterObjects(EIpObjectFilter filter);

    Integer countObjects(EIpObjectFilter filter);
}
