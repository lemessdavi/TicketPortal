package TicketPortal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.Evento;
import TicketPortal.models.TipoIngresso;

@Repository
public interface TipoIngressoRepository extends JpaRepository<TipoIngresso, Long> {

	Optional<TipoIngresso> findByEvento(Evento evento);
}