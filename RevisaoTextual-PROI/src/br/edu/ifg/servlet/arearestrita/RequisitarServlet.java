package br.edu.ifg.servlet.arearestrita;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/area-restrita/requisitar")
public class RequisitarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pegando valor que foi selecionado na tela
		Double valor = Double.valueOf(request.getParameter("valor"));
		
		// setando na sessão o valor final
		session.setAttribute("valor", valor);
		
		// Redirecionando para a pagina de pagamento
		response.sendRedirect("pagamento.jsp");
	}
	
}
