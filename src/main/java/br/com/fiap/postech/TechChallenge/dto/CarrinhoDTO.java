package br.com.fiap.postech.TechChallenge.dto;

import java.util.UUID;

public record CarrinhoDTO(UUID id, UUID produtoId, UUID usrId, int qnt) {
}
