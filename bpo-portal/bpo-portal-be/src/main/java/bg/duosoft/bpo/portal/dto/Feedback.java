package bg.duosoft.bpo.portal.dto;

import bg.duosoft.bpo.common.dto.BaseDTO;
import bg.duosoft.bpo.portal.enums.MessageType;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;
import lombok.Data;

import java.util.List;

@Data
public class Feedback implements BaseDTO<Integer> {
    private Integer id;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private String objectType;
    private String applicationNumber;
    private MessageType messageType;
    private List<FileStoreEntryDTO> attachments;
}
