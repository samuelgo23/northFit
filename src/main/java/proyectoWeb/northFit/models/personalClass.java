package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;

    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;

    @Column(name = "capacidad_actual")
    private Integer capacidadActual = 0;

    @Column(name = "tipo_clase")
    @Enumerated(EnumType.STRING)
    private TipoClase tipoClase;

    @Column(name = "nivel_dificultad")
    @Enumerated(EnumType.STRING)
    private NivelDificultad nivelDificultad;

    @Column(name = "precio_adicional")
    private Double precioAdicional; // null si está incluido en membresía

    @Column(name = "activa")
    private Boolean activa = true;

    @OneToMany(mappedBy = "clase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> reservas;

    public enum TipoClase {
        YOGA,
        SPINNING,
        CROSSFIT,
        ZUMBA,
        PILATES,
        FUNCIONAL,
        BOXEO,
        NATACION,
        OTRO
    }

    public enum NivelDificultad {
        PRINCIPIANTE,
        INTERMEDIO,
        AVANZADO
    }

    @PrePersist
    protected void onCreate() {
        if (activa == null) {
            activa = true;
        }
        if (capacidadActual == null) {
            capacidadActual = 0;
        }
    }

    public boolean tieneCuposDisponibles() {
        return capacidadActual < capacidadMaxima;
    }
}