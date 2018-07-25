package br.edu.ifg.servlet.arearestritaadmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.model.Servico;

@WebServlet("/area-restrita-admin/listar-servicos")
public class ListarServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected List<Servico> doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retornando uma lista de servicos
		ServicoDAO servicoDAO = new ServicoDAO();
		List<Servico> servicos = servicoDAO.getAll();
		return servicos; 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Removendo servico
		ServicoDAO servicoDAO = new ServicoDAO();
		servicoDAO.excluirServico(request.getAttribute("id"));
	}


	}



