package br.com.fiap.postech.TechChallenge.controller;

import br.com.fiap.postech.TechChallenge.dto.ProdutoDTO;
import br.com.fiap.postech.TechChallenge.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping // Busca todos os produtos
    public ResponseEntity<Collection<ProdutoDTO>> findAll(){
        var produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}") // Busca produto específico pelo ID
    public ResponseEntity<ProdutoDTO> findById(@PathVariable UUID id){
        var produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping // Cadastra produto novo
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produtoDTO){
        produtoDTO = service.save(produtoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(produtoDTO);
    }

    @PutMapping("/{id}") // Atualiza cadastro do produto
    public ResponseEntity<ProdutoDTO> update(@PathVariable UUID id, @RequestBody ProdutoDTO produtoDTO){
        produtoDTO = service.update(id, produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/{id}") // Deleta produto do sistema (alterar para desativar ao invés de deletar)
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
