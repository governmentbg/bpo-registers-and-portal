package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.filter.PlantBreedFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPlantDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.PlantBreedFilterDetailsMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.service.ipobject.IpPlantService;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.PLANTS_BREEDS)
@RequestMapping(value = {"/api/v1/plants-breeds"})
@RequiredArgsConstructor
public class PlantBreedController {

    private final IpPlantService ipPlantService;
    private final IpObjectSearchService ipObjectSearchService;
    private final PlantBreedFilterDetailsMapper plantBreedFilterDetailsMapper;

    @PostMapping
    public Page<IpObjectSearchResult> search(@RequestBody PlantBreedFilterDetailsDTO filter) {
        IpObjectFilter searchRequiredFilter = plantBreedFilterDetailsMapper.toIpObjectFilter(filter);
        return ipObjectSearchService.search(searchRequiredFilter);
    }

    @GetMapping("/{id}")
    public IpPlantDTO getById(@PathVariable String id) {
        return ipPlantService.getById(id, true);
    }

    @GetMapping("/by-id")
    public IpPlantDTO getObjectById(@RequestParam String id) {
        return ipPlantService.getById(id, true);
    }
}
