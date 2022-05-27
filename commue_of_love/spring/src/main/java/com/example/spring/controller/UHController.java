package com.example.spring.controller;

import com.example.spring.pojo.UH;
import com.example.spring.result.Result;
import com.example.spring.service.UHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Objects;

@RestController
public class UHController {
    @Autowired
    private UHService uhService;

    @RequestMapping("/api/uh/all")
    public List<UH> findAll() throws Exception {
        System.out.println("查询所有数据:");
        return uhService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/api/uh/unaudited")
    @ResponseBody
    public List<UH> findByAudited(@RequestBody UH requestUH) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String audit = requestUH.getAudited();
        audit = HtmlUtils.htmlEscape(audit);

        System.out.println("根据审核状态查询数据:audit=" + audit);
        if(audit.equals("unaudited")){
            return uhService.findByAudited("待审核");
        }else{
            return uhService.findByAudited("已审核");
        }
    }

    @CrossOrigin
    @PostMapping("/api/uh/audited")
    @ResponseBody
    public boolean updateAudited(@RequestBody UH requestUH) {
        int id = requestUH.getId();
        String audited = requestUH.getAudited();
        System.out.println("id=" + id);
        System.out.println("audited=" + audited);

        if ("passed".equals(audited)) {
            System.out.println("通过");
            return true;
//            return uhService.updateAudited(id, "已通过");
        } else {
            System.out.println("驳回");
            return false;
//            return uhService.updateAudited(id, "已驳回");
        }
    }

    @CrossOrigin
    @PostMapping("/api/uh/modify")
    @ResponseBody
    public boolean updateInfo(@RequestBody UH requestUH) {
        System.out.println("id=" + requestUH.getId());
        System.out.println("修改");

        return uhService.updateInfo(requestUH);
    }

//    @RequestMapping(value = "/findByID", method = RequestMethod.GET)
//    public UH findByID(@RequestParam(value = "id", required = true) int id) {
//        System.out.println("ID:"+id);
//        System.out.println("根据ID查询数据:");
//        return uhService.findByID(id);
//    }

//    @RequestMapping(value = "/findByUid", method = RequestMethod.GET)
//    public List<UH> findByUid(@RequestParam(value = "uid", required = true) String uid) {
//        System.out.println("发起人账号:"+uid);
//        System.out.println("根据发起人账号查询数据:");
//        return uhService.findByAudited(uid);
//    }
//
//    @RequestMapping(value = "/findByKeyword", method = RequestMethod.GET)
//    public List<UH> findByKeyword(@RequestParam(value = "keyword", required = true) String keyword) {
//        System.out.println("关键词:"+keyword);
//        System.out.println("根据关键词模糊查询数据:");
//        return uhService.findByKeyword(keyword);
//    }

    @CrossOrigin
    @PostMapping(value = "/test")
    @ResponseBody
    public Result test(@RequestBody UH requestUH) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String audit = requestUH.getAudited();
        audit = HtmlUtils.htmlEscape(audit);

        System.out.println("username=" + audit);

        if (!Objects.equals("待审核", audit)) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}
