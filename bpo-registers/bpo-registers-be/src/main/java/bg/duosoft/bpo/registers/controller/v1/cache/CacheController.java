package bg.duosoft.bpo.registers.controller.v1.cache;

import bg.duosoft.bpo.publik.core.service.cache.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "Cache")
@RequestMapping("/api/v1/cache")
@PreAuthorize("hasRole(T(bg.duosoft.bpo.common.security.util.SecurityRole).ADMIN_REGISTERS)")
public class CacheController {
    private final CacheService cacheService;

    @GetMapping(value = "/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Clear application cache")
    public void clearAll() {
        cacheService.clearCache();
    }

}
