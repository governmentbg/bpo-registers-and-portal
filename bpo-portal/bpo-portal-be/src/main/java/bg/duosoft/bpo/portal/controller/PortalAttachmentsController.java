package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.portal.dto.PortalAttachmentShort;
import bg.duosoft.bpo.portal.service.PortalAttachmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portal-attachments")
public class PortalAttachmentsController {

    private final PortalAttachmentsService portalAttachmentsService;


    @GetMapping()
    public List<PortalAttachmentShort> getAllPortalAttachments() {
        return this.portalAttachmentsService.getAllPortalAttachments();
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFileStoreEntryById(@PathVariable("id") String id) {
        FileStoreEntryBaseDTO fileStoreEntry = this.portalAttachmentsService.getFileStoreEntryById(id);

        if (Objects.isNull(fileStoreEntry)) {
            throw new ResourceNotFoundException();
        }

        String fileName = fileStoreEntry.getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-file-name", fileName);
        headers.set("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", fileName, fileName));
        headers.set("Access-Control-Expose-Headers", "x-file-name");
        headers.set("Access-Control-Allow-Headers", "x-file-name");
        headers.setContentType(MediaType.valueOf(fileStoreEntry.getContentType()));

        return  new ResponseEntity<>(fileStoreEntry.getContent(), headers, HttpStatus.OK);
    }
}
