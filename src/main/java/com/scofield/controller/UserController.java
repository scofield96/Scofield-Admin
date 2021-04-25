package com.scofield.controller;

import com.scofield.frame.aop.annotation.Log;
import com.scofield.service.UserService;
import com.scofield.entity.User;

import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *  user前端控制器
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Log("获取用户列表")
    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryByPage(params);
        return R.success("success", page);
    }

    @PostMapping("/save")
    public R save(@RequestBody User user) {
            userService.save(user);
        return R.success("success");
    }

    @PostMapping("/updateById")
    public R update(@RequestBody User user) {
            userService.updateById(user);
        return R.success("success");
    }

    @PostMapping("/deleteById")
    public R delete(@RequestBody User user) {
            userService.removeById(user);
        return R.success("success");
    }

    @PostMapping("/deleteIds")
    public R deleteIds(@RequestBody Long[] ids) {
            userService.removeByIds(Arrays.asList(ids));
        return R.success("success");
    }

}