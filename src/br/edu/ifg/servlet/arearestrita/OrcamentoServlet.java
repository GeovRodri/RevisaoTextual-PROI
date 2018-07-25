package br.edu.ifg.servlet.arearestrita;
/**
 * Servlet que é chamada pelo formulario em area-cliente, que tem como proposito chamar o metodo da classe que interpreta o arquivo inserido no form e calcular o orçamento.
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.util.PdfParser;

@MultipartConfig
@WebServlet("/area-restrita/orcamento")
public class OrcamentoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2535264930309928221L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdfParser orcamento = new PdfParser();
		orcamento.executa(request, response); //chama o metodo executa, que ira fazer o calculo do orcamento para cada servico e retornar a pag que ira exibir os resultados.
	}
}