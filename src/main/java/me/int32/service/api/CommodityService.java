package me.int32.service.api;

import me.int32.exception.InvalidParamException;
import me.int32.service.bo.CommodityBO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommodityService {
    List<CommodityBO> getAllCommodity();

    CommodityBO save(CommodityBO commodityBO) throws InvalidParamException;

    List<CommodityBO> findByIds(List<Long> commodityIds);
}
