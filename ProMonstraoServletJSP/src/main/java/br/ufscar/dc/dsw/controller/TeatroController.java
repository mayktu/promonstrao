package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.TeatroDAO;
import br.ufscar.dc.dsw.domain.Teatro;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.ParamParser;

@WebServlet(urlPatterns = "/teatro/*")
public class TeatroController extends HttpServlet {

    private static final long serialVersionUID = 1L;


    private TeatroDAO teatroDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        teatroDAO = new TeatroDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            Erro erros = new Erro();
            Locale locale = request.getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("message", locale);

            String action = request.getPathInfo();
            action = action != null ? action : "";
            if (action.equals("/cadastrar")) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String nome = request.getParameter("nome");
                String cnpj = request.getParameter("cnpj");
                String cidade = request.getParameter("cidade");

                if (email == null || email.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.email-missing"));
                }

                if (senha == null || senha.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.password-missing"));
                }

                if (nome == null || nome.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.name-missing"));
                }

                if (cnpj == null || cnpj.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.cnpj-missing"));
                }

                if (cidade == null || cidade.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.city-missing"));
                }

                if (!erros.hasErros()) {

                    Teatro teatro = teatroDAO.getByEmail(email);
                    if (teatro != null) {
                        erros.add(bundle.getString("errors.theater.email-used"));
                        request.setAttribute("erros", erros);
                        request.getRequestDispatcher("/WEB-INF/jsp/teatro/cadastrar.jsp")
                                .forward(request, response);
                    } else {
                        teatro = new Teatro(email, nome, cnpj, cidade);
                        teatroDAO.insert(teatro, senha);
                    }
                    response.sendRedirect(request.getContextPath() + "/teatro");
                } else {
                    request.setAttribute("erros", erros);
                    request.getRequestDispatcher("/WEB-INF/jsp/teatro/cadastrar.jsp")
                            .forward(request, response);
                }
            } else if (action.equals("/editar")) {
                long id = Long.parseLong(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String cnpj = request.getParameter("cnpj");
                String cidade = request.getParameter("cidade");

                if (nome == null || nome.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.name-missing"));
                }

                if (cnpj == null || cnpj.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.cnpj-missing"));
                }

                if (cidade == null || cidade.isEmpty()) {
                    erros.add(bundle.getString("errors.theater.city-missing"));
                }

                if (!erros.hasErros()) {
                    Teatro teatro = new Teatro(id, null, nome, cnpj, cidade);
                    teatroDAO.update(teatro);
                    response.sendRedirect(request.getContextPath() + "/teatro");
                } else {
                    request.setAttribute("erros", erros);
                    request.getRequestDispatcher("/WEB-INF/jsp/teatro/editar.jsp")
                            .forward(request, response);
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/teatro");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getPathInfo();
        action = action != null ? action : "";

        if (action.equals("") || action.equals("/")) {
            list(request, response);
        } else if (action.equals("/cadastrar")) {
            request.getRequestDispatcher("/WEB-INF/jsp/teatro/cadastrar.jsp")
                    .forward(request, response);
        } else if (action.equals("/editar")) {
            String idParam = request.getParameter("id");
            Long id = ParamParser.parseLong(idParam);
            if (id != null) {
                Teatro teatro = teatroDAO.getById(id);
                if (teatro == null) {
                    redirectToNotFound(request, response);
                }
                request.setAttribute("teatro", teatro);
                request.getRequestDispatcher("/WEB-INF/jsp/teatro/editar.jsp")
                        .forward(request, response);
            } else {
                redirectToNotFound(request, response);
            }
        } else if (action.equals("/deletar")) {
            String idParam = request.getParameter("id");
            Long id = ParamParser.parseLong(idParam);
            if (id != null) teatroDAO.delete(id);
            response.sendRedirect(request.getContextPath() + "/teatro");
        } else {
            redirectToNotFound(request, response);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Teatro> lista = teatroDAO.getAll();
        request.setAttribute("listaTeatros", lista);
        request.getRequestDispatcher("/WEB-INF/jsp/teatro/index.jsp")
                .forward(request, response);
    }

    private void redirectToNotFound(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/not-found.jsp")
                .forward(request, response);
    }
}