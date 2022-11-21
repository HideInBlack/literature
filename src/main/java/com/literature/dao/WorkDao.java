package com.literature.dao;


import com.literature.pojo.Work;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作品持久层接口
 */
@Mapper
@Repository
public interface WorkDao {

    //插入作品
    int insertWork(Work work);

    //根据用户id查找其所有作品
    List<Work> getUserWork(@Param(value = "userId")int userId,
                           @Param(value = "page")int page ,
                           @Param(value = "limit") int limit,
                           @Param(value = "count") String count);

    //查询所有待审核的
    List<Work> getAllPermit();

    //智能推荐两个相同类型
    List<Work> getTwo(@Param(value = "id")int id,
                      @Param(value = "type")String type);

    //查找所有用户的所有作品 hot:热门推荐，keyWord:关键字模糊查询，keyType：关键字模糊查询
    List<Work> getAllWork(@Param(value = "hot")String hot,
                          @Param(value = "newest")String newest,
                          @Param(value = "keyWord")String keyWord,
                          @Param(value = "keyType")String keyType);

    //查看一个作品
    Work getOneWork(@Param(value = "workId")int workId);

    //修改作品
    int updateWork(Work work);

    //删除作品
    int deleteWork(Work work);

    //阅读量自增1
    int addOne(int workId);
}
