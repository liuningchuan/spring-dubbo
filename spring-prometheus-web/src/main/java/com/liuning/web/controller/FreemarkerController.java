package com.liuning.web.controller;

import com.liuning.web.aspect.BusiLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class FreemarkerController {

    @RequestMapping("/index")
    @BusiLog(name = "Freemarker测试接口", ignore = true)
    public String index(Model model){
        model.addAttribute("name","hello pillar");
        return "index.ftl";
    }
}
