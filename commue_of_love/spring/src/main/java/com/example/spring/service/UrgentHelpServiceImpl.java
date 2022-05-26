package com.example.spring.service;

import com.example.spring.bean.UrgentHelp;
import com.example.spring.dao.UrgentHelpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrgentHelpServiceImpl implements UrgentHelpService{
    @Autowired
    private UrgentHelpDao urgentHelpDao;

    @Override
    public boolean updateAudited(int id, String audited) {
        boolean flag = false;
        try {
            urgentHelpDao.updateAudited(id, audited);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateCompleted(int id, String completed) {
        boolean flag = false;
        try {
            urgentHelpDao.updateCompleted(id, completed);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateInfo(UrgentHelp urgentHelp) {
        boolean flag = false;
        try {
            urgentHelpDao.updateInfo(urgentHelp);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<UrgentHelp> findAll() {
        return urgentHelpDao.findAll();
    }

    @Override
    public UrgentHelp findByID(int id) {
        return urgentHelpDao.findByID(id);
    }

    @Override
    public List<UrgentHelp> findByAudited(String audited) {
        return urgentHelpDao.findByAudited(audited);
    }

    @Override
    public List<UrgentHelp> findByUid(String uid) {
        return urgentHelpDao.findByUid(uid);
    }

    @Override
    public List<UrgentHelp> findByKeyword(String keyword) {
        return urgentHelpDao.findByKeyword(keyword);
    }

}
