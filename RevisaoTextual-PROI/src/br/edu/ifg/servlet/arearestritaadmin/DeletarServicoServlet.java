package br.edu.ifg.servlet.arearestritaadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.dao.ServicoValorDAO;

@WebServlet("/area-restrita-admin/deletar-servico")
public class DeletarServicoServlet extends HttpServlet {
	//Servlet para gerenciamento dos serviços
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServicoDAO servicoDAO = new ServicoDAO();
		ServicoValorDAO servicoValor = new ServicoValorDAO();
		
		Integer id = Integer.valueOf(request.getParameter("servicoId"));
		
		servicoDAO.excluirServico(id);
		servicoValor.removerValorServico(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("deletar-servico.jsp");
		dispatcher.include(request, response);
	}
}

