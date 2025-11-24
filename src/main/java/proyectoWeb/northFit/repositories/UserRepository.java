package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.User;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCorreo(String correo);
    List<User> findByRol(User.Role rol);
    List<User> findByActivoTrue();
    boolean existsByCorreo(String correo);
}