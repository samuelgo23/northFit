package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "trainers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @Column(nullable = false)
    private String especialidad;

    @Column(columnDefinition = "TEXT")
    private String certificaciones;

    @Column(columnDefinition = "TEXT")
    private String experiencia;

    @Column(name = "años_experiencia")
    private Integer anosExperiencia;

    @Column(columnDefinition = "TEXT")
    private String horarioDisponible;

    @Column(name = "tarifa_hora_personal")
    private Double tarifaHoraPersonal;

    @Column(name = "calificacion_promedio")
    private Double calificacionPromedio;

    @Column(name = "activo")
    private Boolean activo = true;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<PersonalClass> clases;

    @PrePersist
    protected void onCreate() {
        if (activo == null) {
            activo = true;
        }
        if (calificacionPromedio == null) {
            calificacionPromedio = 5.0;
        }
    }
}