package com.example.spring.controller;

import com.example.spring.pojo.DN;
import com.example.spring.service.DNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class DNController {
    @Autowired
    private DNService dnService;

    @RequestMapping("/api/dn/all")
    public List<DN> findAll() throws Exception {
        System.out.println("查询所有数据:");
        return dnService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/api/dn/unaudited")
    @ResponseBody
    public List<DN> findByAudited(@RequestBody DN requestDN) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String audit = requestDN.getAudited();
        audit = HtmlUtils.htmlEscape(audit);

        System.out.println("根据审核状态查询数据:audit=" + audit);
        if(audit.equals("unaudited")){
            return dnService.findByAudited("待审核");
        }else{
            return dnService.findByAudited("已审核");
        }
    }

    @CrossOrigin
    @PostMapping("/api/dn/audited")
    @ResponseBody
    public boolean updateAudited(@RequestBody DN requestDN) {
        int id = requestDN.getId();
        String audited = requestDN.getAudited();
        System.out.println("id=" + id);
        System.out.println("audited=" + audited);

        if ("passed".equals(audited)) {
            System.out.println("通过");
            return true;
//            return dnService.updateAudited(id, "已通过");
        } else {
            System.out.println("驳回");
            return false;
//            return dnService.updateAudited(id, "已驳回");
        }
    }

    @CrossOrigin
    @PostMapping("/api/dn/modify")
    @ResponseBody
    public boolean updateInfo(@RequestBody DN requestDN) {
        System.out.println("id=" + requestDN.getId());
        System.out.println("修改");

        return dnService.updateInfo(requestDN);
    }
}
