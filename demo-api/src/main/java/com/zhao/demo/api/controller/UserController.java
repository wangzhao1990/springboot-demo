package com.zhao.demo.api.controller;

import com.zhao.demo.dal.po.User;
import com.zhao.demo.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo-api/user")
@Api("UserController相关的api")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET,produces="application/json")
    @ApiOperation(value = "根据用户ID查询用户信息",notes = "查询数据库中某个的用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Integer")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }
}
