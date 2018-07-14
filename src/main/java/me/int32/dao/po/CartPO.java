package me.int32.dao.po;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sale_system_cart")
public class CartPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "commodity_id")
    private Long commodityId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "selling_num")
    private Integer sellingNumber;

    @Column(name = "selling_fee")
    private Integer sellingFee;

    @Column(name = "profit")
    private Integer profit;

    @Column(name = "data_status")
    private Integer dataStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "removed_at")
    private LocalDateTime removedAt;
}
