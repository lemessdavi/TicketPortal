package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.ItemCompraRepository;
import TicketPortal.models.ItemCompra;

import java.util.List;

@RestController
@RequestMapping("/itemcompras")
public class ItemCompraController {

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @GetMapping
    public List<ItemCompra> getAllItemCompras() {
        return itemCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCompra> getItemCompraById(@PathVariable("id") Long id) {
        ItemCompra itemCompra = itemCompraRepository.findById(id).orElse(null);
        if (itemCompra != null) {
            return new ResponseEntity<>(itemCompra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ItemCompra> createItemCompra(@RequestBody ItemCompra itemCompra) {
        ItemCompra createdItemCompra = itemCompraRepository.save(itemCompra);
        return new ResponseEntity<>(createdItemCompra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCompra> updateItemCompra(@PathVariable("id") Long id, @RequestBody ItemCompra itemCompra) {
        ItemCompra existingItemCompra = itemCompraRepository.findById(id).orElse(null);
        if (existingItemCompra != null) {
            existingItemCompra.setCompra(itemCompra.getCompra());
            existingItemCompra.setQuantidade(itemCompra.getQuantidade());
            existingItemCompra.setValorItem(itemCompra.getValorItem());

            ItemCompra updatedItemCompra = itemCompraRepository.save(existingItemCompra);
            return new ResponseEntity<>(updatedItemCompra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItemCompra(@PathVariable("id") Long id) {
        itemCompraRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

