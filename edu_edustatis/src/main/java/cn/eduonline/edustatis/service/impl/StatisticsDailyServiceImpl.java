package cn.eduonline.edustatis.service.impl;

import cn.eduonline.common.R;
import cn.eduonline.edustatis.client.UcenterClient;
import cn.eduonline.edustatis.entity.StatisticsDaily;
import cn.eduonline.edustatis.mapper.StatisticsDailyMapper;
import cn.eduonline.edustatis.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-27
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;

    /**根据某一天得到注册人数，添加到数据库里面*/
    @Override
    public void createStatisData(String day) {

        //删除添加的日期的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        R r = ucenterClient.countRegisterNum(day);
        Integer count = (Integer)r.getData().get("count");
        //得到某天注册人数，添加到统计分析表里面
        StatisticsDaily daily = new StatisticsDaily();
        daily.setDateCalculated(day);
        daily.setRegisterNum(count);
        //课程数
        Integer courseNum = RandomUtils.nextInt(100, 200);
        daily.setCourseNum(courseNum);
        //登录人数
        Integer loginNum = RandomUtils.nextInt(100, 200);
        daily.setLoginNum(loginNum);
        //视频播放数
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        daily.setVideoViewNum(videoViewNum);

        //创建时间
        Date gmtCreate = Calendar.getInstance().getTime();
        daily.setGmtCreate(gmtCreate);

        //修改时间
        Date gmtModified = Calendar.getInstance().getTime();
        daily.setGmtModified(gmtModified);

        baseMapper.insert(daily);
    }

    /**根据时间范围查询统计的数据*/
    @Override
    public Map<String, Object> getChartsData(String type, String begin, String end) {
        //根据时间范围查询统计数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        //选择需要查询显示的字段
        wrapper.select("date_calculated",type);
        wrapper.between("date_calculated",begin,end);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);

        // 日期 ['2019-01-01','2019-01-09']
        List<String> dateList = new ArrayList<>();
        // 数据 [100,230]
        List<Integer> numList = new ArrayList<>();

        //遍历查询出来的集合，从集合获取数据封装到list集合
        for (int i = 0; i < statisticsDailies.size(); i++) {
            //每个对象
            StatisticsDaily daily = statisticsDailies.get(i);
            //得到每个对象中日期
            String dateCalculated = daily.getDateCalculated();
            //放到list集合
            dateList.add(dateCalculated);

            //判断从集合中获取具体数据
            switch (type) {
                //注册人数
                case "register_num":
                    Integer registerNum = daily.getRegisterNum();
                    numList.add(registerNum);
                    break;
                case "login_num":
                    Integer loginNum = daily.getLoginNum();
                    numList.add(loginNum);
                    break;
                case "video_view_num":
                    Integer videoViewNum = daily.getVideoViewNum();
                    numList.add(videoViewNum);
                    break;
                case "course_num":
                    Integer courseNum = daily.getCourseNum();
                    numList.add(courseNum);
                    break;
                default:
                    break;
            }
        }
        //创建map集合，用于最终数据存储
        Map<String,Object> map = new HashMap<>(256);
        //把封装之后的两个list集合放到map里面
        map.put("dateList",dateList);
        map.put("numList",numList);
        return map;
    }
}
