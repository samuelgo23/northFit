package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Booking;
import proyectoWeb.northFit.models.Booking.EstadoReserva;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByClaseId(Long claseId);
    List<Booking> findByEstado(EstadoReserva estado);
    List<Booking> findByUserIdAndEstado(Long userId, EstadoReserva estado);
    boolean existsByUserIdAndClaseIdAndEstado(Long userId, Long claseId, EstadoReserva estado);
}