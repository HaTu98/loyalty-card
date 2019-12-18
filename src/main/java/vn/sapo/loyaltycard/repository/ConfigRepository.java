package vn.sapo.loyaltycard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.loyaltycard.model.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    Config findByConfig(String config);
}
