package br.com.fiap.postech.TechChallenge.repository;

import br.com.fiap.postech.TechChallenge.entities.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
}
