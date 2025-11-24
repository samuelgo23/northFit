package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private PersonalClass clase;

    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @Column(name = "fecha_cancelacion")
    private LocalDateTime fechaCancelacion;

    @Column(columnDefinition = "TEXT")
    private String notas;

    @Column(name = "asistio")
    private Boolean asistio;

    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA,
        COMPLETADA
    }

    @PrePersist
    protected void onCreate() {
        if (fechaReserva == null) {
            fechaReserva = LocalDateTime.now();
        }
        if (estado == null) {
            estado = EstadoReserva.PENDIENTE;
        }
    }
}