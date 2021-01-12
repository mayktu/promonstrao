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

import br.ufscar.dc.dsw.dao.SiteDao;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.ParamParser;

@WebServlet(urlPatterns = "/site/*")
public class SiteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private SiteDao siteDao;

    @Override
    public void init() throws ServletException {
        super.init();
        siteDao = new SiteDao();
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
                String endereco = request.getParameter("endereco");
                String telefone = request.getParameter("telefone");

                if (email == null || email.isEmpty()) {
                    erros.add(bundle.getString("errors.website.email-missing"));
                }

                if (senha == null || senha.isEmpty()) {
                    erros.add(bundle.getString("errors.website.password-missing"));
                }

                if (nome == null || nome.isEmpty()) {
                    erros.add(bundle.getString("errors.website.name-missing"));
                }

                if (endereco == null || endereco.isEmpty()) {
                    erros.add(bundle.getString("errors.website.url-missing"));
                }

                if (telefone == null || telefone.isEmpty()) {
                    erros.add(bundle.getString("errors.website.phone-missing"));
                }

                if (!erros.hasErros()) {
                    Site site = siteDao.getByEmail(email);
                    if (site != null) {
                        erros.add(bundle.getString("errors.website.email-used"));
                        request.setAttribute("erros", erros);
                        request.getRequestDispatcher("/WEB-INF/jsp/site/cadastrar.jsp")
                                .forward(request, response);
                              
                        return;
                    }

                    site = new Site(email, nome, endereco, telefone);
                    siteDao.insert(site, senha);
                    response.sendRedirect(request.getContextPath() + "/site");
                    
                } else {
                    request.setAttribute("erros", erros);
                    request.getRequestDispatcher("/WEB-INF/jsp/site/cadastrar.jsp")
                            .forward(request, response);
                }
            } else if (action.equals("/editar")) {
                long id = Long.parseLong(request.getParameter("id"));

                String nome = request.getParameter("nome");
                String endereco = request.getParameter("endereco");
                String telefone = request.getParameter("telefone");

                if (nome == null || nome.isEmpty()) {
                    erros.add(bundle.getString("errors.website.name-missing"));
                }

                if (endereco == null || endereco.isEmpty()) {
                    erros.add(bundle.getString("errors.website.url-missing"));
                }

                if (telefone == null || telefone.isEmpty()) {
                    erros.add(bundle.getString("errors.website.phone-missing"));
                }

                if (!erros.hasErros()) {
                    Site site = new Site(id, nome, endereco, telefone);
                    siteDao.update(site);
                    response.sendRedirect(request.getContextPath() + "/site");
                } else {
                    request.setAttribute("erros", erros);
                    request.getRequestDispatcher("/WEB-INF/jsp/site/editar.jsp")
                            .forward(request, response);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/site");
            }

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
            request.getRequestDispatcher("/WEB-INF/jsp/site/cadastrar.jsp")
                    .forward(request, response);
        } else if (action.equals("/editar")) {
            String idParam = request.getParameter("id");
            Long id = ParamParser.parseLong(idParam);
            if (id != null) {
                Site site = siteDao.getById(id);
                if (site == null) {
                    redirectToNotFound(request, response);
                }
                request.setAttribute("site", site);
                request.getRequestDispatcher("/WEB-INF/jsp/site/editar.jsp")
                        .forward(request, response);
            } else {
                redirectToNotFound(request, response);
            }
        } else if (action.equals("/deletar")) {
            String idParam = request.getParameter("id");
            Long id = ParamParser.parseLong(idParam);
            if (id != null) siteDao.delete(id);
            response.sendRedirect(request.getContextPath() + "/site");
        } else {
            redirectToNotFound(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Site> lista = siteDao.getAll();
        request.setAttribute("listaSites", lista);
        request.getRequestDispatcher("/WEB-INF/jsp/site/index.jsp")
                .forward(request, response);
    }

    private void redirectToNotFound(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/not-found.jsp")
                .forward(request, response);
    }
}