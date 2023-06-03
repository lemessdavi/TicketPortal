package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.CompraRepository;
import TicketPortal.models.Compra;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {
    
    private final CompraRepository compraRepository;
    
    @Autowired
    public CompraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }
    
    // Create
    @PostMapping
    public ResponseEntity<Compra> criarCompra(@RequestBody Compra compra) {
        Compra novaCompra = compraRepository.save(compra);
        return new ResponseEntity<>(novaCompra, HttpStatus.CREATED);
    }
    
    // Read all
    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras() {
        List<Compra> compras = compraRepository.findAll();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }
    
    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarCompraPorId(@PathVariable("id") Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            return new ResponseEntity<>(compraOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Compra> atualizarCompra(@PathVariable("id") Long id, @RequestBody Compra compraAtualizada) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            Compra compraExistente = compraOptional.get();
            compraExistente.setUsuario(compraAtualizada.getUsuario());
            compraExistente.setDataCompra(compraAtualizada.getDataCompra());
            compraExistente.setSituacao(compraAtualizada.getSituacao());
            compraExistente.setValorTotal(compraAtualizada.getValorTotal());
            
            Compra compraAtualizadaDB = compraRepository.save(compraExistente);
            return new ResponseEntity<>(compraAtualizadaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirCompra(@PathVariable("id") Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            compraRepository.delete(compraOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
