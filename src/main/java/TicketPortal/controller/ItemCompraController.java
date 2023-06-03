package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.ItemCompraRepository;
import TicketPortal.models.ItemCompra;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itemcompras")
public class ItemCompraController {
    
    private final ItemCompraRepository itemCompraRepository;
    
    @Autowired
    public ItemCompraController(ItemCompraRepository itemCompraRepository) {
        this.itemCompraRepository = itemCompraRepository;
    }
    
    // Create
    @PostMapping
    public ResponseEntity<ItemCompra> criarItemCompra(@RequestBody ItemCompra itemCompra) {
        ItemCompra novoItemCompra = itemCompraRepository.save(itemCompra);
        return new ResponseEntity<>(novoItemCompra, HttpStatus.CREATED);
    }
    
    // Read all
    @GetMapping
    public ResponseEntity<List<ItemCompra>> listarItensCompra() {
        List<ItemCompra> itensCompra = itemCompraRepository.findAll();
        return new ResponseEntity<>(itensCompra, HttpStatus.OK);
    }
    
    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<ItemCompra> buscarItemCompraPorId(@PathVariable("id") Long id) {
        Optional<ItemCompra> itemCompraOptional = itemCompraRepository.findById(id);
        if (itemCompraOptional.isPresent()) {
            return new ResponseEntity<>(itemCompraOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ItemCompra> atualizarItemCompra(@PathVariable("id") Long id, @RequestBody ItemCompra itemCompraAtualizado) {
        Optional<ItemCompra> itemCompraOptional = itemCompraRepository.findById(id);
        if (itemCompraOptional.isPresent()) {
            ItemCompra itemCompraExistente = itemCompraOptional.get();
            itemCompraExistente.setCompra(itemCompraAtualizado.getCompra());
            itemCompraExistente.setIngresso(itemCompraAtualizado.getIngresso());
            itemCompraExistente.setQuantidade(itemCompraAtualizado.getQuantidade());
            itemCompraExistente.setValorItem(itemCompraAtualizado.getValorItem());
            
            ItemCompra itemCompraAtualizadoDB = itemCompraRepository.save(itemCompraExistente);
            return new ResponseEntity<>(itemCompraAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirItemCompra(@PathVariable("id") Long id) {
        Optional<ItemCompra> itemCompraOptional = itemCompraRepository.findById(id);
        if (itemCompraOptional.isPresent()) {
            itemCompraRepository.delete(itemCompraOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
