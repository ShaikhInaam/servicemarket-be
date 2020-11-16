package com.market.servicemarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {


    @GetMapping("/")
    String greetings() {
        return "Greetings! Service Market pakistan";
    }


}
