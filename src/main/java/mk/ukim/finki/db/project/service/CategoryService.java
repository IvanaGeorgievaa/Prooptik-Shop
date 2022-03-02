package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Category;
import mk.ukim.finki.db.project.views.CategoryView;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category findById(Integer id);
    List<Category> findAll();
}
