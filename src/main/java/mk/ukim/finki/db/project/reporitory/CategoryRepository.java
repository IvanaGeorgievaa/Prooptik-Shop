package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.views.CategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryView, Integer> {
}
