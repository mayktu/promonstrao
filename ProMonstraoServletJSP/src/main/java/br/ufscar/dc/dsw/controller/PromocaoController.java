package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.PromocaoDAO;
import br.ufscar.dc.dsw.dao.SiteDao;
import br.ufscar.dc.dsw.dao.TeatroDAO;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.PromocaoDisplay;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.ParamParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/promocao/*"})
public class PromocaoController extends HttpServlet {

    PromocaoDAO promocaoDAO;
    SiteDao siteDao;
    TeatroDAO teatroDao;

    @Override
    public void init() throws ServletException {
        super.init();
        promocaoDAO = new PromocaoDAO();
        siteDao = new SiteDao();
        teatroDao = new TeatroDAO();
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
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
                Long idSite = ParamParser.parseLong(request.getParameter("id_site"));
                String nome = request.getParameter("nome");
                Float preco = ParamParser.parseFloat(request.getParameter("preco"));
                String data = request.getParameter("data");

                if (idSite == null) {
                    erros.add(bundle.getString("errors.promotion.site-missing"));
                }

                if (nome == null || nome.isEmpty()) {
                    erros.add(bundle.getString("errors.promotion.name-missing"));
                }

                if (preco == null) {
                    erros.add(bundle.getString("errors.promotion.price-missing-invalid"));
                }

                if (data == null || data.isEmpty()) {
                    erros.add(bundle.getString("errors.promotion.date-missing-invalid"));
                }

                if (!erros.hasErros()) {
                    Promocao promocao = new Promocao(null, idSite, usuario.getId(), nome, preco, data);

                    if (promocaoDAO.checkIfExists(promocao)) {
                        erros.add(bundle.getString("errors.promotion.date-already-used"));
                        request.setAttribute("erros", erros);
                        request.getRequestDispatcher("/WEB-INF/jsp/promocao/cadastrar.jsp")
                                .forward(request, response);
                        return;
                    }
                    promocaoDAO.insert(promocao);
                } else {
                    request.setAttribute("erros", erros);
                    request.getRequestDispatcher("/WEB-INF/jsp/promocao/cadastrar.jsp")
                            .forward(request, response);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/promocao");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        action = action != null ? action : "";

        if (action.equals("") || action.equals("/")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
            long idSite = usuario.getId();
            List<PromocaoDisplay> lista = new ArrayList<>();
            if (usuario.getPapel().equals("SITE")) {
                lista = promocaoDAO.getBySite(idSite);
            } else if (usuario.getPapel().equals("TEATRO")) {
                lista = promocaoDAO.getByTeatro(idSite);
            }
            request.setAttribute("listaPromocao", lista);
            request.getRequestDispatcher("/WEB-INF/jsp/promocao/index.jsp")
                    .forward(request, response);
        } else if (action.equals("/cadastrar")) {
            List<Site> sites = siteDao.getAll();
            request.setAttribute("sites", sites);
            request.getRequestDispatcher("/WEB-INF/jsp/promocao/cadastrar.jsp")
                    .forward(request, response);
        } else {
            redirectToNotFound(request, response);
        }
    }

    private void redirectToNotFound(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/not-found.jsp")
                .forward(request, response);
    }
}
