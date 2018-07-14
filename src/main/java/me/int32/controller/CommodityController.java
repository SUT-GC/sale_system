package me.int32.controller;

import me.int32.entries.CommodityDTO;
import me.int32.entries.ResultDTO;
import me.int32.entries.ResultStatusDTO;
import me.int32.exception.InvalidParamException;
import me.int32.service.api.CommodityService;
import me.int32.service.bo.CommodityBO;
import me.int32.transformer.CommodityTransformer;
import me.int32.validate.ValidateCommodity;
import me.int32.validate.ValidateNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityTransformer commodityTransformer;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultDTO index() {
        try {
            return ResultDTO.of(ResultStatusDTO.success(), "Hello World");
        } catch (Exception e) {
            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultDTO getAllCommodity() {
        try {
            List<CommodityDTO> commodityDTOS = commodityTransformer.transform(commodityService.getAllCommodity());

            return ResultDTO.of(ResultStatusDTO.success(), commodityDTOS);
        } catch (Exception e) {
            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultDTO save(@RequestBody CommodityDTO commodity) {
        try {
            ValidateCommodity.validate(commodity);
            CommodityBO result = commodityService.save(commodityTransformer.transform(commodity));

            return ResultDTO.of(ResultStatusDTO.success(), commodityTransformer.transform(result));
        } catch (Exception e) {
            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultDTO delete(@RequestParam(value = "id") Long id) {
        try {
            ValidateNull.validateNull(id);
            commodityService.delete(id);

            return ResultDTO.of(ResultStatusDTO.success(), null);
        } catch (Exception e) {
            return ResultDTO.of(ResultStatusDTO.error(e), null);
        }
    }
}
