package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.filter.GeoIndicationFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.GeoIndicationFilterDetailsMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpMarkService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.02.2024
 * Time: 18:38
 */
@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.GEO_INDICATIONS)
@RequestMapping(value = {"/api/v1/geo-indications"})
@RequiredArgsConstructor
public class GeoIndicationController {

    private final IpMarkService ipMarkService;
    private final IpObjectSearchService ipObjectSearchService;
    private final GeoIndicationFilterDetailsMapper geoIndicationFilterDetailsMapper;

    @PostMapping
    public Page<IpObjectSearchResult> search(@RequestBody GeoIndicationFilterDetailsDTO filter) {
        IpObjectFilter searchRequiredFilter = geoIndicationFilterDetailsMapper.toIpObjectFilter(filter);
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
