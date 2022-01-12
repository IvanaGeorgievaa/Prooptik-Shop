package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.db.project.reporitory.ProductRepository;
import mk.ukim.finki.db.project.reporitory.ProductsByCategoryViewRepository;
import mk.ukim.finki.db.project.service.ProductService;
import mk.ukim.finki.db.project.views.ProductsByCategoryView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsByCategoryViewRepository productsByCategoryViewRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductsByCategoryViewRepository productsByCategoryViewRepository, ProductRepository productRepository) {
        this.productsByCategoryViewRepository = productsByCategoryViewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductsByCategoryView> findAll() {
        return this.productsByCategoryViewRepository.findAll();
    }

    @Override
    public List<ProductsByCategoryView> findAllByCategory(Integer category) {
        return findAll().stream().filter(i->i.getId_kategorija().equals(category)).collect(Collectors.toList());
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    }
}