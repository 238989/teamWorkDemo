package com.example.spring.controller;

import com.example.spring.pojo.VL;
import com.example.spring.service.VLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class VLController {
    @Autowired
    private VLService vlService;

    @RequestMapping("/api/vl/all")
    public List<VL> findAll() throws Exception {
        System.out.println("查询所有数据:");
        return vlService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/api/vl/unaudited")
    @ResponseBody
    public List<VL> findByAudited(@RequestBody VL requestVL) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String audit = requestVL.getAudited();
        audit = HtmlUtils.htmlEscape(audit);

        System.out.println("根据审核状态查询数据:audit=" + audit);
        if(audit.equals("unaudited")){
            return vlService.findByAudited("待审核");
        }else{
            return vlService.findByAudited("已审核");
        }
    }

    @CrossOrigin
    @PostMapping("/api/vl/audited")
    @ResponseBody
    public boolean updateAudited(@RequestBody VL requestVL) {
        int id = requestVL.getId();
        String audited = requestVL.getAudited();
        System.out.println("id=" + id);
        System.out.println("audited=" + audited);

        if ("passed".equals(audited)) {
            System.out.println("通过");
            return true;
//            return vlService.updateAudited(id, "已通过");
        } else {
            System.out.println("驳回");
            return false;
//            return vlService.updateAudited(id, "已驳回");
        }
    }

    @CrossOrigin
    @PostMapping("/api/vl/modify")
    @ResponseBody
    public boolean updateInfo(@RequestBody VL requestVL) {
        System.out.println("id=" + requestVL.getId());
        System.out.println("修改");

        return vlService.updateInfo(requestVL);
    }
}
