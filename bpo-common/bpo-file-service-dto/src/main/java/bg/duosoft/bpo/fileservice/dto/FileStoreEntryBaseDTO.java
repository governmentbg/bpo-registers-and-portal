package bg.duosoft.bpo.fileservice.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FileStoreEntryBaseDTO implements Cloneable {
    private String fileId;
    private String fileName;
    private Long fileSize;
    private String contentType;
    private String bucket;
    private String relativePath;
    private String fullPath;
    private byte[] content;
    private Map<String, String> additionalMetadata;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
