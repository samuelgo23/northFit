package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Client;
import proyectoWeb.northFit.models.Client.EstadoMembresia;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
    List<Client> findByEstadoMembresia(EstadoMembresia estado);
    List<Client> findByMembershipId(Long membershipId);
    List<Client> findBySedePreferidaId(Long sedeId);
}
