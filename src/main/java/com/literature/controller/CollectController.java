package com.literature.controller;

import com.literature.pojo.Collect;
import com.literature.pojo.Work;
import com.literature.service.CollectService;
import com.literature.service.WorkService;
import com.literature.util.LayuiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 收藏夹的操作容器
 */
@Controller
public class CollectController {

    @Autowired
    CollectService collectService;

    @Autowired
    WorkService workService;


    /**
     * 查找我的收藏作品
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMyCollect")
    public Map<String, Object> myCollect(HttpSession session) {

        int userId = (int) session.getAttribute("loginUser");
        List<Collect> list = collectService.listCollect(userId);

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());

    }

    /**
     * 移除收藏作品
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCollect")
    public Map<String, Object> deleteCollect(@RequestParam(value = "id") int id) {

        int result = collectService.deleteCollect(id);
        if (result > 0){
            return LayuiUtil.setResultMap(0, "删除成功", "",0);
        }else{
            return LayuiUtil.setResultMap(1, "操作失败", "",0);
        }


    }

    @ResponseBody
    @RequestMapping("/addCollect")
    public Map<String, Object> addCollect(@RequestParam(value = "workId") int workId, HttpSession session) {

        //获取当前修改更新时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = (String) df.format(new Date());

        int userId = (int) session.getAttribute("loginUser");
        List<Collect> list = collectService.listCollect(userId);

        Work work= workService.getOneWork(workId);
        if (work.getAuthorId() != userId){//如果访问的是自己的作品，不会被添加到收藏纪录

            Collect collect = new Collect();
            collect.setUserId(userId);
            collect.setWorkId(workId);
            collect.setCollectTime(time);
            int readResult = collectService.checkCollect(collect);//核实是否已存在收藏

            if(readResult > 0){ //说明已经存在纪录，无需再次收藏
                return LayuiUtil.setResultMap(1, "作品已在收藏夹中，无需再次收藏", "", 0);
            }else{//说明没有阅读纪录，需要添加记录

                if (collectService.insertCollect(collect)>0){
                    return LayuiUtil.setResultMap(0, "成功收藏此作品，可在收藏夹中查看", "", 0);
                }else{
                    return LayuiUtil.setResultMap(1, "数据请求失败", "", 0);
                }
            }
        }else{
            return LayuiUtil.setResultMap(1, "不需要收藏自己的作品！", "", 0);
        }



    }
}
