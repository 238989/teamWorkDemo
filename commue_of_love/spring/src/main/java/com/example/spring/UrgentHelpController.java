package com.example.spring;

import com.example.spring.bean.UrgentHelp;
import com.example.spring.bean.User;
import com.example.spring.service.UrgentHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/urgent_help")
public class UrgentHelpController {
    @Autowired
    private UrgentHelpService urgentHelpService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<UrgentHelp> findAll() {
        System.out.println("查询所有数据:");
        return urgentHelpService.findAll();
    }

    @RequestMapping(value = "/findByID", method = RequestMethod.GET)
    public UrgentHelp findByID(@RequestParam(value = "id", required = true) int id) {
        System.out.println("ID:"+id);
        System.out.println("根据ID查询数据:");
        return urgentHelpService.findByID(id);
    }

    @RequestMapping(value = "/findByAudited/", method = RequestMethod.GET)
    public List<UrgentHelp> findByAudited(@RequestParam(value = "audited", required = true) String audited){
        System.out.println("审核状态:"+audited);
        System.out.println("根据审核状态查询数据:");
        if(audited.equals("unaudited")){
            return urgentHelpService.findByAudited("待审核");
        }else{
            return urgentHelpService.findByAudited("已审核");
        }
    }

    @RequestMapping(value = "/findByUid", method = RequestMethod.GET)
    public List<UrgentHelp> findByUid(@RequestParam(value = "uid", required = true) String uid) {
        System.out.println("发起人账号:"+uid);
        System.out.println("根据发起人账号查询数据:");
        return urgentHelpService.findByAudited(uid);
    }

    @RequestMapping(value = "/findByKeyword", method = RequestMethod.GET)
    public List<UrgentHelp> findByKeyword(@RequestParam(value = "keyword", required = true) String keyword) {
        System.out.println("关键词:"+keyword);
        System.out.println("根据关键词模糊查询数据:");
        return urgentHelpService.findByKeyword(keyword);
    }

    @RequestMapping(value = "/updateAudited", method = RequestMethod.PUT)
    public boolean updateAudited(@RequestBody int id, String audited) {
        System.out.println("修改审核状态：");
        return urgentHelpService.updateAudited(id, audited);
    }

    @RequestMapping(value = "/updateCompleted", method = RequestMethod.PUT)
    public boolean updateCompleted(@RequestBody int id, String completed) {
        System.out.println("修改进度：");
        return urgentHelpService.updateCompleted(id, completed);
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public boolean updateInfo(@RequestBody UrgentHelp urgentHelp) {
        System.out.println("修改其他信息：");
        return urgentHelpService.updateInfo(urgentHelp);
    }
}
