package xbrain_challenge.rest_api.database.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.Random;

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
    private String productCode = "PRODUCT-" + (new Random().nextInt(999-100+1) + 100);

    @Column(nullable = false, scale = 2)
    @Positive(message = "The product price must be positive.")
    @NotNull(message = "The product price can't be NULL.")
    private BigDecimal price = new BigDecimal(
            (new Random().nextInt(100) / 100.0) + (new Random().nextInt(1000) + 1)
    );

    @Column(nullable = false)
    @Positive(message = "The product quantity must be positive.")
    private Integer quantity = new Random().nextInt(5) + 1;
}
