package xbrain_challenge.rest_api.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import xbrain_challenge.rest_api.database.entity.OrderEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DeliveryDto {

    @NotBlank(message = "A delivery must contain an address")
    @NotNull
    private String deliveryAddress;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
