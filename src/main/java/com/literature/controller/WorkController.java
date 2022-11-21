package com.literature.controller;

import com.github.pagehelper.PageHelper;
import com.literature.pojo.Read;
import com.literature.pojo.User;
import com.literature.pojo.Work;
import com.literature.service.ReadService;
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

@Controller
public class WorkController {

    @Autowired
    WorkService workService;

    @Autowired
    ReadService readService;

    /**
     *查询所有用户的所有文学作品
     */
    @ResponseBody
    @RequestMapping("/workListAll")
    public Map<String, Object> workListAll() {
        List<Work> list = workService.getAllWork("" ,"" ,"","");

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }
    /**
     *查询所有用户的待审核文学作品
     */
    @ResponseBody
    @RequestMapping("/workListCheck")
    public Map<String, Object> workListCheck() {
        List<Work> list = workService.getAllWork("" ,"" ,"","check");

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }

    /**
     * 热度查询前八个
     * @return
     */
    @ResponseBody
    @RequestMapping("/workListHot")
    public Map<String, Object> workListHot() {
        List<Work> list = workService.getAllWork("热度查询" ,"" ,"","");

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }

    /**
     * 查询最新八个
     * @return
     */
    @ResponseBody
    @RequestMapping("/workListNew")
    public Map<String, Object> workListNew() {
        List<Work> list = workService.getAllWork("" ,"最新查询" ,"","");

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }

    /**
     * 智能推荐两个相同类型的作品
     * @return
     */
    @ResponseBody
    @RequestMapping("/workTwo")
    public Map<String, Object> workTwo(@RequestParam(value = "workId")int workId,
                                       @RequestParam(value = "type")String type) {
        List<Work> list = workService.getTwo(workId ,type);

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }

    /**
     * 模糊查询
     * @param keyWord
     * @param keyType
     * @return
     */
    @ResponseBody
    @RequestMapping("/workListSearch")
    public Map<String, Object> workListSearch(@RequestParam(value = "keyWord")String keyWord ,
                                              @RequestParam(value = "keyType")String keyType) {
        List<Work> list = workService.getAllWork("" ,"", keyWord ,keyType);

        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }

    /**
     * 新建作品
     * @param work
     * @return
     */
    @ResponseBody
    @RequestMapping("/workCreate")
    public Map<String, Object> workCreate(Work work , HttpSession session){

        //获取当前的注册时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = (String) df.format(new Date());

        int authorId = (int) session.getAttribute("loginUser");
        work.setAuthorId(authorId);
        work.setPublishTime(time);
        work.setUpdateTime(time);

        int result = workService.insertWork(work);
        if (result > 0){
            return LayuiUtil.setResultMap(0, "上传成功，等待管理员审核", "", 0);
        }else{
            return LayuiUtil.setResultMap(1, "接口异常，发布失败", "", 0);
        }

    }

    /**
     * 修改更新作品 / admin批准作品 /admin驳回作品/
     * @param work
     * @return
     */
    @ResponseBody
    @RequestMapping("/workUpdate")
    public Map<String, Object> workUpdate(Work work){

        //获取当前修改更新时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = (String) df.format(new Date());

        work.setUpdateTime(time);

        int result = workService.updateWork(work);
        if (result > 0){
            return LayuiUtil.setResultMap(0, "操作成功", "", 0);
        }else{
            return LayuiUtil.setResultMap(1, "请求失败", "", 0);
        }

    }

    /**
     * 删除作品
     * @param work
     * @return
     */
    @ResponseBody
    @RequestMapping("/workDelete")
    public Map<String, Object> workDelete(Work work){
        //获取当前修改更新时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = (String) df.format(new Date());

        work.setUpdateTime(time);
        int result = workService.deleteWork(work);
        if (result > 0){
            return LayuiUtil.setResultMap(0, "已删除此作品", "", 0);
        }else{
            return LayuiUtil.setResultMap(1, "请求失败", "", 0);
        }

    }

    /**
     * 获取登录用户的所有作品
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMyWork")
    public Map<String, Object> getMyWork( @RequestParam(value = "page")int page ,
                                          @RequestParam(value = "limit")int limit,
                                          HttpSession session){
        int userId = (int) session.getAttribute("loginUser");
        List<Work> myWork = workService.getUserWork(userId ,page ,limit,null);

        return LayuiUtil.setResultMap(0, "数据请求成功", myWork, myWork.size());
    }

    /**
     * 请求一个单独作品数据 and 设置用户的访问记录
     * @param workId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOneWork")
    public Map<String, Object> workListAll(@RequestParam(value = "workId") int workId,
                                           HttpSession session) {
        //获取当前修改更新时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = (String) df.format(new Date());


        //每次访问数据库，所以阅读量+1
        workService.addOne(workId);
        Work work= workService.getOneWork(workId);
        //设置阅读纪录
        int userId = (int) session.getAttribute("loginUser");

        if (work.getAuthorId() != userId){//如果访问的是自己的作品，不会被添加到阅读记录

            Read read = new Read();
            read.setUserId(userId);
            read.setWorkId(workId);
            read.setReadTime(time);
            int readResult = readService.checkRead(read);//核实是否已存在阅读记录

            if(readResult > 0){ //说明已经存在纪录，再次阅读纪录需要修改阅读时间
                readService.updateRead(read);
            }else{//说明没有阅读纪录，需要添加记录
                readService.insertRead(read);
            }
        }





        return LayuiUtil.setResultMap(0, "数据请求成功", work, 1);
    }



    /**
     *查询所有待审核的文学作品（admin权限）
     */
    @ResponseBody
    @RequestMapping("/workListNoPermit")
    public Map<String, Object> workListNoPermit() {
        List<Work> list = workService.getAllPermit();
        return LayuiUtil.setResultMap(0, "操作成功", list, list.size());
    }

}
