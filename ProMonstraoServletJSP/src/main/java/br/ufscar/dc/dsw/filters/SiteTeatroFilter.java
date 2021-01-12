package br.ufscar.dc.dsw.filters;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SiteTeatroFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Erro erros = new Erro();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if (!usuario.getPapel().equals("TEATRO") && !usuario.getPapel().equals("SITE")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [TEATRO] ou [SITE] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            request.getRequestDispatcher("/WEB-INF/jsp/no-auth.jsp")
                    .forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }
}
