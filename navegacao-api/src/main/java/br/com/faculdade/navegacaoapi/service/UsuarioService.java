package br.com.faculdade.navegacaoapi.service;

import br.com.faculdade.navegacaoapi.model.Usuario;
import br.com.faculdade.navegacaoapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void deletarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }
}