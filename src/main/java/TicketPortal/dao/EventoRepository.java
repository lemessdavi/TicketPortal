package TicketPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Você pode adicionar métodos personalizados de consulta aqui, se necessário
}
