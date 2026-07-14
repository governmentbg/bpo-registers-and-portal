package bg.duosoft.bpo.fileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.InputStream;

/**
 * User: ggeorgiev
 * Date: 02.04.2025
 * Time: 15:56
 */
@AllArgsConstructor
@Getter
public class FileStreamResult {
    private final InputStream inputStream;
    private final String contentType;
    private final String fileName;
    private final long contentLength;
}
