package bg.duosoft.bpo.registers.service.ipobject.impl;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectBaseDTO;
import bg.duosoft.bpo.registers.entity.ipobject.IpObjectBase;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectBaseMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectServiceBase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 08.04.2022
 * Time: 11:43
 */
@Transactional
public abstract class IpObjectServiceBaseImpl<E extends IpObjectBase, D extends IpObjectBaseDTO> implements IpObjectServiceBase<D> {

    public abstract BaseRepository<String, E> getRepository();

    public abstract IpObjectBaseMapper<E, D> getObjectMapper();

    public D getById(String id, boolean forPublicRegister) {
        Optional<E> res = getRepository().findById(id);
        if (res.isEmpty() ||  !published(forPublicRegister, res.get())) {
            return null;
        }
        return getObjectMapper().toDto(res.get(), forPublicRegister);
    }


    @Override
    public List<D> getByIds(List<String> ids, boolean forPublicRegister) {
        List<E> objects = getRepository().findAllById(ids);
        return objects == null ? null : objects.stream().filter(r -> published(forPublicRegister, r)).map(r -> getObjectMapper().toDto(r, forPublicRegister)).toList();
    }
    private boolean published(boolean forPublicRegister, E e) {
        return !forPublicRegister || (forPublicRegister && e.getIpObject().getPublished() != 0);
    }
}
