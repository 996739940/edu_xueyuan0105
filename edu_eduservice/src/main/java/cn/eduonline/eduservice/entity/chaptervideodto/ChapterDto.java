package cn.eduonline.eduservice.entity.chaptervideodto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterDto
 * @Description 章节
 * @Author 张燕廷
 * @Date 2020/2/7 18:10
 * @Version 1.0
 **/
@Data
public class ChapterDto {

    private String id;
    private String title;
    //章节里面的很多的小节
    private List<VideoDto> children = new ArrayList<>();
}
