package me.int32.validate;

import me.int32.entries.CommodityDTO;
import me.int32.exception.InvalidParamException;
import me.int32.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidateCommodity {
    public static void validate(CommodityDTO commodityDTO) throws ServiceException {
        ValidateNull.validateNull(commodityDTO);

        if (StringUtils.isEmpty(commodityDTO.getName())) {
            throw new InvalidParamException("商品名称不能为空");
        }

        if (commodityDTO.getCustomFee() == null || commodityDTO.getCustomFee() < 0.0) {
            throw new InvalidParamException("商品定价错误");
        }

        if (commodityDTO.getPurchaseFee() == null || commodityDTO.getPurchaseFee() < 0.0) {
            throw new InvalidParamException("商品进价错误");
        }

        if (commodityDTO.getSellingNum() == null || commodityDTO.getSellingNum() < 0.0) {
            throw new InvalidParamException("销量异常");
        }

        if (commodityDTO.getStock() == null || commodityDTO.getStock() < 0.0) {
            throw new InvalidParamException("库存异常");
        }
    }
}
