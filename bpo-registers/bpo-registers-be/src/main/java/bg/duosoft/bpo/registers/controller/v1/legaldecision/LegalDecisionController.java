package bg.duosoft.bpo.registers.controller.v1.legaldecision;


import bg.duosoft.bpo.registers.dto.filter.LegalDecisionFilter;
import bg.duosoft.bpo.registers.dto.legaldecision.LegalDecisionDocumentDTO;
import bg.duosoft.bpo.registers.service.legaldecision.LegalDecisionDocumentService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.LEGAL_DECISIONS)
@RequestMapping(value = {"/api/v1/legal-decisions"})
@RequiredArgsConstructor
public class LegalDecisionController {

    private final LegalDecisionDocumentService service;

    @PostMapping
    public Page<LegalDecisionDocumentDTO> search(@RequestBody LegalDecisionFilter filter) {
        return service.filterDocuments(filter);
    }

    @GetMapping("/{id}")
    public LegalDecisionDocumentDTO getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/by-id")
    public LegalDecisionDocumentDTO getByDecisionId(@RequestParam String id) {
        return service.getById(id);
    }

    @GetMapping("/by-object-id")
    public List<LegalDecisionDocumentDTO> getAllByObjectId(@RequestParam String id) {
        return service.getAllByObjectId(id);
    }

}
