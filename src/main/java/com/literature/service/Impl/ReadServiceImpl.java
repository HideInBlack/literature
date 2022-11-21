package com.literature.service.Impl;

import com.literature.dao.ReadDao;
import com.literature.pojo.Read;
import com.literature.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 */
@Service
public class ReadServiceImpl implements ReadService {

    @Autowired
    ReadDao readDao ;

    @Override
    public List<Read> listRead(int userId) {
        return readDao.listRead(userId);
    }

    @Override
    public int checkRead(Read read) {
        return readDao.getOneRead(read);
    }

    @Override
    public int insertRead(Read read) {
        return readDao.insertRead(read);
    }

    @Override
    public int updateRead(Read read) {
        return readDao.updateRead(read);
    }

    @Override
    public int deleteRead(int id) {
        return readDao.deleteRead(id);
    }
}
