package br.com.fiap.postech.TechChallenge.service;

import br.com.fiap.postech.TechChallenge.controller.exception.ControllerNotFoundException;
import br.com.fiap.postech.TechChallenge.dto.CarrinhoDTO;
import br.com.fiap.postech.TechChallenge.entities.Carrinho;
import br.com.fiap.postech.TechChallenge.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CarrinhoService {

    @Autowired
    private CarrinhoRepository repo;

    public CarrinhoDTO findById(UUID id) {
        var carrinho = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Carrinho não encontrado"));
        return toCarrinhoDTO(carrinho);
    }

    public CarrinhoDTO save(CarrinhoDTO carrinhoDTO) {
        Carrinho carrinho = toCarrinho(carrinhoDTO);
        carrinho = repo.save(carrinho);
        return toCarrinhoDTO(carrinho);
    }

    public CarrinhoDTO update(UUID id, Carrinho carrinho) {
        CarrinhoDTO carrinhoDTO = toCarrinhoDTO(carrinho);
        Carrinho buscaCarrinho = repo.getReferenceById(id);
        buscaCarrinho.setUsrId(carrinhoDTO.id());
        buscaCarrinho.setProdutoId(carrinhoDTO.usrId());
        buscaCarrinho.setQnt(carrinhoDTO.qnt());

        buscaCarrinho = repo.save(buscaCarrinho);

        return toCarrinhoDTO(buscaCarrinho);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    private CarrinhoDTO toCarrinhoDTO(Carrinho carrinho) {
        return new CarrinhoDTO(carrinho.getId(),
                carrinho.getProdutoId(),
                carrinho.getUsrId(),
                carrinho.getQnt());
    }

    private Carrinho toCarrinho(CarrinhoDTO carrinhoDTO) {
        return new Carrinho(carrinhoDTO.id(),
                carrinhoDTO.produtoId(),
                carrinhoDTO.usrId(),
                carrinhoDTO.qnt());
    }
}
