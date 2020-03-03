package cn.eduonline.edustatis.controller;


import cn.eduonline.common.R;
import cn.eduonline.edustatis.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/edustatistics/countData")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;

    /**根据时间范围查询统计的数据*/
    @GetMapping("showCharts/{type}/{begin}/{end}")
    public R showChartsData(@PathVariable String type,
                            @PathVariable String begin,
                            @PathVariable String end) {
        Map<String,Object> map = dailyService.getChartsData(type,begin,end);
        return R.ok().data(map);
    }

    /**根据某一天获取注册人数，添加到数据库里面*/
    @GetMapping("createData/{day}")
    public R createData(@PathVariable String day) {
        dailyService.createStatisData(day);
        return R.ok();
    }
}

