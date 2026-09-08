package org.king.controller;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.Emp;
import org.king.pojo.LoginInfo;
import org.king.pojo.Result;
import org.king.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
* 登录Controller
* */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    /*
    * 登录方法
    * */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录信息：{}", emp);
        LoginInfo loginInfo = empService.login(emp);

        if (loginInfo!=null){
            return Result.success(loginInfo);
        }

        return Result.error("用户名或者密码错误");
    }

}
