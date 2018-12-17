package br.edu.ifg.servlet.arearestritaadmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifg.dao.ServicoDAO;

@WebServlet("/area-restrita-admin/listar-servicos")
public class ListarServicoServlet extends HttpServlet {
	//Servlet para gerenciamento dos serviços
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retornando os servicos
		ServicoDAO servicoDAO = new ServicoDAO();
		servicoDAO.getAll();
		 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Removendo servico
		ServicoDAO servicoDAO = new ServicoDAO();
		servicoDAO.excluirServico((Integer) request.getAttribute("id"));
	}


	}



