package com.yaiun.controller.seller;


import com.yaiun.common.model.SellerDO;
import com.yaiun.common.other.Result;
import com.yaiun.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wj
 * @since 2021-09-22
 */
@RestController
public class SellerController {
    @Autowired
    public SellerService sellerService;


    @GetMapping("/seller")
    public Result getSellerList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        List<SellerDO> list = sellerService.list();
        int count = list.size();
        if (page != null && size != null) {
            list = list.stream().skip(page * size - 1).limit(size).collect(Collectors.toList());
        }
        return Result.ok(count, list);
    }

    @DeleteMapping("/seller")
    public Result deleteUser(@RequestParam(required = false) Integer id) {
        sellerService.removeById(id);
        return Result.ok();
    }

    @PostMapping("seller")
    public Result update(@RequestBody SellerDO sellerDO) {
        sellerService.saveOrUpdate(sellerDO);
        return Result.ok();
    }
}

