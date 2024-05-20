
package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    public MainController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value="/pc")
    public String index() {
        return "pc/pc";
    }

    @RequestMapping(value="/login")
    public String indexZero() {
        return "login/login";
    }

    @RequestMapping(value="/pc/smain")
    public String smain() {
        return "pc/smain";
    }

}