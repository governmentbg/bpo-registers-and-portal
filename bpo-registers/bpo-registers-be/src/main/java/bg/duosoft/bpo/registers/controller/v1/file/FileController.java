package bg.duosoft.bpo.registers.controller.v1.file;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.registers.service.file.ThumbnailService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@RestController
@Api(tags = Tags.FILES)
@RequestMapping(value = {"/api/v1/files"})
@RequiredArgsConstructor
public class FileController {

    private final FileStoreService fileStoreService;
    private final ThumbnailService thumbnailService;

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> getAbdocsFileContent(@RequestParam String bucket, @RequestParam String fullPath) {
        FileStoreEntryBaseDTO file = fileStoreService.getFileStoreEntryDetailsAndContent(bucket, fullPath);
        return getResponseEntity(file);
    }

    @GetMapping("/thumbnails/ipobjects")
    public ResponseEntity<byte[]> getThumbnailByObjectIdAndType(@RequestParam("objectId") String objectId, @RequestParam("objectType") String objectType) {
        FileStoreEntryBaseDTO file = thumbnailService.getThumbnailByObjectIdAndType(objectId, objectType);
        return getResponseEntity(file);
    }

    public static ResponseEntity<byte[]> getResponseEntity(FileStoreEntryBaseDTO file) {
        if (Objects.isNull(file)) {
            return ResponseEntity.notFound().build();
        }

        String fileNameEncoded = URLEncoder.encode(file.getFileName(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", fileNameEncoded, fileNameEncoded))
                .contentType(MediaType.valueOf(file.getContentType())).body(file.getContent());
    }
}
