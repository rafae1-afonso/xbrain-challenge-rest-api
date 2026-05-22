package xbrain_challenge.rest_api.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DeliveryDto {

    @org.hibernate.validator.constraints.UUID(message = "The delivery needs an order_id")
    @Column
    private UUID order_id;

    @NotBlank(message = "A delivery must contain an address.")
    @NotNull
    private String deliveryAddress;
}
