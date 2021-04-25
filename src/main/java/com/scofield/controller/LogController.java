package com.scofield.controller;

import com.scofield.service.LogService;
import com.scofield.entity.Log;

import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *  log前端控制器
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    public LogService logService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = logService.queryByPage(params);
        return R.success("success", page);
    }

    @PostMapping("/save")
    public R save(@RequestBody Log log) {
            logService.save(log);
        return R.success("success");
    }

    @PostMapping("/updateById")
    public R update(@RequestBody Log log) {
            logService.updateById(log);
        return R.success("success");
    }

    @PostMapping("/deleteById")
    public R delete(@RequestBody Log log) {
            logService.removeById(log);
        return R.success("success");
    }

    @PostMapping("/deleteIds")
    public R deleteIds(@RequestBody Long[] ids) {
            logService.removeByIds(Arrays.asList(ids));
        return R.success("success");
    }

}