package com.literature.service;

import com.literature.pojo.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务层逻辑接口
 */
public interface WorkService {

    //插入作品
    int insertWork(Work work);

    //根据用户id查找其所有作品
    List<Work> getUserWork( int userId,int page ,int limit ,String count);

    //查询所有待审核的
    List<Work> getAllPermit();

    //智能推荐两个相同类型
    List<Work> getTwo(int id, String type);

    //查找所有用户的所有作品
    List<Work> getAllWork(String hot,String newest, String keyWord, String keyType);

    //查看一个作品
    Work getOneWork(int workId);

    //修改作品
    int updateWork(Work work);

    //删除作品
    int deleteWork(Work work);

    //阅读量自增1
    int addOne(int workId);

}
