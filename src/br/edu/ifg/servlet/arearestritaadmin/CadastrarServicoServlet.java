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
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

@WebServlet("/area-restrita-admin/cadastrar-servico")
public class CadastrarServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// pegando os parametros do formulario
		String descricao = request.getParameter("descricao");
		String caracteristicas = request.getParameter("caracteristica");
		Double vPagina = Double.valueOf(request.getParameter("valorPagina"));	
		Double vLauda = Double.valueOf(request.getParameter("valorLauda"));	
		Double vPalavra = Double.valueOf(request.getParameter("valorPalavra"));	
	
		criaServico(descricao,caracteristicas);
		
		
		criarValor(vPagina, vLauda, vPalavra);		
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("listar-servico.jsp");
		dispatcher.include(request, response);

		
	}
	public void criaServico(String descricao,String caracteristicas) {

		ServicoDAO servicoDAO = new ServicoDAO();
		
		Servico servico = new Servico();
		servico.setDescricao(descricao);
		servico.setCaracteristicas(caracteristicas);
		
		servicoDAO.adicionaServico(servico);		
	}
	public void criarValor(Double vPagina, Double vLauda, Double vPalavra) {
		
		ServicoValor pagina = new ServicoValor("0",vPagina);
		ServicoValor palavra = new ServicoValor("1",vPalavra);
		ServicoValor lauda = new ServicoValor("2",vLauda);
				
		ServicoValorDAO servicoValorDAO = new ServicoValorDAO();
		servicoValorDAO.adicionaServicoValor(pagina);
		servicoValorDAO.adicionaServicoValor(palavra);
		servicoValorDAO.adicionaServicoValor(lauda);

	}
}
	