package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getUsuario (Long id) {
        Usuario usuario001 = new Usuario();

//        usuario001.setId(id);
//        usuario001.setNombre("Santiago");
//        usuario001.setApellido("Monrroy");
//        usuario001.setEmail("sanmon@email.com");
//        usuario001.setTelefono("3195721674");

        return usuario001;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if (!validarToken(token)) {return;}

        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios (@RequestHeader(value = "Authorization") String token) {
        if (!validarToken(token)) {return null;}

        String usuarioId = jwtUtil.getKey(token);

        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario (@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    private boolean validarToken (String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
}
