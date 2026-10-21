package mx.edu.utez.gestiontickets.controller.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gestiontickets.model.ticket.EstadoTicket;
import mx.edu.utez.gestiontickets.model.ticket.PrioridadTicket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTicketDto {

    private Long id;
    private String descripcion;
    private EstadoTicket estado;
    private PrioridadTicket prioridad;
    private Long usuarioId;
}
