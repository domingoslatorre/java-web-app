package com.example.javawebapp;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        session.removeAttribute("usuarioLogado");

        res.sendRedirect("login.jsp");
    }
}