package bg.duosoft.bpo.common.service.mapper;

import bg.duosoft.bpo.common.util.date.DateUtils;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public class YearStringToLocalDateMapper {

    public String toYearString(LocalDate localDate) {
        return DateUtils.toYear(localDate);
    }

    public LocalDate toLocalDate(String year) {
        return DateUtils.toLocalDate(year);
    }
}
