package com.yaiun.controller.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaiun.service.UserService;
import com.yaiun.common.model.UserDO;
import com.yaiun.common.other.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(tags = "登录接口")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password
    ) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        UserDO userDO = userService.getOne(wrapper);
        if (userDO == null) {
            return Result.fail("用户名或者密码错误");
        }
        return Result.ok(1, userDO);
    }

}
