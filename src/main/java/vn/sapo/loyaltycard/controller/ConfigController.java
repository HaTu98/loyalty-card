package vn.sapo.loyaltycard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.loyaltycard.dto.config.ConfigRequest;
import vn.sapo.loyaltycard.dto.config.ConfigResponse;
import vn.sapo.loyaltycard.service.ConfigService;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;


    @PostMapping("/update")
    public ConfigResponse updateConfig(@RequestBody ConfigRequest configRequest) throws Exception {
        return configService.updateConfig(configRequest);
    }

    @PostMapping("/create")
    public ConfigResponse createConfig(@RequestBody ConfigRequest configRequest) {
        return configService.createConfig(configRequest);
    }
}
