package com.alurachallengebackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheck {

    @RequestMapping("/health")
    @ResponseBody
    public String healthCheck(){return "ok";}
}
