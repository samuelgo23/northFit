package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @Column(nullable = false)
    private Double monto;

    @Column(name = "metodo_pago")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    @Column(name = "numero_transaccion", unique = true)
    private String numeroTransaccion;

    @Column(columnDefinition = "TEXT")
    private String concepto;

    @Column(columnDefinition = "TEXT")
    private String notas;

    public enum MetodoPago {
        EFECTIVO,
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        TRANSFERENCIA,
        PAYPAL
    }

    public enum EstadoPago {
        PENDIENTE,
        COMPLETADO,
        RECHAZADO,
        REEMBOLSADO
    }

    @PrePersist
    protected void onCreate() {
        if (fechaPago == null) {
            fechaPago = LocalDateTime.now();
        }
        if (estado == null) {
            estado = EstadoPago.PENDIENTE;
        }
        if (numeroTransaccion == null) {
            numeroTransaccion = "TRX" + System.currentTimeMillis();
        }
    }
}