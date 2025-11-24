package proyectoWeb.northFit.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String contrasena;
    private String rol; // admin, trainer, client

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<payment> pagos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<booking> reservas;

    public user() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
