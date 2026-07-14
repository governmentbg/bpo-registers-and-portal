package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpDesign;
import bg.duosoft.bpo.registers.dto.ipobject.IpDesignDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:18
 */
@Mapper(componentModel = "spring", uses = {
        IpObjectMapper.class,
        IpSingleDesignMapper.class
})
public abstract class IpDesignMapper extends IpObjectBaseMapper<EIpDesign, IpDesignDTO> {

    @AfterMapping
    protected void afterMapping(EIpDesign source, @MappingTarget IpDesignDTO target, @Context boolean forRegister) {
        if (target.getSingleDesigns() != null) {
            target.getSingleDesigns().forEach(sd -> sd.setMainDesign(target));
        }

        if (forRegister) {
            if (target.isDeferredPublication()) {
                IpObjectDTO ipObject = target.getIpObject();
                ipObject.setTitle("НЕПУБЛИКУВАНО");
                ipObject.setTitleEn("NOT PUBLISHED");
                target.getSingleDesigns().forEach(d -> {
                    d.setName("НЕПУБЛИКУВАНО");
                    d.setNameEn("NOT PUBLISHED");
                });
                target
                        .getSingleDesigns()
                        .stream()
                        .map(d -> d.getDrawings())
                        .filter(Objects::nonNull)
                        .flatMap(m -> m.stream())
                        .forEach(d -> d.getAttachment().setFileLocation("no-image.jpg"));
            }
            if (!ObjectUtils.isEmpty(target.getSingleDesigns()) && !ObjectUtils.isEmpty(source.getSingleDesigns())) {
                Set<String> publishedSingleDesignIds = source.getSingleDesigns().stream().filter(sd -> Objects.equals(sd.getPublished(), 1)).map(r -> r.getId()).collect(Collectors.toSet());
                target.setSingleDesigns(target.getSingleDesigns().stream().filter(o -> publishedSingleDesignIds.contains(o.getId())).toList());
            }

        }
    }
    @AfterMapping
    protected void afterMapping(IpDesignDTO source, @MappingTarget EIpDesign target) {
        if (target.getSingleDesigns() != null) {
            target.getSingleDesigns().forEach(sd -> sd.setMainDesign(target));
        }
    }

}
