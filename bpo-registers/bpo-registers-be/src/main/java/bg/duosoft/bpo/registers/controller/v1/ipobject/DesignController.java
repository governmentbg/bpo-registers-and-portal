package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.filter.DesignFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.ipobject.IpDesignDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.DesignFilterDetailsMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpDesignService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
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
@Api(tags = Tags.DESIGNS)
@RequestMapping(value = {"/api/v1/designs"})
@RequiredArgsConstructor
public class DesignController {

    private final IpDesignService ipDesignService;
    private final IpObjectSearchService ipObjectSearchService;
    private final DesignFilterDetailsMapper designFilterDetailsMapper;

    @PostMapping
    public Page<IpObjectSearchResult> search(@RequestBody DesignFilterDetailsDTO filter) {
        IpObjectFilter searchRequiredFilter = designFilterDetailsMapper.toIpObjectFilter(filter);
        return ipObjectSearchService.search(searchRequiredFilter);
    }

    @GetMapping("/{id}")
    public IpDesignDTO getById(@PathVariable String id) {
        return ipDesignService.getById(id, true);
    }

    @GetMapping("/by-id")
    public IpDesignDTO getObjectById(@RequestParam String id) {
        return ipDesignService.getById(id, true);
    }
}
