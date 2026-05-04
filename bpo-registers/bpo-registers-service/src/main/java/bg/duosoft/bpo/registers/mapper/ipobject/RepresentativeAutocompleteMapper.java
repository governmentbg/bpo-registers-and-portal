package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.autocomplete.RepresentativeAutocompleteResultDTO;
import bg.duosoft.bpo.registers.nonentity.autocomplete.RepresentativeAutocomplete;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.03.2024
 * Time: 18:39
 */
@Mapper(componentModel = "spring")
public abstract class RepresentativeAutocompleteMapper extends BaseObjectMapper<RepresentativeAutocomplete, RepresentativeAutocompleteResultDTO> {
}
