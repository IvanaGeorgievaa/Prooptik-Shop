package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path ={"", "/", "/home"})
public class HomeController {
    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("CategoryList", categoryService.findAll());
        return "home";
    }
}

