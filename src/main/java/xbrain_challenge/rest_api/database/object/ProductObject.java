package xbrain_challenge.rest_api.database.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Embeddable
public class ProductObject {

    @Column(nullable = false)
    @Pattern(regexp = "^PRODUCT-\\d{3}$", message = "The product code must match the format: PRODUCT-000.")
    private String productCode = "PRODUCT-XXX";

    @Column(nullable = false)
    @Positive(message = "The product price must be positive.")
    @NotNull(message = "The product price can't be NULL.")
    private BigDecimal price = new BigDecimal(0);

    @Column(nullable = false)
    @Positive(message = "The product quantity must be positive.")
    private Integer quantity = 1;
}
