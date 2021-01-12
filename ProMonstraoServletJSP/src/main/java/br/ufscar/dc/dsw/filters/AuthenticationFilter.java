package br.ufscar.dc.dsw.filters;

import br.ufscar.dc.dsw.domain.Usuario;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Usuario usuario = (Usuario) httpRequest.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            String action = httpRequest.getPathInfo();
            if (action != null && action.equals("/login")) {
                httpResponse.sendRedirect(httpRequest.getContextPath());
            } else {
                chain.doFilter(request, response);
            }
        }

    }
}
