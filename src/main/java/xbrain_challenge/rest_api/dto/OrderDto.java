package xbrain_challenge.rest_api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;
import lombok.*;
import xbrain_challenge.rest_api.database.object.ProductObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDto {

    @NotBlank(message = "The order needs a customer code.")
    @Column(nullable = false)
    @Pattern(regexp = "^CUSTOMER-\\d{3}$", message = "The customer code must match the format: CUSTOMER-000.")
    private String customerCode;

    private BigDecimal totalValue;

    @NotBlank(message = "You must add a delivery address for this order.")
    @Column(nullable = false)
    private String deliveryAddress;

    @ElementCollection
    @Size(min = 1, message = "The list must contain at least one product.")
    @Column
    private List<ProductObject> products = new ArrayList<>();
}
