package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Membership;
import proyectoWeb.northFit.models.Membership.TipoMembresia;
import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByActivaTrue();
    List<Membership> findByTipo(TipoMembresia tipo);
    List<Membership> findByPrecioBetween(Double min, Double max);
}