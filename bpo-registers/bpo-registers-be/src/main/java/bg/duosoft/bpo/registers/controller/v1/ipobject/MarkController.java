package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.filter.MarkFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkAttachmentDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.MarkFilterDetailsMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpMarkAttachmentService;
import bg.duosoft.bpo.registers.service.ipobject.IpMarkService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static bg.duosoft.bpo.registers.controller.v1.file.FileController.getResponseEntity;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.02.2024
 * Time: 17:51
 */
@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.MARKS)
@RequestMapping(value = {"/api/v1/marks"})
@RequiredArgsConstructor
public class MarkController {

    private final IpMarkService ipMarkService;
    private final IpObjectSearchService ipObjectSearchService;
    private final MarkFilterDetailsMapper markFilterDetailsMapper;
    private final FileStoreService fileStoreService;
    private final IpMarkAttachmentService markAttachmentService;

    @GetMapping(value = "/attachment")
    public ResponseEntity<byte[]> getMarkAttachment(@RequestParam("objectId") String objectId, @RequestParam(value = "sequenceNumber", required = false) Integer sequenceNumber) {
        IpMarkAttachmentDTO ma = markAttachmentService.selectMarkAttachmentByObjectId(objectId, sequenceNumber);
        FileStoreEntryBaseDTO file = ma == null ? null : fileStoreService.getFileStoreEntryDetailsAndContent(ma.getAttachment().getBucketName(), ma.getAttachment().getFileLocation());
        return getResponseEntity(file);
    }

    @PostMapping
    public Page<IpObjectSearchResult> search(@RequestBody MarkFilterDetailsDTO filter) {
        IpObjectFilter searchRequiredFilter = markFilterDetailsMapper.toIpObjectFilter(filter);
        return ipObjectSearchService.search(searchRequiredFilter);
    }

    @GetMapping("/{id}")
    public IpMarkDTO getById(@PathVariable String id) {
        return ipMarkService.getById(id, true);
    }

    @GetMapping("/by-id")
    public IpMarkDTO getObjectById(@RequestParam String id) {
        return ipMarkService.getById(id, true);
    }
}
