package com.sawari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/signup")
    public String signupPage() {
        return "SignupPage"; // This should map to src/main/resources/templates/SignupPage.html
    }
}
