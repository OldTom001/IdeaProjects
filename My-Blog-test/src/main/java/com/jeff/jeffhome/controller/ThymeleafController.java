package com.jeff.jeffhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//不能使用@RestController(是@Controller和@responseBody的结合体)
public class ThymeleafController {
    @GetMapping("/thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.put("thymeleafText", "shiyanlou");
        map.put("number1", 2019);
        map.put("number2", 3);
        return "thymeleaf";
    }
}
