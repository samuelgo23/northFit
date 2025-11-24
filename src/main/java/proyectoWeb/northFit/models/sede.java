package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "sedes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sede {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    private String ciudad;

    private String telefono;

    private String email;

    @Column(name = "horario_apertura")
    private String horarioApertura;

    @Column(name = "horario_cierre")
    private String horarioCierre;

    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;

    @Column(columnDefinition = "TEXT")
    private String servicios; // Lista de servicios: "Sauna, Piscina, Estacionamiento"

    @Column(name = "activa")
    private Boolean activa = true;

    private String imagen; // URL de imagen

    @OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
    private List<PersonalClass> clases;

    @OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "sedePreferida", fetch = FetchType.LAZY)
    private List<Client> clients;

    @PrePersist
    protected void onCreate() {
        if (activa == null) {
            activa = true;
        }
    }
}