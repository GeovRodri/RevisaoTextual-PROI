package br.edu.ifg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.sis.internal.converter.StringConverter.Integer;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.model.Servico;

@WebServlet("/area-restrita-admin/cadastrar-servico")
public class AdmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServicoDAO servicoDAO = new ServicoDAO();
		
		
		// pegando os parametros do formulario
		Integer id = Integer.valueOf(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		String caracteristicas = request.getParameter("caracteristica");
		Double vPagina = request.getParameter("valorpagina");	
		Double vLauda = request.getParameter("valorlauda");	
		Double vPalavra = request.getParameter("valorpalavra");	
		List<ServicoValores> lista = ArrayList();
		
		lista.add = (ServicoValores pagina = new ServicoValores(id,id,0,vPagina));
		lista.add = (ServicoValores palavra = new ServicoValores(id,id,1,vPalavra));
		lista.add = (ServicoValores lauda = new ServicoValores(id,id,2,vLauda));

		if () {
		
			Servico servico = new Servico(descricao, caracteristicas, lista);
			servicoDAO.cadastrarServico(servico);

		}else{

		// Alterando Servico

			Servico servico = new Servico(id, descricao, caracteristicas,lista);
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
