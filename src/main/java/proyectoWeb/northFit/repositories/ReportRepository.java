package proyectoWeb.northFit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoWeb.northFit.models.Report;
import proyectoWeb.northFit.models.Report.TipoReporte;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByTipoReporte(TipoReporte tipo);
    List<Report> findByGeneradoPorId(Long userId);
    List<Report> findTop10ByOrderByFechaGeneracionDesc();
}