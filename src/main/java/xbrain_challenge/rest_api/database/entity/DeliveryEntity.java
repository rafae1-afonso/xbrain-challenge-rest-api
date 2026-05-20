package xbrain_challenge.rest_api.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "table_delivery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID order_id;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
