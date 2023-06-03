package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.IngressoRepository;
import TicketPortal.models.Ingresso;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoRepository ingressoRepository;

    @Autowired
    public IngressoController(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    // Create
    @PostMapping
    public ResponseEntity<Ingresso> criarIngresso(@RequestBody Ingresso ingresso) {
        Ingresso novoIngresso = ingressoRepository.save(ingresso);
        return new ResponseEntity<>(novoIngresso, HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Ingresso>> listarIngressos() {
        List<Ingresso> ingressos = ingressoRepository.findAll();
        return new ResponseEntity<>(ingressos, HttpStatus.OK);
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> buscarIngressoPorId(@PathVariable("id") Long id) {
        Optional<Ingresso> ingressoOptional = ingressoRepository.findById(id);
        if (ingressoOptional.isPresent()) {
            return new ResponseEntity<>(ingressoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Ingresso> atualizarIngresso(@PathVariable("id") Long id, @RequestBody Ingresso ingressoAtualizado) {
        Optional<Ingresso> ingressoOptional = ingressoRepository.findById(id);
        if (ingressoOptional.isPresent()) {
            Ingresso ingressoExistente = ingressoOptional.get();
            ingressoExistente.setEvento(ingressoAtualizado.getEvento());
            ingressoExistente.setTipoPromocao(ingressoAtualizado.getTipoPromocao());

            Ingresso ingressoAtualizadoDB = ingressoRepository.save(ingressoExistente);
            return new ResponseEntity<>(ingressoAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirIngresso(@PathVariable("id") Long id) {
        Optional<Ingresso> ingressoOptional = ingressoRepository.findById(id);
        if (ingressoOptional.isPresent()) {
            ingressoRepository.delete(ingressoOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
