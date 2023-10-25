package com.example.javawebapp;

import java.io.IOException;

import com.example.javawebapp.usuario.Usuario;
import com.example.javawebapp.usuario.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String emailUsuario = (String) session.getAttribute("emailUsuario");
        Usuario usuario = UsuarioDao.buscarPorEmail(emailUsuario);
        
        req.setAttribute("usuario", usuario);
        req.getRequestDispatcher("WEB-INF/profile.jsp").forward(req, res);
    }
}
