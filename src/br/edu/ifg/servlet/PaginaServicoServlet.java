package br.edu.ifg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.model.Servico;
import br.edu.ifg.util.Utils;

@WebServlet("/servico")
public class PaginaServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServicoDAO servicoDAO = new ServicoDAO();
		PrintWriter out = resp.getWriter();
		
		// Setando o retorno como utf8
		resp.setCharacterEncoding("UTF-8");
		
		// buscando a lista de servicos
		List<Servico> servicos = servicoDAO.getAll();
		
		// Percorrendo os servicos e montando a grid para cada uma
		servicos.forEach((servico) -> {
			String descricao = servico.getDescricao();
			String caracteristicas = servico.getCaracteristicas();
			
			out.println("<div class=\"col-lg-6\">");
			out.println("	<div class=\"box wow fadeInLeft\" style=\"visibility: visible; animation-name: fadeInLeft;\">");
			out.println("		<div class=\"icon\"><i class=\"fa fa-pencil\"></i></div>");
			out.println("		<h4 class=\"title\">" + descricao + "</h4>");
			out.println("		<p class=\"description\">" + caracteristicas + "</p>");
			out.println("		<p class=\"description\"> Valores: </p>");
			
			// imprimindo os valores
			servico.getServicoValores().forEach((valores) -> {
				out.println("		<p class=\"description\"> R$ " + Utils.formatarValor(valores.getValor()) + " - " + valores.getFormaPagamento().getDescricao() + "</p>");
			});
			
			out.println("	</div>");
			out.println("</div>");
		});
	}
}
