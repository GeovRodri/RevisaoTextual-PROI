package br.edu.ifg.servlet.arearestritaadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.model.Servico;

@WebServlet("/area-restrita-admin/servico")
public class ServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/*@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		ServicoDAO servicoDAO = new ServicoDAO();
		
		// Setando retorno como utf8
		response.setContentType("text/html;charset=UTF-8");
		
		// pegando os parametros do formulario
		Integer id = Integer.valueOf(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		String caracteristicas = request.getParameter("caracteristicas");
		List<Double> valores = request.getParameter("servicoValores");	
			
		// Alterando Servico
		Servico servico = new Servico(id, descricao, caracteristicas,valores);
		
		servicoDAO.alterar(servico);
		
	
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Servico servico = (Servico) session.getAttribute("userSession");
		
		// removendo Servico
		ServicoDAO servicoDAO = new ServicoDAO();
		ServicoDAO.excluirServico(servico.getId());
		
		
	}*/
	
}
	