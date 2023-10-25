package com.example.javawebapp;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "protegidoServlet", value = "/protegido")
public class ProtegidoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String usuarioLogado = (String) req.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            res.sendRedirect("login.jsp");
            return;
        } 

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Protegido</h1>");
        out.println("</body></html>");
    }
}