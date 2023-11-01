package com.example.javawebapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.javawebapp.forms.LoginForm;
import com.example.javawebapp.usuario.UsuarioDao;
import com.example.javawebapp.validators.EmailValidator;
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
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, res);
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

        LoginForm loginForm = new LoginForm(email, senha);

        Set<ConstraintViolation<LoginForm>> violations = 
            ValidatorUtil.validateObject(loginForm);

        if (violations.isEmpty()) {
            if (UsuarioDao.login(email, senha)) {
                HttpSession session = req.getSession();
                session.setAttribute("emailUsuario", email);
                res.sendRedirect("index");
            } else {
                req.setAttribute("errorLogin", "E-mail ou senha incorretos");
                req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("email", email);
            req.setAttribute("senha", senha);
            req.setAttribute("violations", violations);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, res);
        }
    } 
}
