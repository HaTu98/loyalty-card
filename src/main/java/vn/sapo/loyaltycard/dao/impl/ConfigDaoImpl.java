package vn.sapo.loyaltycard.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.loyaltycard.dao.ConfigDao;
import vn.sapo.loyaltycard.model.Config;
import vn.sapo.loyaltycard.repository.ConfigRepository;

@Service
public class ConfigDaoImpl implements ConfigDao {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public Config save(Config config) {
        return configRepository.save(config);
    }

    @Override
    public Config getConfig(String config) {
        return configRepository.findByConfig(config);
    }
}
