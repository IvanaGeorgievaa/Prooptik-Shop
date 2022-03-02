package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.Category;
import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.db.project.reporitory.ProductRepository;
import mk.ukim.finki.db.project.service.CategoryService;
import mk.ukim.finki.db.project.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Integer category) {
        return findAll().stream().filter(i -> i.getCategory().getId().equals(category)).collect(Collectors.toList());
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void deleteById(Integer id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Product edit(Integer id, Product product, MultipartFile image) throws IOException {
        Product editProduct = this.findById(id);
        Category category = this.categoryService.findById(product.getCategory().getId());
        editProduct.setCategory(category);
        editProduct.setName(product.getName());
        editProduct.setPrice(product.getPrice());
        editProduct.setDescription(product.getDescription());
        editProduct.setQuantity(product.getQuantity());

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            editProduct.setPicture(base64Image);
        }
        return this.productRepository.save(editProduct);

    }

    @Override
    public Product save(Product product, MultipartFile image) throws IOException {
        Category category = this.categoryService.findById(product.getCategory().getId());
        product.setCategory(category);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            product.setPicture(base64Image);
        }
        return this.productRepository.save(product);
    }
}