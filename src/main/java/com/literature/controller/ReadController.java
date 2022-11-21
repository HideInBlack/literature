package com.literature.controller;

import com.literature.pojo.Read;
import com.literature.service.ReadService;
import com.literature.util.LayuiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 阅读纪录的操作容器
 */
@Controller
public class ReadController {

    @Autowired
    ReadService readService;

    /**
     * 我的阅读纪录
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/readMy")
    public Map<String, Object> readMy(HttpSession session) {

        int userId = (int) session.getAttribute("loginUser");
        List<Read> list = readService.listRead(userId);

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }


    /**
     * 删除阅读纪录
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRead")
    public Map<String, Object> deleteRead(@RequestParam(value = "id") int id) {

        int result = readService.deleteRead(id);
        if (result > 0){
            return LayuiUtil.setResultMap(0, "删除成功", "",0);
        }else{
            return LayuiUtil.setResultMap(1, "操作失败", "",0);
        }


    }

}


