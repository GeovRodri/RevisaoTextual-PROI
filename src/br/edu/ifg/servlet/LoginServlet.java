package br.edu.ifg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.dao.UsuarioDAO;
import br.edu.ifg.enums.TipoUsuarioEnum;
import br.edu.ifg.model.Servico;
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
		String tipo = request.getParameter("tipo");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		// Realizando a busca de usuario por email
		Usuario usuario = usuarioDAO.buscarPorEmail(email);
		
		if (usuario != null) {
			// comparando as senhas
			if (usuario.getSenha().equals(senha)) {
				if (tipo != null && !tipo.isEmpty()) {
					
					if (usuario.getTipo().compareTo(TipoUsuarioEnum.lookup(tipo)) != 0) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
						dispatcher.include(request, response);
				        
				        // colocando alert para exibir na tela
				        out.print("<script>");
				        out.print("		alert('Esse usuário não é administrador!');");
				        out.print("</script>");
					}
				}
				
				// buscando a lista de servicos para setar na sessão do usuario. Vai ser utilizado na hora de realizar o orçamento
				ServicoDAO servicoDAO = new ServicoDAO();
				List<Servico> servicos = servicoDAO.getAll();
				session.setAttribute("servicos", servicos);
				
				session.setAttribute("userSession", usuario);
				response.sendRedirect("area-restrita/area-cliente.jsp");
			} else {
				RequestDispatcher dispatcher = null;
				
				// decidindo qual pagina será retornada de acordo com o tipo de login
				if (tipo.isEmpty()) {
					dispatcher = request.getRequestDispatcher("index.jsp");
				} else {
					dispatcher = request.getRequestDispatcher("admin.jsp");
				}
				
		        dispatcher.include(request, response);
		        
		        // colocando alert para exibir na tela
		        out.print("<script>");
		        out.print("		alert('Senha incorreta!');");
		        out.print("</script>");
			}
		} else {
			RequestDispatcher dispatcher = null;
			
			// decidindo qual pagina será retornada de acordo com o tipo de login
			if (tipo.isEmpty()) {
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("admin.jsp");
			}
			
	        dispatcher.include(request, response);
	        
	        // colocando alert para exibir na tela
	        out.print("<script>");
	        out.print("		alert('Usuario inexistente!');");
	        out.print("</script>");
		}
		
	}
}
