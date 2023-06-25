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
@RequestMapping("/tipo-ingressos")
public class TipoIngressoController {

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    @GetMapping
    public List<TipoIngresso> getAllTipoIngressos() {
        return tipoIngressoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoIngresso> getTipoIngressoById(@PathVariable("id") Long id) {
        TipoIngresso tipoIngresso = tipoIngressoRepository.findById(id).orElse(null);
        if (tipoIngresso != null) {
            return new ResponseEntity<>(tipoIngresso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TipoIngresso> createTipoIngresso(@RequestBody TipoIngresso tipoIngresso) {
        TipoIngresso createdTipoIngresso = tipoIngressoRepository.save(tipoIngresso);
        return new ResponseEntity<>(createdTipoIngresso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoIngresso> updateTipoIngresso(@PathVariable("id") Long id, @RequestBody TipoIngresso tipoIngresso) {
        TipoIngresso existingTipoIngresso = tipoIngressoRepository.findById(id).orElse(null);
        if (existingTipoIngresso != null) {
            existingTipoIngresso.setEvento(tipoIngresso.getEvento());
            existingTipoIngresso.setTitulo(tipoIngresso.getTitulo());
            existingTipoIngresso.setValorNormal(tipoIngresso.getValorNormal());
            existingTipoIngresso.setQuantidade(tipoIngresso.getQuantidade());
            existingTipoIngresso.setDesconto(tipoIngresso.getDesconto());
            existingTipoIngresso.setLoteNumero(tipoIngresso.getLoteNumero());

            TipoIngresso updatedTipoIngresso = tipoIngressoRepository.save(existingTipoIngresso);
            return new ResponseEntity<>(updatedTipoIngresso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTipoIngresso(@PathVariable("id") Long id) {
        tipoIngressoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

