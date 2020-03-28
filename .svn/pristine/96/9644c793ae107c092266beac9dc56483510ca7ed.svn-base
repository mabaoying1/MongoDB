package com.healthpay.modules.sys.service;

import com.healthpay.modules.sys.dao.SsoUserDao;
import com.healthpay.modules.sys.entity.SsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2018/3/28.
 */
@Service
@Transactional(readOnly = false)
public class SsoUserService {
    @Autowired
    SsoUserDao ssoUserDao;

    public SsoUser getByIdNumber(String idNumber){
        SsoUser ssoUser = ssoUserDao.getByIdNumber(idNumber);
        return ssoUser;
    }

    public void save(SsoUser ssoUser){
        ssoUserDao.insert(ssoUser);
    }

}