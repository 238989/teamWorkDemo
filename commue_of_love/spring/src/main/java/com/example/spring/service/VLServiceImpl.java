package com.example.spring.service;

import com.example.spring.bean.VL;
import com.example.spring.dao.VLDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VLServiceImpl implements VLService {
    @Autowired
    private VLDao vlDao;

    @Override
    public boolean updateAudited(int id, String audited) {
        boolean flag = false;
        try {
            vlDao.updateAudited(id, audited);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateInfo(VL vl) {
        boolean flag = false;
        try {
            vlDao.updateInfo(vl);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<VL> findAll() {
        return vlDao.findAll();
    }

    @Override
    public VL findByID(int id) {
        return vlDao.findByID(id);
    }

    @Override
    public List<VL> findByAudited(String audited) {
        return vlDao.findByAudited(audited);
    }

    @Override
    public List<VL> findByUid(String uid) {
        return vlDao.findByUid(uid);
    }

    @Override
    public List<VL> findByKeyword(String keyword) {
        return vlDao.findByKeyword(keyword);
    }

}
