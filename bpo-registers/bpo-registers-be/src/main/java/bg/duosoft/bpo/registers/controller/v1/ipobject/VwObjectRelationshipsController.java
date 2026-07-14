package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.VwObjectRelationshipDTO;
import bg.duosoft.bpo.registers.service.ipobject.VwObjectRelationshipsService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.OBJECT_RELATIONSHIPS)
@RequestMapping(value = {"/api/v1/object-relationships"})
@RequiredArgsConstructor
public class VwObjectRelationshipsController {

    private final VwObjectRelationshipsService service;

    @GetMapping
    public List<VwObjectRelationshipDTO> getRelationshipsByObjectId(@RequestParam String objectId) {
        return service.getObjectRelationshipsByObjectIdAndRelationshipType(objectId, null);
    }
}
