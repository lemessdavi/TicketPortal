package TicketPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.TipoIngresso;

@Repository
public interface TipoIngressoRepository extends JpaRepository<TipoIngresso, Long> {
    // Você pode adicionar métodos personalizados de consulta aqui, se necessário
}