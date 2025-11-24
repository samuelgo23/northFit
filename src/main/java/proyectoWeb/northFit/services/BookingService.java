package proyectoWeb.northFit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoWeb.northFit.models.Booking;
import proyectoWeb.northFit.models.PersonalClass;
import proyectoWeb.northFit.models.User;
import proyectoWeb.northFit.repositories.BookingRepository;
import proyectoWeb.northFit.repositories.PersonalClassRepository;
import proyectoWeb.northFit.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PersonalClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Booking> obtenerTodas() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> obtenerPorId(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> obtenerPorUsuario(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> obtenerPorClase(Long claseId) {
        return bookingRepository.findByClaseId(claseId);
    }

    public List<Booking> obtenerPorEstado(Booking.EstadoReserva estado) {
        return bookingRepository.findByEstado(estado);
    }

    public Booking crearReserva(Long userId, Long claseId, String notas) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        PersonalClass clase = classRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        // Validar que la clase tenga cupos disponibles
        if (!clase.tieneCuposDisponibles()) {
            throw new RuntimeException("La clase no tiene cupos disponibles");
        }

        // Validar que el usuario no tenga ya una reserva para esta clase
        if (bookingRepository.existsByUserIdAndClaseIdAndEstado(
                userId, claseId, Booking.EstadoReserva.CONFIRMADA)) {
            throw new RuntimeException("Ya tienes una reserva para esta clase");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setClase(clase);
        booking.setNotas(notas);
        booking.setEstado(Booking.EstadoReserva.CONFIRMADA);

        // Incrementar capacidad actual de la clase
        clase.setCapacidadActual(clase.getCapacidadActual() + 1);
        classRepository.save(clase);

        return bookingRepository.save(booking);
    }

    public Booking confirmarReserva(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        booking.setEstado(Booking.EstadoReserva.CONFIRMADA);
        return bookingRepository.save(booking);
    }

    public Booking cancelarReserva(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        
        booking.setEstado(Booking.EstadoReserva.CANCELADA);
        booking.setFechaCancelacion(java.time.LocalDateTime.now());

        // Decrementar capacidad actual de la clase
        PersonalClass clase = booking.getClase();
        if (clase.getCapacidadActual() > 0) {
            clase.setCapacidadActual(clase.getCapacidadActual() - 1);
            classRepository.save(clase);
        }

        return bookingRepository.save(booking);
    }

    public Booking marcarAsistencia(Long id, boolean asistio) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        booking.setAsistio(asistio);
        booking.setEstado(Booking.EstadoReserva.COMPLETADA);
        return bookingRepository.save(booking);
    }

    public void eliminarReserva(Long id) {
        bookingRepository.deleteById(id);
    }
}