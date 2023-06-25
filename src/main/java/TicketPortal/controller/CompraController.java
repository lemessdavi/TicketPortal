package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.CompraRepository;
import TicketPortal.models.Compra;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable("id") Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra != null) {
            return new ResponseEntity<>(compra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
        Compra createdCompra = compraRepository.save(compra);
        return new ResponseEntity<>(createdCompra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable("id") Long id, @RequestBody Compra compra) {
        Compra existingCompra = compraRepository.findById(id).orElse(null);
        if (existingCompra != null) {
            existingCompra.setUsuario(compra.getUsuario());
            existingCompra.setDataCompra(compra.getDataCompra());
            existingCompra.setSituacao(compra.getSituacao());
            existingCompra.setValorTotal(compra.getValorTotal());

            Compra updatedCompra = compraRepository.save(existingCompra);
            return new ResponseEntity<>(updatedCompra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCompra(@PathVariable("id") Long id) {
        compraRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

