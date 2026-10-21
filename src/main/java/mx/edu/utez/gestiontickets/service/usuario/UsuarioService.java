package mx.edu.utez.gestiontickets.service.usuario;

import mx.edu.utez.gestiontickets.controller.usuario.dto.RequestCreateUsuarioDto;
import mx.edu.utez.gestiontickets.controller.usuario.dto.ResponseUsuarioDto;
import mx.edu.utez.gestiontickets.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.gestiontickets.model.usuario.Usuario;
import mx.edu.utez.gestiontickets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseUsuarioDto crearUsuario(RequestCreateUsuarioDto payload) {
        Usuario usuario = new Usuario();
        usuario.setNombre(payload.getNombre().trim());
        usuario.setActivo(payload.getActivo());
        return convertirUsuario(usuarioRepository.save(usuario));
    }

    @Transactional
    public ResponseUsuarioDto desactivarUsuario(Long id) {
        Usuario usuario = obtenerUsuario(id);
        usuario.setActivo(false);
        return convertirUsuario(usuarioRepository.save(usuario));
    }

    private Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("El usuario no existe."));
    }

    private ResponseUsuarioDto convertirUsuario(Usuario usuario) {
        return new ResponseUsuarioDto(usuario.getId(), usuario.getNombre(), usuario.isActivo());
    }
}
