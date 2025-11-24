package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Sede;
import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {
    List<Sede> findByActivaTrue();
    List<Sede> findByCiudad(String ciudad);
}