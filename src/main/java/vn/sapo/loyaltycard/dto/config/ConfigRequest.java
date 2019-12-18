package vn.sapo.loyaltycard.dto.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ConfigRequest {
    private String config;
    private Integer value;
    private String status;
}
