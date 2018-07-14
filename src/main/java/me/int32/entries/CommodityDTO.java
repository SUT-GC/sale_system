package me.int32.entries;

import lombok.Data;
import me.int32.util.JsonUtil;

import java.time.LocalDateTime;

@Data
public class CommodityDTO {
    private Long id;

    private String name;

    private String sku;

    private Double purchaseFee;

    private Double customFee;

    private Integer sellingNum;

    private Integer stock;

    private CommoditySourceDTO source = CommoditySourceDTO.WANG_LI_WEI;

    private DataStatusDTO dataStatus = DataStatusDTO.VALID;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;

    public static void main(String[] args) {
        CommodityDTO commodityDTO = new CommodityDTO();

        commodityDTO.setName("测试1");
        commodityDTO.setSku("测试2");
        commodityDTO.setPurchaseFee(2.0);
        commodityDTO.setCustomFee(2.0);
        commodityDTO.setSellingNum(10);
        commodityDTO.setStock(2);
        commodityDTO.setSource(CommoditySourceDTO.WANG_LI_WEI);
        commodityDTO.setDataStatus(DataStatusDTO.VALID);
        commodityDTO.setCreatedAt(LocalDateTime.now());
        commodityDTO.setUpdatedAt(LocalDateTime.now());
        commodityDTO.setRemovedAt(LocalDateTime.now());

        System.out.println(JsonUtil.writeAsString(commodityDTO));
    }
}
