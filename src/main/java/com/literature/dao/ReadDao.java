package com.literature.dao;

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
public interface ReadDao {

    //查询此用户所有的阅读纪录
    List<Read> listRead(@Param("userId")int userId);

    //查询此阅读纪录是否存在
    int getOneRead(Read read);

    //如果纪录不存在测插入
    int insertRead(Read read);

    //如果纪录存在则更新最新查看时间
    int updateRead(Read read);

    //删除阅读纪录
    int deleteRead(@Param("id")int id);

}
