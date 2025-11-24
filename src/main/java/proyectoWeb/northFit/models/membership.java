package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "memberships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    @Column(name = "tipo_membresia")
    @Enumerated(EnumType.STRING)
    private TipoMembresia tipo;

    @Column(name = "clases_mensuales")
    private Integer clasesMensuales; // null = ilimitadas

    @Column(name = "clases_personales_incluidas")
    private Integer clasesPersonalesIncluidas;

    @Column(name = "acceso_todas_sedes")
    private Boolean accesoTodasSedes = false;

    @Column(columnDefinition = "TEXT")
    private String beneficios;

    @Column(name = "activa")
    private Boolean activa = true;

    @OneToMany(mappedBy = "membership", fetch = FetchType.LAZY)
    private List<Client> clients;

    public enum TipoMembresia {
        BASICA,
        ESTANDAR,
        PREMIUM,
        VIP
    }

    @PrePersist
    protected void onCreate() {
        if (activa == null) {
            activa = true;
        }
        if (accesoTodasSedes == null) {
            accesoTodasSedes = false;
        }
    }
}