package com.myclassroom.classroom.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomePage {

    @GetMapping("test")
    public String test(){
        return " It's working !";
    }
}
