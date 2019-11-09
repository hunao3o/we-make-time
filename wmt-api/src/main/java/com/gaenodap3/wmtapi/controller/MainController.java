package com.gaenodap3.wmtapi.controller;

import java.lang.annotation.Annotation;
//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    //@Autowired
    //private Logger log;

    @GetMapping("/{name}.html")
    public String main(@PathVariable String name, Model model) {
        //log.info(">> forwarding... page: " + name);
        model.addAttribute("pageName", name);
        return "index";
    }
}