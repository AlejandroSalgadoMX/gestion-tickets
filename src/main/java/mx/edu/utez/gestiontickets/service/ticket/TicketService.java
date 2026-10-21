package mx.edu.utez.gestiontickets.service.ticket;

import java.util.List;
import mx.edu.utez.gestiontickets.controller.ticket.dto.RequestCreateTicketDto;
import mx.edu.utez.gestiontickets.controller.ticket.dto.RequestUpdatePrioridadTicketDto;
import mx.edu.utez.gestiontickets.controller.ticket.dto.ResponseTicketDto;
import mx.edu.utez.gestiontickets.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.gestiontickets.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.gestiontickets.model.ticket.EstadoTicket;
import mx.edu.utez.gestiontickets.model.ticket.Ticket;
import mx.edu.utez.gestiontickets.model.usuario.Usuario;
import mx.edu.utez.gestiontickets.repository.ticket.TicketRepository;
import mx.edu.utez.gestiontickets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseTicketDto crearTicket(RequestCreateTicketDto payload) {
        Usuario usuario = obtenerUsuario(payload.getUsuarioId());

        if (!usuario.isActivo()) {
            throw new CustomBadRequestException("El usuario no está activo.");
        }
        if (payload.getDescripcion() == null || payload.getDescripcion().isBlank()) {
            throw new CustomBadRequestException("La descripción no puede estar vacía.");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(payload.getDescripcion().trim());
        ticket.setPrioridad(payload.getPrioridad());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuario(usuario);

        return convertirTicket(ticketRepository.save(ticket));
    }

    @Transactional
    public ResponseTicketDto actualizarEstadoTicket(Long id) {
        Ticket ticket = obtenerTicket(id);

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new CustomBadRequestException("El ticket está cerrado y ya no se puede modificar.");
        }

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else {
            ticket.setEstado(EstadoTicket.CERRADO);
        }

        return convertirTicket(ticketRepository.save(ticket));
    }

    @Transactional
    public ResponseTicketDto reasignarPrioridad(Long id, RequestUpdatePrioridadTicketDto payload) {
        Ticket ticket = obtenerTicket(id);

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new CustomBadRequestException("No se puede cambiar la prioridad de un ticket cerrado.");
        }

        ticket.setPrioridad(payload.getPrioridad());
        return convertirTicket(ticketRepository.save(ticket));
    }

    @Transactional(readOnly = true)
    public List<ResponseTicketDto> obtenerTicketsUsuario(Long usuarioId) {
        obtenerUsuario(usuarioId);
        return ticketRepository.findByUsuarioIdOrderByIdAsc(usuarioId)
                .stream()
                .map(this::convertirTicket)
                .toList();
    }

    private Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("El usuario no existe."));
    }

    private Ticket obtenerTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("El ticket no existe."));
    }

    private ResponseTicketDto convertirTicket(Ticket ticket) {
        return new ResponseTicketDto(
                ticket.getId(),
                ticket.getDescripcion(),
                ticket.getEstado(),
                ticket.getPrioridad(),
                ticket.getUsuario().getId()
        );
    }
}
