package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.views.FinishedOrdersView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedOrdersViewRepository extends JpaRepository<FinishedOrdersView, Integer> {
}
