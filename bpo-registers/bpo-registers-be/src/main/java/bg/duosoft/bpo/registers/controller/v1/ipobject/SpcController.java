package bg.duosoft.bpo.registers.controller.v1.ipobject;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.filter.SpcFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPatentDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.SpcFilterDetailsMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.service.ipobject.IpPatentService;
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
 * Time: 17:51
 */
@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.SPC)
@RequestMapping(value = {"/api/v1/spc"})
@RequiredArgsConstructor
public class SpcController {

    private final IpPatentService ipPatentService;
    private final IpObjectSearchService ipObjectSearchService;
    private final SpcFilterDetailsMapper spcFilterDetailsMapper;

    @PostMapping
    public Page<IpObjectSearchResult> search(@RequestBody SpcFilterDetailsDTO filter){
        IpObjectFilter searchRequiredFilter = spcFilterDetailsMapper.toIpObjectFilter(filter);
        return ipObjectSearchService.search(searchRequiredFilter);
    }

    @GetMapping("/{id}")
    public IpPatentDTO getById(@PathVariable String id){
        return ipPatentService.getById(id, true);
    }

    @GetMapping("/by-id")
    public IpPatentDTO getObjectById(@RequestParam String id) {
        return ipPatentService.getById(id, true);
    }
}
