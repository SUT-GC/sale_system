package me.int32.service.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CommoditySourceBO {

    WANG_LI_WEI(100, "王利维"),;

    @Getter
    private int value;
    @Getter
    private String desc;

    public static CommoditySourceBO fromValue(int value) {
        for (CommoditySourceBO commoditySourceBO : CommoditySourceBO.values()) {
            if (commoditySourceBO.getValue() == value) {
                return commoditySourceBO;
            }
        }

        throw new RuntimeException("CommoditySourceBO has no value" + value);
    }
}
