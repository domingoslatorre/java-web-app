package com.example.javawebapp.usuario;

import java.util.*;

// DAO = Data Access Object
public class UsuarioDao {
    private static Integer idAtual = 0;
    private static List<Usuario> usuarios = new ArrayList<>();

    // INSERT INTO usuarios ...
    public static Usuario cadastrar(String nome, String email, String senha) {
        Usuario usuario = new Usuario(++idAtual, nome, email, senha);
        usuarios.add(usuario);
        return usuario;
    }

    // SELECT * FROM usuarios
    public static List<Usuario> listarTodos() {
        return usuarios;
    }

    // SELECT * FROM usuarios WHERE id = ?
    public static Usuario buscarPorId(Integer id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    // SELECT * FROM usuarios WHERE email = ?
    public static Usuario buscarPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    // Entrada = email e senha
    // Saída = existe um usuario com aquele email e senha (Boolean)
    // SELECT * FROM usuarios WHERE email = ? AND senha = ?
    public static Boolean login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (
                usuario.getEmail().equals(email) 
                && usuario.getSenha().equals(senha)
            ) {
                return true;
            }
        }
        return false;
    }

    // Entrada - email
    // Saida - se existe ou nao o usuario com aquele email
    public static Boolean existeComEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
