package mx.edu.utez.gestiontickets.repository.usuario;

import mx.edu.utez.gestiontickets.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
