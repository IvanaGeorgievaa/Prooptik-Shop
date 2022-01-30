package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.views.CategoryView;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryView> findById(Integer id);
    List<CategoryView> findAll();
}
