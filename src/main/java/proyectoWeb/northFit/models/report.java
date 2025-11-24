package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "generado_por_user_id")
    private User generadoPor;

    @Column(name = "tipo_reporte")
    @Enumerated(EnumType.STRING)
    private TipoReporte tipoReporte;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;

    @Column(name = "fecha_inicio_periodo")
    private LocalDateTime fechaInicioPeriodo;

    @Column(name = "fecha_fin_periodo")
    private LocalDateTime fechaFinPeriodo;

    @Column(columnDefinition = "TEXT")
    private String datos; // JSON con los datos del reporte

    @Column(name = "archivo_url")
    private String archivoUrl; // URL del archivo PDF generado

    public enum TipoReporte {
        INGRESOS,
        ASISTENCIAS,
        NUEVOS_CLIENTES,
        MEMBRESIAS_ACTIVAS,
        CLASES_POPULARES,
        CANCELACIONES,
        GENERAL
    }

    @PrePersist
    protected void onCreate() {
        if (fechaGeneracion == null) {
            fechaGeneracion = LocalDateTime.now();
        }
    }
}