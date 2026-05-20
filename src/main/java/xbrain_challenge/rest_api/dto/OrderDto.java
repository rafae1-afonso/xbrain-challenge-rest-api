package xbrain_challenge.rest_api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;
import lombok.*;

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
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^C-\\d{3}$", message = "The customer code must match the format: C-000.")
    private String customerCode;

    @Positive(message = "The value must be positive.")
    @NotNull(message = "The value can't be NULL")
    private BigDecimal totalValue;

    @NotBlank(message = "You must add a delivery address for this order.")
    @Column(nullable = false)
    private String deliveryAddress;

    @ElementCollection
    @Size(min = 1, message = "The list must contain at least one product code.")
    private List<@Pattern(regexp = "^P-\\d{3}$", message = "The product code must match the format: P-000.") String> products = new ArrayList<>();
}
