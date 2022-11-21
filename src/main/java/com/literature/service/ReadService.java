package com.literature.service;

import com.literature.pojo.Read;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReadService {

    //查询此用户所有的阅读纪录
    List<Read> listRead(int userId);

    //核查此阅读纪录是否存在
    int checkRead(Read read);

    //如果纪录不存在测插入
    int insertRead(Read read);

    //如果纪录存在则更新最新查看时间
    int updateRead(Read read);

    //删除阅读纪录
    int deleteRead(int id);
}
