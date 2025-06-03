
package com.profesorinterino.auth.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<a href='/oauth2/authorization/google'>Login con Google</a>";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "<a href='/oauth2/authorization/google'>Login con Google</a>";
    }

    @GetMapping("/success")
    @ResponseBody
    public String success() {
        return "¡Login exitoso con Google!";
    }
}
