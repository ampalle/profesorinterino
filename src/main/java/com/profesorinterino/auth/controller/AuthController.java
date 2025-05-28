package com.profesorinterino.auth.controller;

import com.profesorinterino.auth.model.Usuario;
import com.profesorinterino.auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private UsuarioService usuarioService;

    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        boolean valido = usuarioService.comprobarCredenciales(usuario.getEmail(), usuario.getPassword());
        return valido ? "Login correcto" : "Credenciales incorrectas";
    }
}