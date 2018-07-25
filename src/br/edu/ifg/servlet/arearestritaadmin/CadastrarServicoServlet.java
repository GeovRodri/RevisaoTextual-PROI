package br.edu.ifg.servlet.arearestritaadmin;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/area-restrita-admin/cadastrar-servico")
public class CadastrarServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cadastrando novo servico
		ServicoDAO servicoDAO = new ServicoDAO();
		ServicoValorDAO servicoValorDAO = new ServicoValorDAO(); 
		PrintWriter out = response.getWriter();
		
		// pegando os parametros do formulario
		Integer id = Integer.valueOf(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		String caracteristicas = request.getParameter("caracteristica");
		Double vPagina =Double.valueOf(request.getParameter("valorpagina"));	
		Double vLauda = Double.valueOf(request.getParameter("valorlauda"));	
		Double vPalavra = Double.valueOf(request.getParameter("valorpalavra"));	
		
		List<ServicoValor> lista = new ArrayList<ServicoValor>(); 
		
		if (request.getParameter("id").equals(null)) {
		//Cadastrando Servico
			
			Servico servico = new Servico();			
			servico.setDescricao(descricao);
			servico.setCaracteristicas(caracteristicas);
			servicoDAO.salvarServico(servico);
			
			
			ServicoValor pagina = new ServicoValor(servico.getId(),"0",vPagina);
			ServicoValor palavra = new ServicoValor(servico.getId(),"1",vLauda);
			ServicoValor lauda = new ServicoValor(servico.getId(),"2",vPalavra);
			
			lista.add(pagina);
			lista.add(palavra);
			lista.add(lauda);
			servico.setServicoValores(lista);
			
			servicoValorDAO.adicionaServicoValor(pagina);
			servicoValorDAO.adicionaServicoValor(palavra);
			servicoValorDAO.adicionaServicoValor(lauda);
			servicoDAO.alterarServico(servico);

		}else{

		// Alterando Servico
			servicoValorDAO.removerValorServico(id);
						
			Servico servico = new Servico(id,descricao,caracteristicas);
			
			ServicoValor pagina = new ServicoValor(id,"0",vPagina);
			ServicoValor palavra = new ServicoValor(id,"1",vLauda);
			ServicoValor lauda = new ServicoValor(id,"2",vPalavra);
			
			lista.add(pagina); 
			lista.add(palavra);
			lista.add(lauda);
			servico.setServicoValores(lista);			
			
			servicoDAO.alterarServico(servico);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-servico.jsp");
			dispatcher.include(request, response);

			// colocando os valores para exibir na tela
			out.print("<script>");
			out.print("$('#descricao').val(descricao);");
			out.print("$('#caracteristica').val(caracteristica);");
			out.print("$('#valorpagina').val(vPagina);");
			out.print("$('#valorlauda').val(vLauda);");
			out.print("$('#valorpalavra').val(vPalavra);");
			out.print("</script>");
		}
	}
}
