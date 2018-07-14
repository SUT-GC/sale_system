package me.int32.dao.po;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "sale_system_commodity")
public class CommodityPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name = "";

    @Column(name = "sku")
    private String sku = "";

    @Column(name = "purchase_fee")
    private Integer purchaseFee = 0;

    @Column(name = "custom_fee")
    private Integer customFee = 0;

    @Column(name = "selling_num")
    private Integer sellingNum = 0;

    @Column(name = "stock")
    private Integer stock = 0;

    @Column(name = "source")
    private Integer source = 0;

    @Column(name = "data_status")
    private Integer dataStatus = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "removed_at")
    private LocalDateTime removedAt = LocalDateTime.now();

}
