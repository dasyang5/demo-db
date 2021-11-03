package com.example.demo.controller;

import com.example.demo.bean.BaseController;
import com.example.demo.bean.RestResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Alex
 * @date 2021/10/29 15:47
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public RestResponse add(User user) {

        if (userService.count(User.builder().username(user.getUsername()).build()) > 0) {
            return error("用户已存在");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        user.setCreTime(new Date());
        user.setUpdTime(new Date());
        userService.save(user);

        return success();
    }

    @GetMapping("list")
    public RestResponse list(User user) {
        return success().tableData(userService.findTableData(user, getPageBean()));
    }

    @GetMapping("get/{id}")
    public RestResponse get(@PathVariable(name = "id") String id) {
        return success().fluentPut("user", userService.get(id));
    }

    @DeleteMapping("delete/{id}")
    public RestResponse delete(@PathVariable(name = "id") String id) {
        userService.delete(id);
        return success();
    }

}
