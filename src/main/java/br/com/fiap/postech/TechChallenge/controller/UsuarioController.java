package br.com.fiap.postech.TechChallenge.controller;

import br.com.fiap.postech.TechChallenge.dto.UsuarioDTO;
import br.com.fiap.postech.TechChallenge.entities.Usuario;
import br.com.fiap.postech.TechChallenge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping() // Novo usuário
    public ResponseEntity<UsuarioDTO> post(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = service.save(usuarioDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(usuarioDTO);
    }

    @PutMapping("/{id}") // Atualiza usuário
    public ResponseEntity<UsuarioDTO> put(@PathVariable UUID id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = service.update(id, usuarioDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(usuarioDTO);
    }

    @PostMapping("/login") // Login
    public ResponseEntity<UsuarioDTO> get(@RequestBody Usuario user) {
        Collection<UsuarioDTO> usuariosDTO = service.findAll();
        for (UsuarioDTO usuarioDTO : usuariosDTO) {
            System.out.println(usuarioDTO);
            System.out.println(user);
            if (usuarioDTO.email().equals(user.getEmail()) && usuarioDTO.senha().equals(user.getSenha())){
                return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(usuarioDTO);
            }
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(null);
    }
}
