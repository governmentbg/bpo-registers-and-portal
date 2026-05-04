package bg.duosoft.bpo.registers.controller.v1.recordal;

import bg.duosoft.bpo.registers.dto.recordal.RecordalDTO;
import bg.duosoft.bpo.publik.core.enums.RecordalGroupType;
import bg.duosoft.bpo.registers.service.recordal.RecordalService;
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
@Api(tags = Tags.RECORDAL)
@RequestMapping(value = {"/api/v1/recordals"})
@RequiredArgsConstructor
public class RecordalController {

    private final RecordalService recordalService;

    @GetMapping
    public List<RecordalDTO> getRecordalsByObjectId(@RequestParam String objectId) {
        return recordalService.getRecordalsWithExcludedGroupsByObjectId(List.of(RecordalGroupType.OPPOSITION.groupTypeId(),
                RecordalGroupType.CANCELLATION.groupTypeId()), objectId);
    }

    @GetMapping("/oppositions")
    public List<RecordalDTO> getOppositionsByObjectId(@RequestParam String objectId) {
        return recordalService.getRecordalsWithIncludedGroupsByObjectId(List.of(RecordalGroupType.OPPOSITION.groupTypeId()), objectId);
    }

    @GetMapping("/cancellations")
    public List<RecordalDTO> getCancellationsByObjectId(@RequestParam String objectId) {
        return recordalService.getRecordalsWithIncludedGroupsByObjectId(List.of(RecordalGroupType.CANCELLATION.groupTypeId()), objectId);
    }

}
