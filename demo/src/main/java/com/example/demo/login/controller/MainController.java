
package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/pc")
public class MainController {

    public MainController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value={"*", "/"})
    public String index() {
        return "pc/pc";
    }

    @RequestMapping(value="/login")
    public String indexZero() {
        return "login/login";
    }

    @RequestMapping(value="/smain")
    public String smain() {
        return "pc/smain";
    }
    
    @RequestMapping(value="/member")
    public String memberMain() {
    	return "pc/memMain";
    }

    @RequestMapping(value="/non-member")
    public String nonmemberMain() {
    	return "pc/nmemMain";
    }

}