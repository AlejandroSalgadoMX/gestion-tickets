package mx.edu.utez.gestiontickets.controller.ticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gestiontickets.model.ticket.PrioridadTicket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdatePrioridadTicketDto {

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;
}
