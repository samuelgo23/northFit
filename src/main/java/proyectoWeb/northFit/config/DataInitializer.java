package proyectoWeb.northFit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import proyectoWeb.northFit.models.*;
import proyectoWeb.northFit.services.*;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private MembershipService membershipService;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuarios de prueba
        createUsers();
        // Crear membresías
        createMemberships();
        
        System.out.println("========================================");
        System.out.println("Datos iniciales creados correctamente");
        System.out.println("========================================");
        System.out.println("ADMIN:   admin@northfit.com / admin123");
        System.out.println("MANAGER: manager@northfit.com / manager123");
        System.out.println("TRAINER: trainer@northfit.com / trainer123");
        System.out.println("CLIENT:  client@northfit.com / client123");
        System.out.println("========================================");
    }

    private void createUsers() {
        // Admin
        if (!userService.existeCorreo("admin@northfit.com")) {
            User admin = new User();
            admin.setNombre("Administrador Principal");
            admin.setCorreo("admin@northfit.com");
            admin.setContrasena("admin123");
            admin.setRol(User.Role.ADMIN);
            admin.setTelefono("3001234567");
            admin.setActivo(true);
            userService.crearUsuario(admin);
        }

        // Manager
        if (!userService.existeCorreo("manager@northfit.com")) {
            User manager = new User();
            manager.setNombre("Gerente General");
            manager.setCorreo("manager@northfit.com");
            manager.setContrasena("manager123");
            manager.setRol(User.Role.MANAGER);
            manager.setTelefono("3009876543");
            manager.setActivo(true);
            userService.crearUsuario(manager);
        }

        // Trainer
        if (!userService.existeCorreo("trainer@northfit.com")) {
            User trainer = new User();
            trainer.setNombre("Carlos Entrenador");
            trainer.setCorreo("trainer@northfit.com");
            trainer.setContrasena("trainer123");
            trainer.setRol(User.Role.TRAINER);
            trainer.setTelefono("3005551234");
            trainer.setActivo(true);
            userService.crearUsuario(trainer);
        }

        // Client
        if (!userService.existeCorreo("client@northfit.com")) {
            User client = new User();
            client.setNombre("Juan Cliente");
            client.setCorreo("client@northfit.com");
            client.setContrasena("client123");
            client.setRol(User.Role.CLIENT);
            client.setTelefono("3007778888");
            client.setActivo(true);
            userService.crearUsuario(client);
        }
    }

    private void createMemberships() {
        // Membresía Básica
        Membership basica = new Membership();
        basica.setNombre("Básica");
        basica.setDescripcion("Acceso a gimnasio y clases grupales básicas");
        basica.setPrecio(50000.0);
        basica.setDuracionDias(30);
        basica.setTipo(Membership.TipoMembresia.BASICA);
        basica.setClasesMensuales(8);
        basica.setClasesPersonalesIncluidas(0);
        basica.setAccesoTodasSedes(false);
        basica.setBeneficios("Acceso a gimnasio, 8 clases grupales al mes");
        basica.setActiva(true);
        membershipService.crearMembresia(basica);

        // Membresía Estándar
        Membership estandar = new Membership();
        estandar.setNombre("Estándar");
        estandar.setDescripcion("Acceso completo a gimnasio y clases grupales");
        estandar.setPrecio(80000.0);
        estandar.setDuracionDias(30);
        estandar.setTipo(Membership.TipoMembresia.ESTANDAR);
        estandar.setClasesMensuales(null); // Ilimitadas
        estandar.setClasesPersonalesIncluidas(1);
        estandar.setAccesoTodasSedes(false);
        estandar.setBeneficios("Acceso a gimnasio, clases grupales ilimitadas, 1 clase personal");
        estandar.setActiva(true);
        membershipService.crearMembresia(estandar);

        // Membresía Premium
        Membership premium = new Membership();
        premium.setNombre("Premium");
        premium.setDescripcion("Acceso total con clases personales incluidas");
        premium.setPrecio(120000.0);
        premium.setDuracionDias(30);
        premium.setTipo(Membership.TipoMembresia.PREMIUM);
        premium.setClasesMensuales(null); // Ilimitadas
        premium.setClasesPersonalesIncluidas(4);
        premium.setAccesoTodasSedes(true);
        premium.setBeneficios("Todo ilimitado, 4 clases personales, acceso a todas las sedes");
        premium.setActiva(true);
        membershipService.crearMembresia(premium);

        // Membresía VIP
        Membership vip = new Membership();
        vip.setNombre("VIP");
        vip.setDescripcion("Plan exclusivo con beneficios premium");
        vip.setPrecio(200000.0);
        vip.setDuracionDias(30);
        vip.setTipo(Membership.TipoMembresia.VIP);
        vip.setClasesMensuales(null); // Ilimitadas
        vip.setClasesPersonalesIncluidas(8);
        vip.setAccesoTodasSedes(true);
        vip.setBeneficios("Todo ilimitado, 8 clases personales, nutricionista, spa incluido");
        vip.setActiva(true);
        membershipService.crearMembresia(vip);
    }
}
