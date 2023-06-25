package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.EventoRepository;
import TicketPortal.models.Evento;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable("id") Long id) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento createdEvento = eventoRepository.save(evento);
        return new ResponseEntity<>(createdEvento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable("id") Long id, @RequestBody Evento evento) {
        Evento existingEvento = eventoRepository.findById(id).orElse(null);
        if (existingEvento != null) {
            existingEvento.setUsuario(evento.getUsuario());
            existingEvento.setDataEvento(evento.getDataEvento());
            existingEvento.setTitulo(evento.getTitulo());
            existingEvento.setDescricao(evento.getDescricao());
            existingEvento.setLocal(evento.getLocal());
            existingEvento.setImagemPath(evento.getImagemPath());

            Evento updatedEvento = eventoRepository.save(existingEvento);
            return new ResponseEntity<>(updatedEvento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvento(@PathVariable("id") Long id) {
        eventoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

