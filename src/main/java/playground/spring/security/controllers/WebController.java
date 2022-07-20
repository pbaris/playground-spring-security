package playground.spring.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Panos Bariamis (pbaris)
 */
@Controller
public class WebController {

    @GetMapping("/index")
    public String root() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String homePage() {
        return "redirect:/index";
    }
}
