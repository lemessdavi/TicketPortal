package TicketPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TicketPortal.dao.UsuarioRepository;
import TicketPortal.models.Usuario;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    // Create
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
    
    // Read all
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setDocumento(usuarioAtualizado.getDocumento());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
            usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
            
            Usuario usuarioAtualizadoDB = usuarioRepository.save(usuarioExistente);
            return new ResponseEntity<>(usuarioAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirUsuario(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
