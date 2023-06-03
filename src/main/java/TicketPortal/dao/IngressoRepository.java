package TicketPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.Ingresso;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    // Você pode adicionar métodos personalizados de consulta aqui, se necessário
}
