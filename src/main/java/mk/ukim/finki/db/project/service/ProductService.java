package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.views.ProductsByCategoryView;

import java.util.List;

public interface ProductService {
    List<ProductsByCategoryView> findAll();
    List<ProductsByCategoryView> findAllByCategory(Integer category);
    Product findById(Integer id);
}
