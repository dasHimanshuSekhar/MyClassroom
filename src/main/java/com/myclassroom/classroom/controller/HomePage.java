package com.myclassroom.classroom.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePage {

    @GetMapping("home_page")
    public String test(){
        return "My Classroom Homepage !";
    }
}
