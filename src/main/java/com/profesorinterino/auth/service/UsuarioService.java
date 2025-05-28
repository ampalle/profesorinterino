package com.profesorinterino.auth.service;

import com.profesorinterino.auth.model.Usuario;
import com.profesorinterino.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepo.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepo.findByEmail(email).orElse(null);
    }

    public boolean comprobarCredenciales(String email, String password) {
        Usuario u = buscarPorEmail(email);
        return u != null && passwordEncoder.matches(password, u.getPassword());
    }
}