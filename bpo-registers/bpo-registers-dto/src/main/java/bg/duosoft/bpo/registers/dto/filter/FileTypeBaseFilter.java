package bg.duosoft.bpo.registers.dto.filter;

import lombok.Data;

import java.util.List;

@Data
public class FileTypeBaseFilter {
    private List<String> fileTypes;
}
