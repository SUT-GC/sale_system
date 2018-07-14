package me.int32.entries;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CommoditySourceDTO {
    WANG_LI_WEI(100, "王利维"),;

    @Getter
    private int value;
    @Getter
    private String desc;

    public static CommoditySourceDTO fromValue(int value) {
        for (CommoditySourceDTO commoditySourceDTO : CommoditySourceDTO.values()) {
            if (commoditySourceDTO.getValue() == value) {
                return commoditySourceDTO;
            }
        }

        throw new RuntimeException("CommoditySourceDTO has no value" + value);
    }
}
