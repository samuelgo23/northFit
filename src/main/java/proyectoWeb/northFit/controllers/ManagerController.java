package proyectoWeb.northFit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyectoWeb.northFit.models.*;
import proyectoWeb.northFit.services.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private BookingService bookingService;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsers", userService.obtenerActivos().size());
        model.addAttribute("totalMemberships", membershipService.obtenerActivas().size());
        model.addAttribute("totalBookings", bookingService.obtenerTodas().size());
        return "manager/dashboard";
    }

    // ===== GESTIÓN DE USUARIOS =====
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.obtenerTodos());
        return "manager/users/list";
    }

    @GetMapping("/users/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "manager/users/form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user) {
        if (user.getId() == null) {
            userService.crearUsuario(user);
        } else {
            userService.actualizarUsuario(user.getId(), user);
        }
        return "redirect:/manager/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "manager/users/form";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.eliminarUsuario(id);
        return "redirect:/manager/users";
    }

    // ===== GESTIÓN DE MEMBRESÍAS =====
    @GetMapping("/memberships")
    public String listMemberships(Model model) {
        model.addAttribute("memberships", membershipService.obtenerTodas());
        return "manager/memberships/list";
    }

    @GetMapping("/memberships/new")
    public String showCreateMembershipForm(Model model) {
        model.addAttribute("membership", new Membership());
        return "manager/memberships/form";
    }

    @PostMapping("/memberships/save")
    public String saveMembership(@ModelAttribute Membership membership) {
        if (membership.getId() == null) {
            membershipService.crearMembresia(membership);
        } else {
            membershipService.actualizarMembresia(membership.getId(), membership);
        }
        return "redirect:/manager/memberships";
    }

    @GetMapping("/memberships/edit/{id}")
    public String showEditMembershipForm(@PathVariable Long id, Model model) {
        Membership membership = membershipService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        model.addAttribute("membership", membership);
        return "manager/memberships/form";
    }

    @GetMapping("/memberships/delete/{id}")
    public String deleteMembership(@PathVariable Long id) {
        membershipService.eliminarMembresia(id);
        return "redirect:/manager/memberships";
    }

    // ===== GESTIÓN DE RESERVAS =====
    @GetMapping("/bookings")
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.obtenerTodas());
        return "manager/bookings/list";
    }

    @GetMapping("/bookings/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelarReserva(id);
        return "redirect:/manager/bookings";
    }

    // ===== REPORTES =====
    @GetMapping("/reports")
    public String reports(Model model) {
        return "manager/reports";
    }
}