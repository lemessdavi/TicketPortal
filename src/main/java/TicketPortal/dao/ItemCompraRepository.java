package TicketPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.ItemCompra;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
    // Você pode adicionar métodos personalizados de consulta aqui, se necessário
}
