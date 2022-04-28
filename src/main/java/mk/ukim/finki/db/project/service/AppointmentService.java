package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Appointment;
import mk.ukim.finki.db.project.model.Product;

import java.util.List;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    List<Appointment> listAllAppointments(String username);
}
