package br.com.fiap.postech.TechChallenge.dto;

import java.util.UUID;

public record ProdutoDTO(UUID id, String nome, String descricao, double preco, String urlDaImagem, String cor, String tam, int qnt) {
}
