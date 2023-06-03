package TicketPortal.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TicketPortal.models.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // adicionar métodos de consulta aqui, se necessário a
}
