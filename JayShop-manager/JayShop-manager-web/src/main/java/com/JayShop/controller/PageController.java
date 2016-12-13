package com.JayShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/10/16.
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
