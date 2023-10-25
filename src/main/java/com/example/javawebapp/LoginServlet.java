package com.example.javawebapp;

import java.io.IOException;
import java.util.Set;

import com.example.javawebapp.forms.LoginForm;
import com.example.javawebapp.usuario.UsuarioDao;
import com.example.javawebapp.validators.ValidatorUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        if (usuarioLogado != null) {
            res.sendRedirect("principal.jsp");
        } else {
            res.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        LoginForm loginForm = new LoginForm(email, senha);
     
        Set<ConstraintViolation<LoginForm>> violations = ValidatorUtil.validateObject(loginForm);
        
        if (violations.isEmpty()) {
            if (UsuarioDao.login(email, senha)) {
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", email);
                res.sendRedirect("principal.jsp");
            } else {
                req.setAttribute("loginError", "login inválido");
                req.setAttribute("loginForm", loginForm);
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("loginForm", loginForm);
            req.setAttribute("violations", violations);
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    } 
}
