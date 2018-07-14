package me.int32.transformer;

import me.int32.entries.CommodityDTO;
import me.int32.entries.CommoditySourceDTO;
import me.int32.entries.DataStatusDTO;
import me.int32.service.bo.CommodityBO;
import me.int32.service.bo.CommoditySourceBO;
import me.int32.service.bo.DataStatusBO;
import me.int32.util.DataTypeUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CommodityTransformer {
    public List<CommodityDTO> transform(List<CommodityBO> commodityBOS) {
        List<CommodityDTO> commodityDTOS = new ArrayList<>();
        if (commodityBOS == null) {
            return commodityDTOS;
        }

        commodityBOS.stream().filter(Objects::nonNull).forEach(p -> {
            commodityDTOS.add(transform(p));
        });

        return commodityDTOS;
    }

    public CommodityDTO transform(CommodityBO commodityBO) {
        if (commodityBO == null) {
            return null;
        }

        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setId(commodityBO.getId());
        commodityDTO.setName(commodityBO.getName());
        commodityDTO.setCustomFee(commodityBO.getCustomFee() == null ? null : DataTypeUtil.intToDoublePrice(commodityBO.getCustomFee()));
        commodityDTO.setPurchaseFee(commodityBO.getPurchaseFee() == null ? null : DataTypeUtil.intToDoublePrice(commodityBO.getPurchaseFee()));
        commodityDTO.setSellingNum(commodityBO.getSellingNum());
        commodityDTO.setSku(commodityBO.getSku());
        commodityDTO.setSource(commodityBO.getSource() == null ? null : CommoditySourceDTO.fromValue(commodityBO.getSource().getValue()));
        commodityDTO.setDataStatus(commodityBO.getDataStatus() == null ? null : DataStatusDTO.fromValue(commodityBO.getDataStatus().getValue()));
        commodityDTO.setStock(commodityBO.getStock());
        commodityDTO.setCreatedAt(commodityBO.getCreatedAt());
        commodityDTO.setUpdatedAt(commodityBO.getUpdatedAt());
        commodityDTO.setRemovedAt(commodityBO.getRemovedAt());

        return commodityDTO;
    }

    public CommodityBO transform(CommodityDTO commodityDTO) {
        if (commodityDTO == null) {
            return null;
        }

        CommodityBO commodityBO = new CommodityBO();

        commodityBO.setId(commodityDTO.getId());
        commodityBO.setName(commodityDTO.getName());
        commodityBO.setSku(commodityDTO.getSku());
        commodityBO.setCustomFee(commodityDTO.getCustomFee() == null ? null : DataTypeUtil.doubleToIntPrice(commodityDTO.getCustomFee()));
        commodityBO.setPurchaseFee(commodityDTO.getPurchaseFee() == null ? null : DataTypeUtil.doubleToIntPrice(commodityDTO.getPurchaseFee()));
        commodityBO.setSellingNum(commodityDTO.getSellingNum());
        commodityBO.setStock(commodityDTO.getStock());
        commodityBO.setDataStatus(commodityDTO.getDataStatus() == null ? null : DataStatusBO.fromValue(commodityDTO.getDataStatus().getValue()));
        commodityBO.setSource(commodityDTO.getSource() == null ? null : CommoditySourceBO.fromValue(commodityDTO.getSource().getValue()));
        commodityBO.setRemovedAt(commodityDTO.getRemovedAt());
        commodityBO.setUpdatedAt(commodityDTO.getUpdatedAt());
        commodityBO.setRemovedAt(commodityDTO.getRemovedAt());

        return commodityBO;
    }
}
