package com.scofield.controller;

import com.scofield.service.RoleService;
import com.scofield.entity.Role;

import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *  role前端控制器
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public RoleService roleService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = roleService.queryByPage(params);
        return R.success("success", page);
    }

    @PostMapping("/save")
    public R save(@RequestBody Role role) {
            roleService.save(role);
        return R.success("success");
    }

    @PostMapping("/updateById")
    public R update(@RequestBody Role role) {
            roleService.updateById(role);
        return R.success("success");
    }

    @PostMapping("/deleteById")
    public R delete(@RequestBody Role role) {
            roleService.removeById(role);
        return R.success("success");
    }

    @PostMapping("/deleteIds")
    public R deleteIds(@RequestBody Long[] ids) {
            roleService.removeByIds(Arrays.asList(ids));
        return R.success("success");
    }

}