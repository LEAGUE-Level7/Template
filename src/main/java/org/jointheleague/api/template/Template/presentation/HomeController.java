package org.jointheleague.api.template.Template.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public String home(){
        return "redirect:v3/api-docs";
    }

}