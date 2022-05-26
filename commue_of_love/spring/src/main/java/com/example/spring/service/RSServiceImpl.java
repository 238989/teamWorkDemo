package com.example.spring.service;

import com.example.spring.bean.RS;
import com.example.spring.dao.RSDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RSServiceImpl implements RSService {
    @Autowired
    private RSDao rsDao;

    @Override
    public boolean updateAudited(int id, String audited) {
        boolean flag = false;
        try {
            rsDao.updateAudited(id, audited);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateInfo(RS rs) {
        boolean flag = false;
        try {
            rsDao.updateInfo(rs);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<RS> findAll() {
        return rsDao.findAll();
    }

    @Override
    public RS findByID(int id) {
        return rsDao.findByID(id);
    }

    @Override
    public List<RS> findByAudited(String audited) {
        return rsDao.findByAudited(audited);
    }

    @Override
    public List<RS> findByUid(String uid) {
        return rsDao.findByUid(uid);
    }

    @Override
    public List<RS> findByKeyword(String keyword) {
        return rsDao.findByKeyword(keyword);
    }

}
