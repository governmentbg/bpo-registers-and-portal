package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.publik.core.enums.AgentStatus;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonToIpObjectDTO;
import bg.duosoft.bpo.registers.entity.ipobject.IpObjectBase;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EStatusMap;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectBaseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 08.04.2022
 * Time: 11:09
 */
public abstract class IpObjectBaseMapper<E extends IpObjectBase, D extends IpObjectBaseDTO> {


    public abstract D toDto(E e, @Context boolean forRegister);

    public abstract List<D> toDtoList(List<E> eList, @Context boolean forRegister);

    @InheritInverseConfiguration
    public abstract E toEntity(D d);

    @InheritInverseConfiguration
    public abstract List<E> toEntityList(List<D> dList);

    @AfterMapping
    protected void afterBaseObjectMapping(E entity, @MappingTarget D target, @Context boolean forRegister) {
        if (forRegister) {
            EStatusMap status = entity.getIpObject().getStatus();
            if (status.getShowExpirationDate() != 1) {
                target.getIpObject().setExpirationDate(null);
            }


            List<IpPersonToIpObjectDTO> persons = target
                    .getIpObject()
                    .getPersons()
                    .stream()
                    .filter(this::isVisible)
                    .toList();
            target.getIpObject().setPersons(persons);
        }
    }
    //removing non active agents and patent specialist agents
    private boolean isVisible(IpPersonToIpObjectDTO p) {
        return !Objects.equals(p.getRole().getId(), PersonRole.REPRESENTATIVE.code()) || (!Objects.equals(p.getRepresentativeType().getId(), RepresentativeType.PATENT_SPECIALIST.code())) && (p.getPerson().getAgent() == null || Objects.equals(p.getPerson().getAgent().getAgentStatus().getId(), AgentStatus.ACTIVE.code()));
    }
}
