package br.edu.ifg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.util.PdfParser;

@MultipartConfig
@WebServlet("/area-restrita/OrcamentoServlet")
public class OrcamentoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2535264930309928221L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdfParser orcamento = new PdfParser();
		/*try {*/
			orcamento.executa(request, response);
			
		/*} catch (Exception e) {
			System.out.println("Arquivo inválido: " + e.getMessage());
		}*/
		
	}
}