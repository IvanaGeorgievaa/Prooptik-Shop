package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Integer id);
    List<Category> findAll();
}
