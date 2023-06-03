package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.EventoRepository;
import TicketPortal.models.Evento;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // Create
    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        Evento novoEvento = eventoRepository.save(evento);
        return new ResponseEntity<>(novoEvento, HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPorId(@PathVariable("id") Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            return new ResponseEntity<>(eventoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable("id") Long id, @RequestBody Evento eventoAtualizado) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            Evento eventoExistente = eventoOptional.get();
            eventoExistente.setUsuario(eventoAtualizado.getUsuario());
            eventoExistente.setDataEvento(eventoAtualizado.getDataEvento());
            eventoExistente.setTitulo(eventoAtualizado.getTitulo());
            eventoExistente.setDescricao(eventoAtualizado.getDescricao());
            eventoExistente.setLocal(eventoAtualizado.getLocal());

            Evento eventoAtualizadoDB = eventoRepository.save(eventoExistente);
            return new ResponseEntity<>(eventoAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirEvento(@PathVariable("id") Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            eventoRepository.delete(eventoOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
