package com.example.mafia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/ranking")
    public String ranking(){
        return "ranking";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/login_join")
    public String login_join(){
        return "login_join";
    }
    @RequestMapping("/game")
    public String game(){
        return "game";
    }
    @RequestMapping("/board")
    public String board(){
        return "board";
    }
}
