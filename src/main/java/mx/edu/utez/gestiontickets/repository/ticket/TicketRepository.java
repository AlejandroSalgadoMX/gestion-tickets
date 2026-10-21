package mx.edu.utez.gestiontickets.repository.ticket;

import java.util.List;
import mx.edu.utez.gestiontickets.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUsuarioIdOrderByIdAsc(Long usuarioId);
}
