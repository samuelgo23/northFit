package proyectoWeb.northFit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;
    private String metodo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters y setters...
}
