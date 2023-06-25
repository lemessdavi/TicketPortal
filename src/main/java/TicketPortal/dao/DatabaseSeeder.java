package TicketPortal.dao;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import TicketPortal.models.Usuario;
import TicketPortal.dao.UsuarioRepository;
import TicketPortal.models.Compra;
import TicketPortal.dao.CompraRepository;
import TicketPortal.models.ItemCompra;
import TicketPortal.dao.ItemCompraRepository;
import TicketPortal.models.Evento;
import TicketPortal.dao.EventoRepository;
import TicketPortal.models.Ingresso;
import TicketPortal.dao.IngressoRepository;
import TicketPortal.models.TipoIngresso;
import TicketPortal.dao.TipoIngressoRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @Override
    public void run(String... args) throws Exception {
        seedUsuarios();
        seedCompras();
        seedItemCompras();
        seedEventos();
        seedTiposIngresso();
        seedIngressos();
    }

    private void seedUsuarios() {
        for (long i = 1; i <= 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Usuario " + i);
            // Set other properties
            usuarioRepository.save(usuario);
        }
    }

    private void seedCompras() {
        for (long i = 1; i <= 20; i++) {
            Compra compra = new Compra();
            Usuario usuario = usuarioRepository.findById(i).orElseThrow();
            compra.setUsuario(usuario);
            // Set other properties
            compraRepository.save(compra);
        }
    }

    private void seedItemCompras() {
        for (long i = 1; i <= 20; i++) {
            ItemCompra itemCompra = new ItemCompra();
            Compra compra = compraRepository.findById(i).orElseThrow();
            System.out.println(compra.getIdCompra());
            itemCompra.setCompra(compra);
            // Set other properties
            itemCompraRepository.save(itemCompra);
        }
    }

    private void seedEventos() {
        for (long i = 1; i <= 20; i++) {
            Evento evento = new Evento();
            Usuario usuario = usuarioRepository.findById(i).orElseThrow();
            evento.setUsuario(usuario);
            // Set other properties
            eventoRepository.save(evento);
        }
    }

    private void seedTiposIngresso() {
        for (long i = 1; i <= 20; i++) {
            TipoIngresso tipoIngresso = new TipoIngresso();
            Evento evento = eventoRepository.findById(i).orElseThrow();
            tipoIngresso.setEvento(evento);
            // Set other properties
            tipoIngressoRepository.save(tipoIngresso);
        }
    }
    
    private void seedIngressos() {
        for (long i = 1; i <= 20; i++) {
            Ingresso ingresso = new Ingresso();
            TipoIngresso tIng = tipoIngressoRepository.findById(i).orElseThrow();
            ingresso.setTipoPromocao(tIng);
            ItemCompra iCompra = itemCompraRepository.findById(i).orElseThrow();
            ingresso.setItemCompra(iCompra);
            // Set other properties
            ingressoRepository.save(ingresso);
        }
    }


}


