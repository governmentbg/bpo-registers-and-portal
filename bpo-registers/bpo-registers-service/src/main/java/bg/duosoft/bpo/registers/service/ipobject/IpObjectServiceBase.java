package bg.duosoft.bpo.registers.service.ipobject;


import bg.duosoft.bpo.registers.dto.ipobject.IpObjectBaseDTO;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 08.04.2022
 * Time: 11:02
 */
public interface IpObjectServiceBase<D extends IpObjectBaseDTO> {
    public D getById(String id, boolean forPublicRegister);
    public List<D> getByIds(List<String> ids, boolean forPublicRegister);
}
