package com.example.spring.controller;

import com.example.spring.pojo.RS;
import com.example.spring.service.RSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class RSController {
    @Autowired
    private RSService rsService;

    @RequestMapping("/api/rs/all")
    public List<RS> findAll() throws Exception {
        System.out.println("查询所有数据:");
        return rsService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/api/rs/unaudited")
    @ResponseBody
    public List<RS> findByAudited(@RequestBody RS requestRS) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String audit = requestRS.getAudited();
        audit = HtmlUtils.htmlEscape(audit);

        System.out.println("根据审核状态查询数据:audit=" + audit);
        if(audit.equals("unaudited")){
            return rsService.findByAudited("待审核");
        }else{
            return rsService.findByAudited("已审核");
        }
    }

    @CrossOrigin
    @PostMapping("/api/rs/audited")
    @ResponseBody
    public boolean updateAudited(@RequestBody RS requestRS) {
        int id = requestRS.getId();
        String audited = requestRS.getAudited();
        System.out.println("id=" + id);
        System.out.println("audited=" + audited);

        if ("passed".equals(audited)) {
            System.out.println("通过");
            return true;
//            return rsService.updateAudited(id, "已通过");
        } else {
            System.out.println("驳回");
            return false;
//            return rsService.updateAudited(id, "已驳回");
        }
    }

    @CrossOrigin
    @PostMapping("/api/rs/modify")
    @ResponseBody
    public boolean updateInfo(@RequestBody RS requestRS) {
        System.out.println("id=" + requestRS.getId());
        System.out.println("修改");

        return rsService.updateInfo(requestRS);
    }
}
