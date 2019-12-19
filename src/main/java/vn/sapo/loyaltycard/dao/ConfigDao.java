package vn.sapo.loyaltycard.dao;

import vn.sapo.loyaltycard.model.Config;

public interface ConfigDao {
    Config save(Config config);
    Config getConfig(String config);
    Config getConfig(Long id);
}
