package br.com.fiap.postech.TechChallenge.controller;

import br.com.fiap.postech.TechChallenge.dto.CarrinhoDTO;
import br.com.fiap.postech.TechChallenge.entities.Carrinho;
import br.com.fiap.postech.TechChallenge.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;

    @PutMapping("/{id}/sub")
    public ResponseEntity<CarrinhoDTO> subCarrinho(@PathVariable UUID id, @RequestBody CarrinhoDTO carrinhoDTO){
        if (carrinhoDTO.qnt() == 1) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            Carrinho carrinho = new Carrinho(carrinhoDTO.id(), carrinhoDTO.produtoId(), carrinhoDTO.usrId(), carrinhoDTO.qnt() - 1);
            carrinhoDTO = service.update(id, carrinho);
            return ResponseEntity.ok(carrinhoDTO);
        }
    }

    @PutMapping("/{id}/add")
    public ResponseEntity<CarrinhoDTO> addCarrinho(@PathVariable UUID id, @RequestBody CarrinhoDTO carrinhoDTO){
        if (service.findById(id) != null) {
            Carrinho carrinho = new Carrinho(carrinhoDTO.id(), carrinhoDTO.produtoId(), carrinhoDTO.usrId(), carrinhoDTO.qnt() + 1);
            carrinhoDTO = service.update(id, carrinho);
            return ResponseEntity.ok(carrinhoDTO);
        }
        else {
            carrinhoDTO = service.save(carrinhoDTO);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(carrinhoDTO);
        }
    }

}
