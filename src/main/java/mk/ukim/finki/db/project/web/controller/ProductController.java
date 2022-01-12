package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.service.CategoryService;
import mk.ukim.finki.db.project.service.ProductService;
import mk.ukim.finki.db.project.views.CategoryView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/products"})
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error,
                                 @RequestParam(required = false) Integer categoryId,
                                 Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        else if(categoryId!=null){
            model.addAttribute("products", productService.findAllByCategory(categoryId));
        }
        else{
            model.addAttribute("products", this.productService.findAll());
        }
        model.addAttribute("CategoryList", categoryService.findAll());
        return "products";
    }

}
