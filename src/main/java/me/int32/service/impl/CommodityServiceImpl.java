package me.int32.service.impl;

import me.int32.dao.po.CommodityPO;
import me.int32.dao.repositories.CommodityRepository;
import me.int32.exception.InvalidParamException;
import me.int32.service.api.CommodityService;
import me.int32.service.bo.CommodityBO;
import me.int32.service.bo.DataStatusBO;
import me.int32.service.transformer.CommodityTransformerBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private CommodityTransformerBO commodityTransformer;

    @Override
    public List<CommodityBO> getAllCommodity() {
        return commodityTransformer.transform(commodityRepository.findAll());
    }

    @Override
    public CommodityBO save(CommodityBO commodityBO) throws InvalidParamException {
        if (commodityBO == null) {
            return null;
        }

        commodityBO.setUpdatedAt(LocalDateTime.now());
        commodityBO.setRemovedAt(LocalDateTime.now());
        commodityBO.setCreatedAt(LocalDateTime.now());

        CommodityPO saveFinish = null;
        if (commodityBO.getId() == null) {
            saveFinish = insertNewCommodity(commodityBO);
        } else {
            saveFinish = updateCommodity(commodityBO);
        }

        return commodityTransformer.transform(saveFinish);
    }

    private CommodityPO updateCommodity(CommodityBO commodityBO) {
        return commodityRepository.save(commodityTransformer.transform(commodityBO));
    }

    private CommodityPO insertNewCommodity(CommodityBO commodityBO) throws InvalidParamException {
        List<CommodityPO> commodityPOS = commodityRepository.findByNameAndSku(commodityBO.getName(), commodityBO.getSku());

        if (!commodityPOS.isEmpty()) {
            throw new InvalidParamException(String.format("%s-%s重复", commodityBO.getName(), commodityBO.getSku()));
        }

        return commodityRepository.save(commodityTransformer.transform(commodityBO));
    }


    @Override
    public List<CommodityBO> findByIds(List<Long> commodityIds) {
        if (CollectionUtils.isEmpty(commodityIds)) {
            return new ArrayList<>();
        }

        return commodityTransformer.transform(commodityRepository.findAllById(commodityIds));
    }

    @Override
    public void delete(Long id) throws InvalidParamException {
        CommodityPO commodityPO = commodityRepository.findById(id).orElse(null);

        if (commodityPO == null) {
            throw new InvalidParamException(String.format("未找ID为%s的商品", id));
        }

        commodityPO.setDataStatus(DataStatusBO.INVALID.getValue());
        commodityPO.setRemovedAt(LocalDateTime.now());
        commodityPO.setUpdatedAt(LocalDateTime.now());

        commodityRepository.save(commodityPO);
    }
}
