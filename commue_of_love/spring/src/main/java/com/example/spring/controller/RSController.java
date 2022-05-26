package com.example.spring.controller;

import com.example.spring.bean.RS;
import com.example.spring.service.RSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rs")
public class RSController {
    @Autowired
    private RSService rsService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<RS> findAll() {
        System.out.println("查询所有数据:");
        return rsService.findAll();
    }

    @RequestMapping(value = "/findByID", method = RequestMethod.GET)
    public RS findByID(@RequestParam(value = "id", required = true) int id) {
        System.out.println("ID:"+id);
        System.out.println("根据ID查询数据:");
        return rsService.findByID(id);
    }

    @CrossOrigin
    @GetMapping("/findByAudited")
    public List<RS> findByAudited(@RequestParam("audited") String audited) {
        System.out.println("审核状态:"+audited);
        System.out.println("根据审核状态查询数据:");
        if(audited.equals("unaudited")){
            return rsService.findByAudited("待审核");
        }else{
            return rsService.findByAudited("已审核");
        }
    }

    @RequestMapping(value = "/findByUid", method = RequestMethod.GET)
    public List<RS> findByUid(@RequestParam(value = "uid", required = true) String uid) {
        System.out.println("发起人账号:"+uid);
        System.out.println("根据发起人账号查询数据:");
        return rsService.findByAudited(uid);
    }

    @RequestMapping(value = "/findByKeyword", method = RequestMethod.GET)
    public List<RS> findByKeyword(@RequestParam(value = "keyword", required = true) String keyword) {
        System.out.println("关键词:"+keyword);
        System.out.println("根据关键词模糊查询数据:");
        return rsService.findByKeyword(keyword);
    }

    @RequestMapping(value = "/updateAudited", method = RequestMethod.PUT)
    public boolean updateAudited(@RequestBody int id, String audited) {
        System.out.println("修改审核状态：");
        return rsService.updateAudited(id, audited);
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public boolean updateInfo(@RequestBody RS rs) {
        System.out.println("修改其他信息：");
        return rsService.updateInfo(rs);
    }
}
