package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.Appointment;
import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.exceptions.UserNotFoundException;
import mk.ukim.finki.db.project.reporitory.AppointmentRepository;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Appointment> listAllAppointments(String username) {
        Client client = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return this.appointmentRepository.findAll()
                .stream()
                .filter(i -> i.getClient().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }
}
