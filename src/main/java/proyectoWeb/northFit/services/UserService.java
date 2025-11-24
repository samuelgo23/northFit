package proyectoWeb.northFit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoWeb.northFit.models.User;
import proyectoWeb.northFit.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> obtenerTodos() {
        return userRepository.findAll();
    }

    public Optional<User> obtenerPorId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> obtenerPorCorreo(String correo) {
        return userRepository.findByCorreo(correo);
    }

    public List<User> obtenerPorRol(User.Role rol) {
        return userRepository.findByRol(rol);
    }

    public List<User> obtenerActivos() {
        return userRepository.findByActivoTrue();
    }

    public User crearUsuario(User user) {
        if (userRepository.existsByCorreo(user.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        return userRepository.save(user);
    }

    public User actualizarUsuario(Long id, User userActualizado) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        user.setNombre(userActualizado.getNombre());
        user.setTelefono(userActualizado.getTelefono());
        
        if (userActualizado.getContrasena() != null && !userActualizado.getContrasena().isEmpty()) {
            user.setContrasena(passwordEncoder.encode(userActualizado.getContrasena()));
        }
        
        return userRepository.save(user);
    }

    public void eliminarUsuario(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setActivo(false);
        userRepository.save(user);
    }

    public void activarUsuario(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setActivo(true);
        userRepository.save(user);
    }

    public boolean existeCorreo(String correo) {
        return userRepository.existsByCorreo(correo);
    }
}