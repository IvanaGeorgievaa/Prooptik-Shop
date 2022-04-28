package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.model.Category;
import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.reporitory.ProductRepository;
import mk.ukim.finki.db.project.service.CategoryService;
import mk.ukim.finki.db.project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, CategoryService categoryService, ProductRepository productRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String message,
                                 @RequestParam(required = false) Integer categoryId,
                                 Model model) {
        if (message != null && !message.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("message", message);
        } else if (categoryId != null) {
            model.addAttribute("products", productService.findAllByCategory(categoryId));
        } else {
            model.addAttribute("products", this.productService.findAll());
        }
        model.addAttribute("CategoryList", categoryService.findAll());
        return "products";
    }
    @GetMapping("/displayChart")
    public String pieChart(Model model) {
        model.addAttribute("eyeglasses", (long) this.productRepository.findAllByCategory_Id(1).size());
        model.addAttribute("sunglasses", (long) this.productRepository.findAllByCategory_Id(2).size());
        model.addAttribute("kidsCollection", (long) this.productRepository.findAllByCategory_Id(3).size());
        model.addAttribute("accessories", (long) this.productRepository.findAllByCategory_Id(4).size());
        return "Chart";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        return "add-product";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(Model model, @PathVariable Integer id) {
        try {
            Product product = this.productService.findById(id);
            model.addAttribute("product", product);
            model.addAttribute("categories", this.categoryService.findAll());
            return "add-product";
        } catch (RuntimeException ex) {
            return "redirect:/products?error=" + ex.getMessage();
        }
    }

    @PostMapping
    public String saveProduct(
            @Valid Product product,
            BindingResult bindingResult,
            @RequestParam MultipartFile image,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("categories", categories);
            return "add-product";
        }
        try {
            this.productService.save(product, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }


    @DeleteMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Integer id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

}
