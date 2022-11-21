package com.literature.controller;


import com.literature.pojo.User;
import com.literature.service.UserService;
import com.literature.util.LayuiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class UserController {

    //注入业务层，controller调用业务层
    @Autowired
    UserService userService;


    /**
     * 查询所有的申请创作的用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/userList")
    public Map<String, Object> listUser() {
        List<User> list = userService.listUser();
        return LayuiUtil.setResultMap(0, "请求成功", list, list.size());
    }

    /**
     * 用户注册
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/userRegister")
    public String register(User user,
                           Model model,
                           @RequestParam(value = "TwoPassword") String TwoPassword) throws Exception{

        if (user.getEmail().equals("") || user.getPassword().equals("") || user.getUserName().equals("")) {
            model.addAttribute("msg", "请先填写必须信息才可注册！");
            return "views/pages/login";
        }else if(!user.getPassword().equals(TwoPassword)){
            model.addAttribute("msg", "输入的密码与确认密码不同，请重新注册！");
            return "views/pages/login";
        }

        //获取当前的注册时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = (String) df.format(new Date());//);// new Date()为获取当前系统时间

        //下面设置默认值
        user.setCreateTime(time);

        int result = userService.insertUser(user);
        if (result > 0) {
            model.addAttribute("msg", "注册成功，请重新登录！");
        } else {
            model.addAttribute("msg", "注册失败！");
        }
        return "views/pages/login";

    }

    /**
     * 用户登录
     *
     * @param user
     * @param model
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/userToLogin")
    public String toLogin(User user,
                          Model model,
                          HttpSession session,
                          RedirectAttributesModelMap modelMap) throws Exception {

        //对比Model 与 RedirectAttributesModelMap ，后者重定向后可以带参数！
        if (user.getEmail() == null || user.getPassword() == null) {
            model.addAttribute("msg", "请先输入账号密码才可登录！");
            return "views/pages/login";
        }
        User loginUser = userService.checkUserLogin(user);

        if (loginUser != null) {

            session.setAttribute("loginUser", loginUser.getId());
            session.setAttribute("loginRole", loginUser.getRole());
            session.setAttribute("loginName",loginUser.getUserName());
            modelMap.addFlashAttribute("msg", "登录成功，欢迎 " + loginUser.getUserName() + " 用户！");
            modelMap.addFlashAttribute("userName",  loginUser.getUserName());

            //登录成功，进入到index页面！thyme leaf
            return "redirect:/index";

        } else {
            model.addAttribute("msg", "用户名或者密码错误,请重新登录！");
            return "views/pages/login";
        }


    }

    /**
     * 退出登录
     */
    @RequestMapping("/userLogout")
    public String logout(HttpSession session, RedirectAttributesModelMap modelMap) {
        session.invalidate();
        modelMap.addFlashAttribute("msg", "退出成功，可重新登录！");
        return "redirect:/login";//redirect重定向干净利落，不能用model参数
    }

    /**
     * 获得用户个人信息
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserDetail")
    public Map<String, Object> getUserDetail(HttpSession session) {

        int userId = (int) session.getAttribute("loginUser");
        User user = userService.getUserById(userId);
        if (user == null) {
            return LayuiUtil.setResultMap(1, "请求失败", "", 0);
        } else {
            return LayuiUtil.setResultMap(0, "请求成功", user, 0);
        }


    }

    /**
     * 获取身份
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRole")
    public Map<String, Object> getRole(HttpSession session) {

        int userRole = (int) session.getAttribute("loginRole");
        String userName = (String) session.getAttribute("loginName");
        Map<String, Object> map=new HashMap();
        map.put("userRole",userRole);
        map.put("userName",userName);

        return LayuiUtil.setResultMap(0, "请求成功", map, 1);


    }

    /**
     * 个人权限信息的修改
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String, Object> updateUser(User user, HttpSession session) {

        int userId = (int) session.getAttribute("loginUser");

        if ("".equals(user.getMemo())||user.getMemo() == null){//用户改了role
            user.setId(userId);
            session.setAttribute("loginRole",user.getRole());
        }
        //外面是admin改role
        int result = userService.updateUser(user);
        if (result > 0) {
            return LayuiUtil.setResultMap(0, "操作成功", user, 0);
        } else {
            return LayuiUtil.setResultMap(1, "请求失败", "", 0);
        }


    }

}
