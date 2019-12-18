package vn.sapo.loyaltycard.dto.config;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class ConfigResponse {
    private Long id;
    private String config;
    private Integer value;
    private String status;
}
