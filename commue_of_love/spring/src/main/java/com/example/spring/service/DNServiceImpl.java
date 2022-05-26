package com.example.spring.service;

import com.example.spring.bean.DN;
import com.example.spring.dao.DNDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DNServiceImpl implements DNService {
    @Autowired
    private DNDao dnDao;

    @Override
    public boolean updateAudited(int id, String audited) {
        boolean flag = false;
        try {
            dnDao.updateAudited(id, audited);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateInfo(DN dn) {
        boolean flag = false;
        try {
            dnDao.updateInfo(dn);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<DN> findAll() {
        return dnDao.findAll();
    }

    @Override
    public DN findByID(int id) {
        return dnDao.findByID(id);
    }

    @Override
    public List<DN> findByAudited(String audited) {
        return dnDao.findByAudited(audited);
    }

    @Override
    public List<DN> findByUid(String uid) {
        return dnDao.findByUid(uid);
    }

    @Override
    public List<DN> findByKeyword(String keyword) {
        return dnDao.findByKeyword(keyword);
    }

}
