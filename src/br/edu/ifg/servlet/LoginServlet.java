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

import br.edu.ifg.dao.UsuarioDAO;
import br.edu.ifg.model.Usuario;


@WebServlet("/login")
public class LoginServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		// Setando retorno como utf8
		response.setContentType("text/html;charset=UTF-8");

		// pegando os parametros da requisição de login
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		// Realizando a busca de usuario por email
		Usuario usuario = usuarioDAO.buscarPorEmail(email);
		
		if (usuario != null) {
			// comparando as senhas
			if (usuario.getSenha().equals(senha)) {
				
				session.setAttribute("userSession", usuario);
				response.sendRedirect("area-restrita/area-cliente.jsp");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		        dispatcher.include(request, response);
		        
		        // colocando alert para exibir na tela
		        out.print("<script>");
		        out.print("		alert('Senha incorreta!');");
		        out.print("</script>");
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.include(request, response);
	        
	        // colocando alert para exibir na tela
	        out.print("<script>");
	        out.print("		alert('Usuario inexistente!');");
	        out.print("</script>");
		}
		
	}
}
