package com.scofield.controller;

import com.scofield.service.UserRoleService;
import com.scofield.entity.UserRole;

import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *  userRole前端控制器
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    public UserRoleService userRoleService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userRoleService.queryByPage(params);
        return R.success("success", page);
    }

    @PostMapping("/save")
    public R save(@RequestBody UserRole userRole) {
            userRoleService.save(userRole);
        return R.success("success");
    }

    @PostMapping("/updateById")
    public R update(@RequestBody UserRole userRole) {
            userRoleService.updateById(userRole);
        return R.success("success");
    }

    @PostMapping("/deleteById")
    public R delete(@RequestBody UserRole userRole) {
            userRoleService.removeById(userRole);
        return R.success("success");
    }

    @PostMapping("/deleteIds")
    public R deleteIds(@RequestBody Long[] ids) {
            userRoleService.removeByIds(Arrays.asList(ids));
        return R.success("success");
    }

}