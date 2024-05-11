package br.com.fiap.postech.TechChallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Collection<Produto> findAll(){
        return repo.findAll();
    }

    public Optional<Produto> findById(UUID id){
        return repo.findById(id);
    }

    public Produto save(Produto produto){
        produto = repo.save(produto);
        return produto;
    }

    public Produto update(UUID id, Produto produto){
        Produto buscaProduto = repo.getOne(id);
        buscaProduto.setNome(produto.getNome());
        buscaProduto.setDescricao(produto.getDescricao());
        buscaProduto.setUrlDaImagem(produto.getUrlDaImagem());
        buscaProduto.setPreco(produto.getPreco());

        buscaProduto = repo.save(buscaProduto);

        return buscaProduto;
    }

    public void delete(UUID id){
        repo.deleteById(id);
    }
}
