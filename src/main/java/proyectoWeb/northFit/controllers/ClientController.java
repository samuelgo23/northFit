package proyectoWeb.northFit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyectoWeb.northFit.models.User;
import proyectoWeb.northFit.services.*;
import proyectoWeb.northFit.repositories.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private PersonalClassRepository classRepository;

    // Dashboard del cliente
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String correo = authentication.getName();
        User user = userService.obtenerPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        model.addAttribute("user", user);
        model.addAttribute("misReservas", bookingService.obtenerPorUsuario(user.getId()));
        return "client/dashboard";
    }

    // Ver perfil
    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        String correo = authentication.getName();
        User user = userService.obtenerPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "client/profile";
    }

    // Actualizar perfil
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, Authentication authentication) {
        String correo = authentication.getName();
        User currentUser = userService.obtenerPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        userService.actualizarUsuario(currentUser.getId(), user);
        return "redirect:/client/profile?success=true";
    }

    // Ver clases disponibles
    @GetMapping("/classes")
    public String listClasses(Model model) {
        model.addAttribute("classes", classRepository.findClasesConCupos());
        return "client/classes";
    }

    // Reservar una clase
    @PostMapping("/classes/{id}/book")
    public String bookClass(@PathVariable Long id, Authentication authentication, 
                           @RequestParam(required = false) String notas) {
        String correo = authentication.getName();
        User user = userService.obtenerPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        try {
            bookingService.crearReserva(user.getId(), id, notas);
            return "redirect:/client/bookings?success=true";
        } catch (Exception e) {
            return "redirect:/client/classes?error=" + e.getMessage();
        }
    }

    // Ver mis reservas
    @GetMapping("/bookings")
    public String myBookings(Authentication authentication, Model model) {
        String correo = authentication.getName();
        User user = userService.obtenerPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        model.addAttribute("bookings", bookingService.obtenerPorUsuario(user.getId()));
        return "client/bookings";
    }

    // Cancelar reserva
    @PostMapping("/bookings/{id}/cancel")
    public String cancelBooking(@PathVariable Long id, Authentication authentication) {
        String correo = authentication.getName();
        User user = userService.obtenerPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        bookingService.cancelarReserva(id);
        return "redirect:/client/bookings?cancelled=true";
    }

    // Ver membresías disponibles
    @GetMapping("/memberships")
    public String viewMemberships(Model model) {
        model.addAttribute("memberships", membershipService.obtenerActivas());
        return "client/memberships";
    }
}