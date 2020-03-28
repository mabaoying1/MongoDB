package com.healthpay.modules.sys.service;

import com.healthpay.modules.sys.dao.UserDao;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by admin on 2017/11/30.
 */
@Service
@Transactional(readOnly = false)
public class UserService {
    @Autowired
    private UserDao userDao;

    //保存连续登陆失败次数
    public void  update(User user){
        User oldUser = userDao.getByLoginName(user);
        userDao.update(user);
        UserUtils.clearCache(oldUser);
    }

    public User getUserByUsername(String username){
        User user = userDao.getByLoginName(new User(null,username));
        return user;
    }
}
