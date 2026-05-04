package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.VwPersonObjectRelationshipDTO;
import bg.duosoft.bpo.registers.service.ipobject.VwPersonObjectRelationshipsService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.PERSON_OBJECT_RELATIONSHIPS)
@RequestMapping(value = {"/api/v1/person-object-relationships"})
@RequiredArgsConstructor
public class VwPersonObjectRelationshipsController {

    private final VwPersonObjectRelationshipsService service;

    @GetMapping
    public List<VwPersonObjectRelationshipDTO> getRelationships(@RequestParam String personName, @RequestParam String objectId) {
        return service.getRelationshipsByPersonNameExcludeObjectId(objectId, personName, PageRequest.of(0, 200));
    }

    @GetMapping("/count")
    public Integer getRelationshipsCount(@RequestParam String personName) {
        return service.getRelationshipsByPersonNameCount(personName);
    }
}
