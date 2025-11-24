package proyectoWeb.northFit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoWeb.northFit.models.Membership;
import proyectoWeb.northFit.repositories.MembershipRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public List<Membership> obtenerTodas() {
        return membershipRepository.findAll();
    }

    public List<Membership> obtenerActivas() {
        return membershipRepository.findByActivaTrue();
    }

    public Optional<Membership> obtenerPorId(Long id) {
        return membershipRepository.findById(id);
    }

    public List<Membership> obtenerPorTipo(Membership.TipoMembresia tipo) {
        return membershipRepository.findByTipo(tipo);
    }

    public List<Membership> obtenerPorRangoPrecio(Double min, Double max) {
        return membershipRepository.findByPrecioBetween(min, max);
    }

    public Membership crearMembresia(Membership membership) {
        return membershipRepository.save(membership);
    }

    public Membership actualizarMembresia(Long id, Membership membresiaActualizada) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        
        membership.setNombre(membresiaActualizada.getNombre());
        membership.setDescripcion(membresiaActualizada.getDescripcion());
        membership.setPrecio(membresiaActualizada.getPrecio());
        membership.setDuracionDias(membresiaActualizada.getDuracionDias());
        membership.setTipo(membresiaActualizada.getTipo());
        membership.setClasesMensuales(membresiaActualizada.getClasesMensuales());
        membership.setClasesPersonalesIncluidas(membresiaActualizada.getClasesPersonalesIncluidas());
        membership.setAccesoTodasSedes(membresiaActualizada.getAccesoTodasSedes());
        membership.setBeneficios(membresiaActualizada.getBeneficios());
        
        return membershipRepository.save(membership);
    }

    public void desactivarMembresia(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        membership.setActiva(false);
        membershipRepository.save(membership);
    }

    public void activarMembresia(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        membership.setActiva(true);
        membershipRepository.save(membership);
    }

    public void eliminarMembresia(Long id) {
        membershipRepository.deleteById(id);
    }
}