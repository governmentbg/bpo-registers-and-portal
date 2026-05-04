package bg.duosoft.bpo.publik.core.dto.common;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.AttachmentTypeDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class FileStoreEntryDTO implements Serializable {
    private Integer id;
    private String description;
    private FileStoreEntryBaseDTO fileStoreEntry;
    private AttachmentTypeDTO type;
}
