package com.li.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ljy
 * @Date 2021/1/24 16:19
 * @description
 */
@Controller
public class LayOutController {

    @RequestMapping(value = "layout")
    public String layout(ModelMap map) {
        return "layout/layout";
    }

    @RequestMapping(value = "home")
    public String home() {
        return "view/home";
    }

    @RequestMapping(value = "upload")
    public String upload() {
        return "";
    }
}
