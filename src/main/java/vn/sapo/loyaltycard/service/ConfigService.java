package vn.sapo.loyaltycard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.loyaltycard.dao.ConfigDao;
import vn.sapo.loyaltycard.dto.config.ConfigRequest;
import vn.sapo.loyaltycard.dto.config.ConfigResponse;
import vn.sapo.loyaltycard.model.Config;

@Service
public class ConfigService {
    @Autowired
    private ConfigDao configDao;

    @Transactional
    public ConfigResponse createConfig(ConfigRequest request) {
        Config config = new Config();
        config.setConfig(request.getConfig());
        config.setStatus(request.getStatus());
        config.setValue(request.getValue());
        return getConfigResponse(configDao.save(config));
    }

    @Transactional
    public ConfigResponse updateConfig(ConfigRequest request) throws Exception {
        Config config = configDao.getConfig(request.getConfig());
        if (config == null)
            throw new Exception("Config ko tồn tại");

        config.setValue(request.getValue());
        return getConfigResponse(configDao.save(config));
    }

    private ConfigResponse getConfigResponse(Config config) {
        return ConfigResponse.builder()
                .id(config.getId())
                .config(config.getConfig())
                .value(config.getValue())
                .status(config.getStatus())
                .build();
    }
}
