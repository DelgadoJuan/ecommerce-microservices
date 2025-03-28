package com.juan.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_line")
@Builder
public class OrderLineEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private OrderEntity order;
    @NotNull(message = "The product id must not be null")
    @Positive(message = "The product id must be positive")
    private Long productId;
    @Positive(message = "The quantity must be positive")
    @NotNull(message = "The quantity must not be null")
    private int quantity;
}
