package TicketPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}


