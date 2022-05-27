package com.example.spring.service;

import com.example.spring.pojo.UH;
import com.example.spring.dao.UHDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UHServiceImpl implements UHService {
    @Autowired
    private UHDao uhDao;

    @Override
    public boolean updateAudited(int id, String audited) {
        boolean flag = false;
        try {
            uhDao.updateAudited(id, audited);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateInfo(UH uh) {
        boolean flag = false;
        try {
            uhDao.updateInfo(uh);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<UH> findAll() {
        return uhDao.findAll();
    }

    @Override
    public UH findByID(int id) {
        return uhDao.findByID(id);
    }

    @Override
    public List<UH> findByAudited(String audited) {
        return uhDao.findByAudited(audited);
    }

    @Override
    public List<UH> findByUid(String uid) {
        return uhDao.findByUid(uid);
    }

    @Override
    public List<UH> findByKeyword(String keyword) {
        return uhDao.findByKeyword(keyword);
    }

}
