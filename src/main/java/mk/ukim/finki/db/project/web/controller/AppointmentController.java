package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.model.Appointment;
import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.exceptions.UserNotFoundException;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final UserRepository userRepository;

    public AppointmentController(AppointmentService appointmentService, UserRepository userRepository) {
        this.appointmentService = appointmentService;
        this.userRepository = userRepository;
    }
    @GetMapping
    public String getAppointmentsPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username=request.getRemoteUser();
        List<Appointment>appointments =this.appointmentService.listAllAppointments(username);
        model.addAttribute("appointments", appointments);
        return "myAppointments";
    }

    @GetMapping("/add-form")
    public String addAppointmentPage() {
        return "add-appointment";
    }

    @PostMapping("/success")
    public String create(@RequestParam String username,
                         @RequestParam String date,
                         Model model){
        Client client=this.userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        Appointment appointment=new Appointment(client, LocalDateTime.parse(date));
        model.addAttribute("client", client);
        this.appointmentService.save(appointment);
        return "SuccessAppointment";
    }
}
