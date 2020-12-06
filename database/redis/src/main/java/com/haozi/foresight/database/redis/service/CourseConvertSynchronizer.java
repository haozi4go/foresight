package com.haozi.foresight.database.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuenhao
 * @date 2020/12/5 下午8:43
 */
@Component
public class CourseConvertSynchronizer {
    private final String submittedCourseIdsKey = "miaocai:migration:submittedCourseId";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Long addSubmittedCourseId(Long courseId){
        try {
            if (isExistedSubmittedCourseId(courseId)){
                return courseId;
            }
            return stringRedisTemplate.opsForList().rightPush(submittedCourseIdsKey,String.valueOf(courseId));
        } catch (Exception e){
            return null;
        }
    }

    public Boolean isExistedSubmittedCourseId(Long courseId){
        Assert.notNull(courseId,"courseId can not null");
        try {
            return this.listSubmittedCourseId().contains(String.valueOf(courseId));
        } catch (Exception e){
            return false;
        }
    }

    public List<String> listSubmittedCourseId(){
        try {
            return stringRedisTemplate.opsForList().range(submittedCourseIdsKey,0,stringRedisTemplate.opsForList().size(submittedCourseIdsKey));
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Long getAndRemoveOldestSubmittedCourseId(){
        try {
            return Long.valueOf(stringRedisTemplate.opsForList().leftPop(submittedCourseIdsKey, Duration.ofSeconds(30)));
        } catch (Exception e){
            return null;
        }
    }


}
