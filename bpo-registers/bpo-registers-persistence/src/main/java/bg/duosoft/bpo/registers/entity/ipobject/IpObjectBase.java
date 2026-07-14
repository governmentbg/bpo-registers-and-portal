package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;

/**
 * User: ggeorgiev
 * Date: 29.03.2022
 * Time: 16:50
 */
public interface IpObjectBase extends BaseEntity<String> {
    public EIpObject getIpObject();
}
