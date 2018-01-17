package com.taylor.reputation.seckill.controller;

import com.taylor.reputation.seckill.redis.RedisService;
import com.taylor.reputation.seckill.result.Result;
import com.taylor.reputation.seckill.service.MiaoshaUserService;
import com.taylor.reputation.seckill.vo.LoginVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author taylor
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        //登录
        boolean flag = userService.login(response, loginVo);
        return Result.success(flag);
    }
}
