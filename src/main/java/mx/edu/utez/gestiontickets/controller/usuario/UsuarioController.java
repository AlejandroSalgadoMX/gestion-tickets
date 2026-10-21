package mx.edu.utez.gestiontickets.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.gestiontickets.controller.usuario.dto.RequestCreateUsuarioDto;
import mx.edu.utez.gestiontickets.controller.usuario.dto.ResponseUsuarioDto;
import mx.edu.utez.gestiontickets.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ResponseUsuarioDto> crearUsuario(
            @RequestBody @Valid RequestCreateUsuarioDto payload
    ) {
        return ResponseEntity
                .status(201)
                .body(usuarioService.crearUsuario(payload));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ResponseUsuarioDto> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.desactivarUsuario(id));
    }
}
