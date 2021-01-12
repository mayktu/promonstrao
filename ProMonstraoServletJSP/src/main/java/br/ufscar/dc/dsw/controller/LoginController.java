package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro errors = new Erro();
        Locale locale = request.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);

        if (request.getParameter("bOK") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email == null || email.isEmpty()) {
                errors.add(bundle.getString("errors.email-missing"));
            }
            if (password == null || password.isEmpty()) {


                errors.add(bundle.getString("errors.password-missing"));
            }
            if (!errors.hasErros()) {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.getByEmail(email);
                if (usuario != null) {
                    if (usuario.getSenha().equals(password)) {
                        request.getSession().setAttribute("usuarioLogado", usuario);

                        response.sendRedirect(request.getContextPath());
                        return;
                    } else {
                        errors.add(bundle.getString("errors.credentials-invalid"));
                    }
                } else {
                    errors.add(bundle.getString("errors.credentials-invalid"));
                }
            }
        }
        request.getSession().invalidate();
        request.setAttribute("mensagens", errors);

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario != null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                .forward(request, response);
    }
}