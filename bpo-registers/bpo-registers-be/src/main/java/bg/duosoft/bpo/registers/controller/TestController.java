package bg.duosoft.bpo.registers.controller;

import bg.duosoft.bpo.common.web.controller.BaseAccessController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: ggeorgiev
 * Date: 12.01.2024
 * Time: 17:12
 */
@RestController
public class TestController extends BaseAccessController {
    @GetMapping("/test")
    public String test() {
        return "done";
    }

    @Override
    public String getEditRole() {
        return "test";
    }

    @Override
    public String getAccessRole() {
        return "test";
    }
}
