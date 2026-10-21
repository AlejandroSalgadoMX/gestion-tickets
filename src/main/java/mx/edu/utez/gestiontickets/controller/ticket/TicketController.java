package mx.edu.utez.gestiontickets.controller.ticket;

import java.util.List;
import jakarta.validation.Valid;
import mx.edu.utez.gestiontickets.controller.ticket.dto.RequestCreateTicketDto;
import mx.edu.utez.gestiontickets.controller.ticket.dto.RequestUpdatePrioridadTicketDto;
import mx.edu.utez.gestiontickets.controller.ticket.dto.ResponseTicketDto;
import mx.edu.utez.gestiontickets.service.ticket.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<ResponseTicketDto> crearTicket(
            @RequestBody @Valid RequestCreateTicketDto payload
    ) {
        return ResponseEntity
                .status(201)
                .body(ticketService.crearTicket(payload));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ResponseTicketDto> actualizarEstadoTicket(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<ResponseTicketDto> reasignarPrioridad(
            @PathVariable Long id,
            @RequestBody @Valid RequestUpdatePrioridadTicketDto payload
    ) {
        return ResponseEntity
                .status(200)
                .body(ticketService.reasignarPrioridad(id, payload));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ResponseTicketDto>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity
                .status(200)
                .body(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}
