package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value ={"/", "/home"})
public class HomeController {

    @GetMapping("home")
    public String getHomePage(){
        return "home";
    }
}

