package br.edu.ifg.servlet.arearestritaadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.dao.ServicoValorDAO;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

@WebServlet("/area-restrita-admin/alterar-servico")
public class AlterarServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
		// pegando os parametros do formulario
		
		Integer id = Integer.valueOf(request.getParameter("servicoId"));
		String descricao = request.getParameter("descricao");
		String caracteristicas = request.getParameter("caracteristica");
		
		Double vPagina =Double.valueOf(request.getParameter("valorPagina"));	
		Double vLauda = Double.valueOf(request.getParameter("valorLauda"));	
		Double vPalavra = Double.valueOf(request.getParameter("valorPalavra"));	
	
		
		Servico servico = criaServico(id,descricao,caracteristicas);
		List<ServicoValor> valor = criarValor(servico.getId(),vPagina, vLauda, vPalavra);
		servico.setServicoValores(valor);
		
		ServicoDAO servicoDAO = new ServicoDAO();
		servicoDAO.alterarServico(servico);

		
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("servico.jsp");
		dispatcher.include(request, response);

		
	}
	public Servico criaServico(Integer id, String descricao,String caracteristicas) {
		
		Servico servico = new Servico();
		servico.setId(id);
		servico.setDescricao(descricao);
		servico.setCaracteristicas(caracteristicas);
		
		return servico;
		
	}
	public List<ServicoValor> criarValor(Integer id,Double vPagina, Double vLauda, Double vPalavra) {
		
		List<ServicoValor> lista = new ArrayList<ServicoValor>(); 

		ServicoValor pagina = new ServicoValor(id,"0",vPagina);
		ServicoValor palavra = new ServicoValor(id,"1",vLauda);
		ServicoValor lauda = new ServicoValor(id,"2",vPalavra);
				
		ServicoValorDAO servicoValorDAO = new ServicoValorDAO();
		servicoValorDAO.alterarServicoValor(pagina);
		servicoValorDAO.alterarServicoValor(palavra);
		servicoValorDAO.alterarServicoValor(lauda);

		lista.add(pagina);
		lista.add(palavra);
		lista.add(lauda);
		return lista;
	}
	

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServicoDAO servicoDAO = new ServicoDAO();
		ServicoValorDAO valor = new ServicoValorDAO();
		Integer id = Integer.valueOf(request.getParameter("servicoId"));

		servicoDAO.excluirServico(id);
		valor.removerValorServico(id);
		
	
	}
}
