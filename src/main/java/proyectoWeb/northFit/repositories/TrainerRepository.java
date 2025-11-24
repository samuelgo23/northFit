package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Trainer;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByUserId(Long userId);
    List<Trainer> findByActivoTrue();
    List<Trainer> findBySedeId(Long sedeId);
    List<Trainer> findByEspecialidad(String especialidad);
}
