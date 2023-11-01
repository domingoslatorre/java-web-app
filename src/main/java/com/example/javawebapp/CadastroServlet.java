package com.example.javawebapp;

import java.io.IOException;
import java.util.Set;

import com.example.javawebapp.forms.CadastroForm;
import com.example.javawebapp.usuario.UsuarioDao;
import com.example.javawebapp.validators.ValidatorUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;

@WebServlet(name = "cadastroServlet", value = "/cadastro")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/cadastro.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        CadastroForm cadastroForm = new CadastroForm(nome, email, senha);
        
        Set<ConstraintViolation<CadastroForm>> violations = ValidatorUtil.validateObject(cadastroForm);
        
        if (violations.isEmpty()) {
            if (UsuarioDao.existeComEmail(email)) {
                // mandar erro na tela
                req.setAttribute("existeErro", "Já existe um usuário com esse e-mail");
                req.getRequestDispatcher("WEB-INF/cadastro.jsp").forward(req, res);
            } else {
                UsuarioDao.cadastrar(nome, email, senha);
                res.sendRedirect("login");
            }
        } else {
            req.setAttribute("cadastroForm", cadastroForm);
            req.setAttribute("violations", violations);
            req.getRequestDispatcher("WEB-INF/cadastro.jsp").forward(req, res);
        }
    }
    
   
}
