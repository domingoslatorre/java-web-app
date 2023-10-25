package com.example.javawebapp;

import java.io.IOException;

import com.example.javawebapp.usuario.Usuario;
import com.example.javawebapp.usuario.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// localhost:8080/java-web-app-1.0/profile?id=1
@WebServlet(name = "profileServlel", value = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // usuário está logado?
        String usuarioLogado = (String) req.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // query string id está definida?
        String id = req.getParameter("id");
        if (id == null) {
            res.sendRedirect("principal.jsp");
            return;
        }

        // qual os dados do usuário logado
        Usuario usuario = UsuarioDao.buscarPorEmail(usuarioLogado);

        // usuário logado é dono do perfil que está tentando acessar?
        if (!usuario.getId().equals(Integer.parseInt(id))) {
            res.sendRedirect("principal.jsp");
            return;
        }

        req.setAttribute("usuario", usuario);
        req.getRequestDispatcher("profile.jsp").forward(req, res);
    }
}