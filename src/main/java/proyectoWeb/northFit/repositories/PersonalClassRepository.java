package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.PersonalClass;
import proyectoWeb.northFit.models.PersonalClass.TipoClase;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PersonalClassRepository extends JpaRepository<PersonalClass, Long> {
    List<PersonalClass> findByActivaTrue();
    List<PersonalClass> findByTrainerId(Long trainerId);
    List<PersonalClass> findBySedeId(Long sedeId);
    List<PersonalClass> findByTipoClase(TipoClase tipo);
    List<PersonalClass> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT c FROM PersonalClass c WHERE c.activa = true AND c.capacidadActual < c.capacidadMaxima")
    List<PersonalClass> findClasesConCupos();
}