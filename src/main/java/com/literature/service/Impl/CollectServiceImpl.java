package com.literature.service.Impl;

import com.literature.dao.CollectDao;
import com.literature.pojo.Collect;
import com.literature.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectDao collectDao;

    @Override
    public List<Collect> listCollect(int userId) {
        return collectDao.listCollect(userId);
    }

    @Override
    public int checkCollect(Collect collect) {
        return collectDao.checkCollect(collect);
    }

    @Override
    public int insertCollect(Collect collect) {
        return collectDao.insertCollect(collect);
    }

    @Override
    public int deleteCollect(int id) {
        return collectDao.deleteCollect(id);
    }
}
