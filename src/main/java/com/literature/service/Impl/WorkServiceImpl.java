package com.literature.service.Impl;

import com.literature.dao.WorkDao;
import com.literature.pojo.Work;
import com.literature.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 */
@Service
public class WorkServiceImpl implements WorkService {

    //注入Dao
    @Autowired
    WorkDao workDao;


    @Override
    public int insertWork(Work work) {
        return workDao.insertWork(work);
    }

    @Override
    public List<Work> getUserWork(int userId ,int page ,int limit,String count) {
        return workDao.getUserWork(userId,page ,limit,count);
    }

    @Override
    public List<Work> getAllPermit() {
        return workDao.getAllPermit();
    }

    @Override
    public List<Work> getTwo(int id, String type) {
        return workDao.getTwo(id ,type);
    }

    @Override
    public List<Work> getAllWork(String hot,String newest, String keyWord, String keyType) {
        return workDao.getAllWork(hot,newest ,keyWord ,keyType);
    }

    @Override
    public Work getOneWork(int workId) {
        return workDao.getOneWork(workId);
    }

    @Override
    public int updateWork(Work work) {
        return workDao.updateWork(work);
    }

    @Override
    public int deleteWork(Work work) {
        return workDao.deleteWork(work);
    }

    @Override
    public int addOne(int workId) {
        return workDao.addOne(workId);
    }


}
