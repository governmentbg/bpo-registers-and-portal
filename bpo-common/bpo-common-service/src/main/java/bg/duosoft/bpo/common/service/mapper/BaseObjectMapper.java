package bg.duosoft.bpo.common.service.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;

import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

public abstract class BaseObjectMapper<E, D> {

    public abstract D toDto(E e);

    @InheritConfiguration(name = "toDto")
    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    public abstract List<D> toDtoList(List<E> eList);

    @InheritInverseConfiguration(name = "toDto")
    public abstract E toEntity(D d);

    @InheritInverseConfiguration(name = "toDtoList")
    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    public abstract List<E> toEntityList(List<D> dList);
}
