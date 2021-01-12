package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.TeatroDAO;
import br.ufscar.dc.dsw.domain.Teatro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Home", urlPatterns = { "" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TeatroDAO dao;

	@Override
	public void init() {
		dao = new TeatroDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Teatro> listaTeatros;
		String ordenar = request.getParameter("ordenar");
		if (ordenar != null) {
			listaTeatros = dao.getAllOrderByCidade();
		} else {
			listaTeatros = dao.getAll();
		}
		request.setAttribute("listaTeatros", listaTeatros);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").include(request, response);
	}
}