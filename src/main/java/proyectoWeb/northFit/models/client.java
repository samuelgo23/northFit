package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sedePreferida;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @Column(name = "fecha_vencimiento_membresia")
    private LocalDate fechaVencimientoMembresia;

    @Column(name = "estado_membresia")
    @Enumerated(EnumType.STRING)
    private EstadoMembresia estadoMembresia;

    private String objetivos;
    
    private String nivelExperiencia; // PRINCIPIANTE, INTERMEDIO, AVANZADO

    @Column(name = "peso_actual")
    private Double pesoActual;

    @Column(name = "altura")
    private Double altura;

    @PrePersist
    protected void onCreate() {
        if (fechaInscripcion == null) {
            fechaInscripcion = LocalDate.now();
        }
        if (estadoMembresia == null) {
            estadoMembresia = EstadoMembresia.INACTIVA;
        }
    }

    public enum EstadoMembresia {
        ACTIVA,
        VENCIDA,
        SUSPENDIDA,
        INACTIVA
    }
}