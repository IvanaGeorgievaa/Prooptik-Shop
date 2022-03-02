package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAllByCategory(Integer category);
    Product findById(Integer id);
    void deleteById(Integer id);
    Product edit(Integer id,Product product,MultipartFile image) throws IOException;
    Product save(Product product, MultipartFile image) throws IOException;
}
