package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.TipoIngressoRepository;
import TicketPortal.models.TipoIngresso;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-ingresso")
public class TipoIngressoController {

    private final TipoIngressoRepository tipoIngressoRepository;

    @Autowired
    public TipoIngressoController(TipoIngressoRepository tipoIngressoRepository) {
        this.tipoIngressoRepository = tipoIngressoRepository;
    }

    // Create
    @PostMapping
    public ResponseEntity<TipoIngresso> criarTipoIngresso(@RequestBody TipoIngresso tipoIngresso) {
        TipoIngresso novoTipoIngresso = tipoIngressoRepository.save(tipoIngresso);
        return new ResponseEntity<>(novoTipoIngresso, HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<TipoIngresso>> listarTiposIngresso() {
        List<TipoIngresso> tiposIngresso = tipoIngressoRepository.findAll();
        return new ResponseEntity<>(tiposIngresso, HttpStatus.OK);
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<TipoIngresso> buscarTipoIngressoPorId(@PathVariable("id") Long id) {
        Optional<TipoIngresso> tipoIngressoOptional = tipoIngressoRepository.findById(id);
        if (tipoIngressoOptional.isPresent()) {
            return new ResponseEntity<>(tipoIngressoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<TipoIngresso> atualizarTipoIngresso(@PathVariable("id") Long id, @RequestBody TipoIngresso tipoIngressoAtualizado) {
        Optional<TipoIngresso> tipoIngressoOptional = tipoIngressoRepository.findById(id);
        if (tipoIngressoOptional.isPresent()) {
            TipoIngresso tipoIngressoExistente = tipoIngressoOptional.get();
            tipoIngressoExistente.setValorNormal(tipoIngressoAtualizado.getValorNormal());
            tipoIngressoExistente.setDesconto(tipoIngressoAtualizado.getDesconto());
            tipoIngressoExistente.setLote(tipoIngressoAtualizado.getLote());

            TipoIngresso tipoIngressoAtualizadoDB = tipoIngressoRepository.save(tipoIngressoExistente);
            return new ResponseEntity<>(tipoIngressoAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirTipoIngresso(@PathVariable("id") Long id) {
        Optional<TipoIngresso> tipoIngressoOptional = tipoIngressoRepository.findById(id);
        if (tipoIngressoOptional.isPresent()) {
            tipoIngressoRepository.delete(tipoIngressoOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
