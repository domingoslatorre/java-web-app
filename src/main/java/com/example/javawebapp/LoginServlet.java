package com.example.javawebapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.javawebapp.validators.EmailValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect("login.jsp");
    }

    // TODO: isolar validações em outro método/classe/...
    // TODO: usar expressões regulares quando possível
    // TODO: criar representação para dados de entrada, ex: classe LoginForm, LoginReq, ...
    // TODO: usar Bean validation
    // TODO: implementar número de tentativas de login
    // TODO: implementar csrf token
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        final List<String> erros = new ArrayList<>();
        
        if (email == null || email.isBlank()) {
            erros.add("E-mail não pode ser vazio");
        }

        if (senha == null || senha.isEmpty()) {
            erros.add("Senha não pode ser vazia");
        }

        if (email != null && !EmailValidator.isValid(email)) {
            erros.add("E-mail inválido");
        }

        if (senha != null && (senha.length() < 6 || senha.length() > 20)) {
            erros.add("Senha deve ter no mínimo 6 e no máximo 20 caracteres");
        }
        
        if (senha != null) {
            boolean temLetraMinuscula = false;
            boolean temLetraMaiuscula = false;
            boolean temDigito = false;
        
            for (char c : senha.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    temLetraMinuscula = true;
                } else if (Character.isUpperCase(c)) {
                    temLetraMaiuscula = true;
                } else if (Character.isDigit(c)) {
                    temDigito = true;
                }
            }

            if (!temLetraMinuscula) {
                erros.add("A Senha deve ter uma letra minúscula");
            }

            if (!temLetraMaiuscula) {
                erros.add("A Senha deve ter uma letra maiúscula");
            }

            if (!temDigito) {
                erros.add("A Senha deve ter um número");
            }
        }

        if (erros.isEmpty()) {
            res.sendRedirect("principal.jsp");
        } else {
            req.setAttribute("email", email);
            req.setAttribute("senha", senha);
            req.setAttribute("erros", erros);
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    } 
}
