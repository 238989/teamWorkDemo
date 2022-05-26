package com.example.spring.controller;

import com.example.spring.bean.UH;
import com.example.spring.service.UHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/uh")
public class UHController {
    @Autowired
    private UHService uhService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<UH> findAll() {
        System.out.println("查询所有数据:");
        return uhService.findAll();
    }

    @RequestMapping(value = "/findByID", method = RequestMethod.GET)
    public UH findByID(@RequestParam(value = "id", required = true) int id) {
        System.out.println("ID:"+id);
        System.out.println("根据ID查询数据:");
        return uhService.findByID(id);
    }

//    @RequestMapping(value = "/findByAudited", method = RequestMethod.GET)
//    public List<UH> findByAudited(@RequestParam(value = "audited", required = true) String audited){
//        System.out.println("审核状态:"+audited);
//        System.out.println("根据审核状态查询数据:");
//        if(audited.equals("unaudited")){
//            return UHService.findByAudited("待审核");
//        }else{
//            return UHService.findByAudited("已审核");
//        }
//    }

    @CrossOrigin
    @GetMapping("/findByAudited")
    public List<UH> findByAudited(@RequestParam("audited") String audited) {
        System.out.println("审核状态:"+audited);
        System.out.println("根据审核状态查询数据:");
        if(audited.equals("unaudited")){
            return uhService.findByAudited("待审核");
        }else{
            return uhService.findByAudited("已审核");
        }
    }


//    @CrossOrigin
//    @PostMapping(value = "api/findByAudited")
//    // 设置POST接收数据
//    @ResponseBody
//    public List<UH> findByAudited(@RequestBody String audited){
//        System.out.println("审核状态:"+audited);
//        System.out.println("根据审核状态查询数据:");
//        if(audited.equals("unaudited")){
//            return UHService.findByAudited("待审核");
//        }else{
//            return UHService.findByAudited("已审核");
//        }
//    }

    @RequestMapping(value = "/findByUid", method = RequestMethod.GET)
    public List<UH> findByUid(@RequestParam(value = "uid", required = true) String uid) {
        System.out.println("发起人账号:"+uid);
        System.out.println("根据发起人账号查询数据:");
        return uhService.findByAudited(uid);
    }

    @RequestMapping(value = "/findByKeyword", method = RequestMethod.GET)
    public List<UH> findByKeyword(@RequestParam(value = "keyword", required = true) String keyword) {
        System.out.println("关键词:"+keyword);
        System.out.println("根据关键词模糊查询数据:");
        return uhService.findByKeyword(keyword);
    }

    @RequestMapping(value = "/updateAudited", method = RequestMethod.PUT)
    public boolean updateAudited(@RequestBody int id, String audited) {
        System.out.println("修改审核状态：");
        return uhService.updateAudited(id, audited);
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public boolean updateInfo(@RequestBody UH uh) {
        System.out.println("修改其他信息：");
        return uhService.updateInfo(uh);
    }
}
