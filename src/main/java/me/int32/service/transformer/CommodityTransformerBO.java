package me.int32.service.transformer;

import me.int32.dao.po.CommodityPO;
import me.int32.service.bo.CommodityBO;
import me.int32.service.bo.CommoditySourceBO;
import me.int32.service.bo.DataStatusBO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommodityTransformerBO {

    public List<CommodityBO> transform(List<CommodityPO> commodityPOS) {
        if (commodityPOS == null) {
            return null;
        }

        List<CommodityBO> commodityBOS = new ArrayList<>();

        commodityPOS.forEach(p -> {
            commodityBOS.add(transform(p));
        });

        return commodityBOS;
    }

    public CommodityBO transform(CommodityPO commodityPO) {
        if (commodityPO == null) {
            return null;
        }

        CommodityBO commodityBO = new CommodityBO();

        commodityBO.setId(commodityPO.getId());
        commodityBO.setName(commodityPO.getName());
        commodityBO.setSku(commodityPO.getSku());
        commodityBO.setCustomFee(commodityPO.getCustomFee());
        commodityBO.setPurchaseFee(commodityPO.getPurchaseFee());
        commodityBO.setSellingNum(commodityPO.getSellingNum());
        commodityBO.setSource(commodityPO.getSource() == null ? null : CommoditySourceBO.fromValue(commodityPO.getSource()));
        commodityBO.setDataStatus(commodityPO.getDataStatus() == null ? null : DataStatusBO.fromValue(commodityPO.getDataStatus()));
        commodityBO.setStock(commodityPO.getStock());
        commodityBO.setCreatedAt(commodityPO.getCreatedAt());
        commodityBO.setUpdatedAt(commodityPO.getUpdatedAt());
        commodityBO.setRemovedAt(commodityPO.getRemovedAt());

        return commodityBO;
    }

    public CommodityPO transform(CommodityBO commodityBO) {
        if (commodityBO == null) {
            return null;
        }

        CommodityPO commodityPO = new CommodityPO();

        commodityPO.setId(commodityBO.getId());
        commodityPO.setName(commodityBO.getName());
        commodityPO.setSku(commodityBO.getSku());
        commodityPO.setCustomFee(commodityBO.getCustomFee());
        commodityPO.setPurchaseFee(commodityBO.getPurchaseFee());
        commodityPO.setSellingNum(commodityBO.getSellingNum());
        commodityPO.setSource(commodityBO.getSource() == null ? 0 : commodityBO.getSource().getValue());
        commodityPO.setStock(commodityBO.getStock());
        commodityPO.setCreatedAt(commodityBO.getCreatedAt());
        commodityPO.setUpdatedAt(commodityBO.getUpdatedAt());
        commodityPO.setRemovedAt(commodityBO.getRemovedAt());
        commodityPO.setDataStatus(commodityBO.getDataStatus() == null ? 0 : commodityBO.getDataStatus().getValue());

        return commodityPO;
    }
}
