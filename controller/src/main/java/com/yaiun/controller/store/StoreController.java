package com.yaiun.controller.store;


import com.yaiun.common.model.StoreDO;
import com.yaiun.common.other.Result;
import com.yaiun.service.StoreService;
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
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/store")
    public Result getSellerList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        List<StoreDO> list = storeService.list();
        int count = list.size();
        if (page != null && size != null) {
            list = list.stream().skip(page * size - 1).limit(size).collect(Collectors.toList());
        }
        return Result.ok(count, list);
    }

    @DeleteMapping("/store")
    public Result deleteUser(@RequestParam(required = false) Integer id) {
        storeService.removeById(id);
        return Result.ok();
    }

    @PostMapping("/store")
    public Result update(@RequestBody StoreDO sellerDO) {
        storeService.saveOrUpdate(sellerDO);
        return Result.ok();
    }

}

