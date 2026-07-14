package bg.duosoft.bpo.publik.core.controller.v1;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.publik.core.service.nomenclature.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final FileStoreService fileStoreService;

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String bucket, @RequestParam String fullPath) {
        FileStoreEntryBaseDTO file = fileStoreService.getFileStoreEntryDetailsAndContent(bucket, fullPath);
        return getResponseEntity(file);
    }

    @PostMapping(value="/temp", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public FileStoreEntryBaseDTO uploadTempFile(@RequestPart String fileName, @RequestPart(required = true, name = "attachment") MultipartFile attachment) {
        try {
            return attachmentService.uploadTempFile(fileName, attachment.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseEntity<byte[]> getResponseEntity(FileStoreEntryBaseDTO file) {
        if (Objects.isNull(file)) {
            return ResponseEntity.notFound().build();
        }
        String fileNameEncoded = URLEncoder.encode(file.getFileName(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", fileNameEncoded, fileNameEncoded))
                .contentType(MediaType.valueOf(file.getContentType())).body(file.getContent());
    }
}
