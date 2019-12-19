package vn.sapo.loyaltycard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.loyaltycard.model.LoyaltyCardType;

import java.util.List;

@Repository
public interface LoyaltyCardTypeRepository extends JpaRepository<LoyaltyCardType, Long > {

    List<LoyaltyCardType> findBySpentThresholdBeforeOrderBySpentThresholdDesc(Float totalSpent);
}
