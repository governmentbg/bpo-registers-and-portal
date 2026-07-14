package bg.duosoft.bpo.registers.mapper.recordal;

import bg.duosoft.bpo.publik.core.dto.nomenclature.RecordalDetailTypeDTO;
import bg.duosoft.bpo.registers.dto.recordal.RecordalDetailDTO;
import bg.duosoft.bpo.registers.entity.recordal.ERecordal;
import bg.duosoft.bpo.registers.dto.recordal.RecordalDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.entity.recordal.ERecordalPersons;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.StatusMapMapper;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 15:34
 */
@Mapper(componentModel = "spring", uses = {
        RecordalTypeMapper.class,
        RecordalDetailMapper.class,
        StatusMapMapper.class,
})
public abstract class RecordalMapper extends BaseObjectMapper<ERecordal, RecordalDTO> {

    @AfterMapping
    protected void afterToDto(ERecordal source, @MappingTarget RecordalDTO target) {
        convertPersonsToDetails(source,target);
    }


    private void convertPersonsToDetails(ERecordal source, RecordalDTO target){
        if (CollectionUtils.isEmpty(target.getDetails())){
            target.setDetails(new ArrayList<>());
        }

        if (!CollectionUtils.isEmpty(source.getPersons())){
            source.getPersons().forEach(person->{
                RecordalDetailDTO detail = target.getDetails().stream().filter(r->r.getType().getId().equals(person.getRole().getId())).findFirst().orElse(null);
                if (Objects.nonNull(detail)){
                    detail.setDescription(detail.getDescription().concat(", ").concat(person.getPerson().getName()));
                }else{
                    target.getDetails().add(getRecordalDetailDTO(person));
                }
            });
        }
    }

    private static @NotNull RecordalDetailDTO getRecordalDetailDTO(ERecordalPersons person) {
        RecordalDetailDTO detail = new RecordalDetailDTO();
        RecordalDetailTypeDTO detailType = new RecordalDetailTypeDTO();
        detailType.setId(person.getRole().getId());
        detailType.setDescription(person.getRole().getDescription());
        detailType.setDescriptionEn(person.getRole().getDescriptionEn());
        detail.setType(detailType);
        detail.setOrderBy(person.getPersonOrder());
        detail.setDescription(person.getPerson().getName());
        return detail;
    }
}
