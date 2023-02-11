package com.jeff.jeffhome.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//Rest模式
@Controller
@RequestMapping("/admin")
public class BookController {
    @GetMapping("/books")
    @ResponseBody
    public String getById(){
        System.out.println("springboot is running...");
        return "springboot is running...";
    }

    @GetMapping("/testusers")
    public String getTest(){
        return "user";
    }

}
