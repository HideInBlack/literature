package com.literature.service.Impl;


import com.literature.dao.UserDao;
import com.literature.pojo.User;
import com.literature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 */
@Service
public class UserServiceImpl implements UserService {

    //业务层注入、调用持久层
    @Autowired
    UserDao userDao;


    @Override
    public List<User> listUser() {

        return userDao.listUser();
    }

    @Override
    public User checkUserLogin(User user) {

        return userDao.getUserByEmail(user);
    }

    @Override
    public User getUserById(int id) {

        return userDao.getUserById(id);
    }

    @Override
    public int insertUser(User user) {

        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
