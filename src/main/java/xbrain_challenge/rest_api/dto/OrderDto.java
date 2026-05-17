package xbrain_challenge.rest_api.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.*;
import xbrain_challenge.rest_api.database.entity.ProductEntity;

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
    private String customerCode;

    @Positive(message = "The value must be positive.")
    @NotNull(message = "The value can't be NULL")
    private BigDecimal totalValue;

    @NotBlank(message = "You must add a delivery address for this order.")
    @Column(nullable = false)
    private String deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "The list must contain at least one product.")
    private List<ProductEntity> products = new ArrayList<>();
}
