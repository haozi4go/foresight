package com.haozi.foresight.database.redis.ctrl;

import com.haozi.foresight.database.redis.service.CourseConvertSynchronizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuenhao
 * @date 2020/12/6 下午4:15
 */
@RestController
public class DemoController {
    @Resource
    private CourseConvertSynchronizer courseConvertSynchronizer;

    @GetMapping("/listSubmittedCourseId")
    public List<String> listSubmittedCourseId(){
        return courseConvertSynchronizer.listSubmittedCourseId();
    }

    @GetMapping("/addSubmittedCourseId")
    public Long addSubmittedCourseId(Long courseId){
        return courseConvertSynchronizer.addSubmittedCourseId(courseId);
    }

    @GetMapping("/getAndRemoveOldestSubmittedCourseId")
    public Long getAndRemoveOldestSubmittedCourseId(){
        return courseConvertSynchronizer.getAndRemoveOldestSubmittedCourseId();
    }

    @GetMapping("/isExistedSubmittedCourseId")
    public Boolean isExistedSubmittedCourseId(Long courseId){
        return courseConvertSynchronizer.isExistedSubmittedCourseId(courseId);
    }
}
