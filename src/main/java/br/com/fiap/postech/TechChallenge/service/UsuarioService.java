package br.com.fiap.postech.TechChallenge.service;

import br.com.fiap.postech.TechChallenge.controller.exception.ControllerNotFoundException;
import br.com.fiap.postech.TechChallenge.dto.ProdutoDTO;
import br.com.fiap.postech.TechChallenge.dto.UsuarioDTO;
import br.com.fiap.postech.TechChallenge.entities.Produto;
import br.com.fiap.postech.TechChallenge.entities.Usuario;
import br.com.fiap.postech.TechChallenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = toUsuario(usuarioDTO);
        usuario = repo.save(usuario);
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO update(UUID id, UsuarioDTO usuarioDTO) {
        try {
            Usuario buscaUsuario = repo.getReferenceById(id);
            buscaUsuario.setNome(usuarioDTO.nome());
            buscaUsuario.setEmail(usuarioDTO.email());
            buscaUsuario.setSenha(usuarioDTO.senha());
            buscaUsuario.setTipoUsuario(usuarioDTO.tipoUsr());

            buscaUsuario = repo.save(buscaUsuario);

            return toUsuarioDTO(buscaUsuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario não encontrado");
        }
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsuario());
    }

    private Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.senha(),
                usuarioDTO.tipoUsr());
    }

}
