package mx.edu.utez.gestiontickets.controller.ticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gestiontickets.model.ticket.PrioridadTicket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateTicketDto {

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;

    @NotNull(message = "El usuario es obligatorio")
    @Positive(message = "El id del usuario debe ser válido")
    private Long usuarioId;
}
