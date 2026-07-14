package bg.duosoft.bpo.registers.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicationFilterDetailsDTO {
    private String publicationYear;
    private String publicationNumber;
    private Integer publicationSection;
    private FromToFilterDetailsDTO<LocalDate> publicationDate;
}
