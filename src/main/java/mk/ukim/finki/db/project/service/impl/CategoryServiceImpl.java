package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.reporitory.CategoryRepository;
import mk.ukim.finki.db.project.service.CategoryService;
import mk.ukim.finki.db.project.views.CategoryView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<CategoryView> findById(Integer id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public List<CategoryView> findAll() {
        return this.categoryRepository.findAll();
    }
}
