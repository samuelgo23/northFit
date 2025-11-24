package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Payment;
import proyectoWeb.northFit.models.Payment.EstadoPago;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
    List<Payment> findByEstado(EstadoPago estado);
    List<Payment> findByFechaPagoBetween(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT SUM(p.monto) FROM Payment p WHERE p.estado = 'COMPLETADO' AND p.fechaPago BETWEEN ?1 AND ?2")
    Double calcularIngresosPorPeriodo(LocalDateTime inicio, LocalDateTime fin);
}