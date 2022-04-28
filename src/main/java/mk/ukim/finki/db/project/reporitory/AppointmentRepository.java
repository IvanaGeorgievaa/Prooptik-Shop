package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
