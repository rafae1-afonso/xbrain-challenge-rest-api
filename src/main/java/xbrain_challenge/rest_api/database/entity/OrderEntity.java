package xbrain_challenge.rest_api.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import xbrain_challenge.rest_api.database.object.ProductObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "table_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String customerCode;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "table_order_products", joinColumns = @JoinColumn(name = "order_id"))
    @Column
    private List<ProductObject> products = new ArrayList<>();
}
