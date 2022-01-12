package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.views.ProductsByCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsByCategoryViewRepository extends JpaRepository<ProductsByCategoryView, Integer> {
}
