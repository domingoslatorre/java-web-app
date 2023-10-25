package com.example.javawebapp.usuario;

import java.util.List;
import java.util.ArrayList;

public class UsuarioDao {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Integer idAtual = 0;

    static {
        cadastrar("Maria", "maria@email.com", "123");
    }

    public static Usuario cadastrar(String nome, String email, String senha) {
        Usuario usuario = new Usuario(++idAtual, nome, email, senha);
        usuarios.add(usuario);
        return usuario;
    }

    public static List<Usuario> buscarTodos() {
        return usuarios;
    }

    public static Usuario buscarPorId(Integer id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario buscarPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }
    
    public static Boolean login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
