package com.example.javawebapp.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.example.javawebapp.db.Conexao;

// DAO = Data Access Object
public class UsuarioDao {
    public static Usuario cadastrar(String nome, String email, String senha) {
        Usuario usuario = null;
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?);";
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, senha);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                usuario = new Usuario(rs.getInt(1), nome, email, senha);
            }

            rs.close();

            return usuario;  
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios;";
        List<Usuario> alunos = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                alunos.add(
                    new Usuario(
                        rs.getInt("id"), 
                        rs.getString("nome"), 
                        rs.getString("email"), 
                        rs.getString("senha")
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return alunos;
        } 

        return alunos;
        
    }

    public static Usuario buscarPorId(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public static Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public static Boolean login(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    public static Boolean existeComEmail(String email) {
        return buscarPorEmail(email) != null;
    }
}
