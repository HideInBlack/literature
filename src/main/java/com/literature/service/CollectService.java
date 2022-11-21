package com.literature.service;

import com.literature.pojo.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {

    //查询此用户所有的收藏纪录
    List<Collect> listCollect(@Param("userId")int userId);

    //查询此阅读纪录是否存在
    int checkCollect(Collect collect);

    //如果纪录不存在测插入
    int insertCollect(Collect collect);

    //删除阅读纪录
    int deleteCollect(@Param("id")int id);

}
