package com.literature.dao;

import com.literature.pojo.Collect;
import com.literature.pojo.Read;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久层接口
 */
@Mapper
@Repository
public interface CollectDao {

    //查询此用户所有的收藏纪录
    List<Collect> listCollect(@Param("userId")int userId);

    //查询此阅读纪录是否存在
    int checkCollect(Collect collect);

    //如果纪录不存在测插入
    int insertCollect(Collect collect);

    //删除阅读纪录
    int deleteCollect(@Param("id")int id);
}
