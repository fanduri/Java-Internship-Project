package com.NitsaBedianashvili.Notifications.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/")
    public String helo(){
        return "salami yvelas gelas da belas";
    }

}
