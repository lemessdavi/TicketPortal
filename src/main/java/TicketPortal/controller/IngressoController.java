package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.IngressoRepository;
import TicketPortal.models.Ingresso;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    private IngressoRepository ingressoRepository;

    @GetMapping
    public List<Ingresso> getAllIngressos() {
        return ingressoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> getIngressoById(@PathVariable("id") Long id) {
        Ingresso ingresso = ingressoRepository.findById(id).orElse(null);
        if (ingresso != null) {
            return new ResponseEntity<>(ingresso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ingresso> createIngresso(@RequestBody Ingresso ingresso) {
        Ingresso createdIngresso = ingressoRepository.save(ingresso);
        return new ResponseEntity<>(createdIngresso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingresso> updateIngresso(@PathVariable("id") Long id, @RequestBody Ingresso ingresso) {
        Ingresso existingIngresso = ingressoRepository.findById(id).orElse(null);
        if (existingIngresso != null) {
            existingIngresso.setTipoPromocao(ingresso.getTipoPromocao());
            existingIngresso.setItemCompra(ingresso.getItemCompra());

            Ingresso updatedIngresso = ingressoRepository.save(existingIngresso);
            return new ResponseEntity<>(updatedIngresso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteIngresso(@PathVariable("id") Long id) {
        ingressoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

