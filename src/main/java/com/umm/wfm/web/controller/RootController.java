package com.umm.wfm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根
 *
 * @author zhangff
 */
@Controller
public class RootController {

    @RequestMapping(value = "/")
    public ModelAndView root() {
        return new ModelAndView("redirect:/statics/index.html");
    }
    

}