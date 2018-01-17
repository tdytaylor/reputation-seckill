package com.taylor.reputation.seckill.controller;

import com.taylor.reputation.seckill.domain.User;
import com.taylor.reputation.seckill.redis.KeyPrefix;
import com.taylor.reputation.seckill.redis.RedisService;
import com.taylor.reputation.seckill.redis.UserKey;
import com.taylor.reputation.seckill.result.Result;
import com.taylor.reputation.seckill.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "joshua");
        return "hello";
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    //1.rest api json输出 2.页面
    @RequestMapping("/hello")
    public Result<String> hello() {
        return Result.success("hello,imooc");
        // return new Result(0, "success", "hello,imooc");
    }

    @RequestMapping("/helloError")
    public Result<String> helloError() {
        return Result.error(Result.CodeMsg.SERVER_ERROR);
        //return new Result(500102, "XXX");
    }

    @RequestMapping("/db/get")
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping(value = "/redis/get")
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "1", User.class);
        return Result.success(user);
    }

    @RequestMapping(value = "/redis/set")
    public Result<Boolean> redisSet() {
        User user = new User(1, "taylor swift");
        boolean flag = redisService.set(UserKey.getById, "1", user);
        return Result.success(flag);
    }
}
