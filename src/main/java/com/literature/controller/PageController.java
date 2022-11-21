package com.literature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转控制
 */
@Controller
public class PageController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "views/index";
    }

    @GetMapping({"/login"})
    public String login() {
        return "views/pages/login";
    }

    @GetMapping({"/hot"})
    public String hot() {
        return "views/pages/hot";
    }

    @GetMapping({"/newest"})
    public String newest() {
        return "views/pages/newest";
    }

    @GetMapping({"/write"})
    public String write() {
        return "views/pages/write";
    }

    @GetMapping({"/myWork"})
    public String myWork() {
        return "views/pages/myWork";
    }

    @GetMapping({"/myRead"})
    public String myRead() {
        return "views/pages/myRead";
    }

    @GetMapping({"/myCollect"})
    public String myCollect() {
        return "views/pages/myCollect";
    }

    @GetMapping({"/reading"})
    public String reading() {
        return "views/pages/reading";
    }

    @GetMapping({"/search"})
    public String search() {
        return "views/pages/search";
    }

    @GetMapping({"/manageUser"})
    public String manageUser() {
        return "views/pages/admin/manageUser";
    }

    @GetMapping({"/manageWork"})
    public String manageWork() {
        return "views/pages/admin/manageWork";
    }

    @GetMapping({"/checkWork"})
    public String checkWork() {
        return "views/pages/admin/checkWork";
    }

}
