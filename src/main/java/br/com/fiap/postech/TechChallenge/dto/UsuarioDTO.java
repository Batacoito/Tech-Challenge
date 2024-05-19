package br.com.fiap.postech.TechChallenge.dto;

import java.util.UUID;

public record UsuarioDTO(UUID id, String nome, String email, String senha, String tipoUsr) {
}
