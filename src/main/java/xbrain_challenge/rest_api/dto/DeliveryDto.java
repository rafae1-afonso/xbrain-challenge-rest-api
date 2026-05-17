package xbrain_challenge.rest_api.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import xbrain_challenge.rest_api.database.entity.OrderEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DeliveryDto {

    @NotBlank
    @Column(nullable = false)
    private String deliveryAddress;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
