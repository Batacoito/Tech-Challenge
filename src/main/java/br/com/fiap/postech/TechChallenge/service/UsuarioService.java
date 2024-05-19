package br.com.fiap.postech.TechChallenge.service;

import br.com.fiap.postech.TechChallenge.dto.UsuarioDTO;
import br.com.fiap.postech.TechChallenge.entities.Usuario;
import br.com.fiap.postech.TechChallenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = toUsuario(usuarioDTO);
        usuario = repo.save(usuario);
        return toUsuarioDTO(usuario);
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsr());
    }

    private Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.senha(),
                usuarioDTO.tipoUsr());
    }

}
