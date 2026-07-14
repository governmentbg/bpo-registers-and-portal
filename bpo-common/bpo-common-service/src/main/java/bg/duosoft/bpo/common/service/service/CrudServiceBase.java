package bg.duosoft.bpo.common.service.service;

import bg.duosoft.bpo.common.dto.BaseDTO;

import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 03.10.2022
 * Time: 15:34
 */
public interface CrudServiceBase<ID extends Serializable, DTO extends BaseDTO<ID>> {
    public List<DTO> getAll();

    public DTO getById(ID objectId);

    public DTO create(DTO dto);

    public DTO update(DTO dto);

    public void delete(ID objectId);
}
