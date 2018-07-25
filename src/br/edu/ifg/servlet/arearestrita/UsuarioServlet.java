package br.edu.ifg.servlet.arearestrita;

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

@WebServlet("/area-restrita/usuario")
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		// Setando retorno como utf8
		response.setContentType("text/html;charset=UTF-8");
		
		// pegando os parametros do formulario
		Integer id = Integer.valueOf(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String confirmacaoSenha = request.getParameter("confirmacao-senha");
		
		// Endereço
		String uf = request.getParameter("uf");
		String localidade = request.getParameter("localidade");
		String cep = request.getParameter("cep");
		String bairro = request.getParameter("bairro");
		String logradouro = request.getParameter("logradouro");
		String numero = request.getParameter("numero");
		
		// Verificando se as senhas são diferentes
		if (!senha.equals(confirmacaoSenha)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("alterar-dados.jsp");
	        dispatcher.include(request, response);
	        
	        // colocando alert para exibir na tela
	        out.print("<script>");
	        out.print("		alert('A confirmação da senha não confere!');");
	        out.print("</script>");
		} else {
			
			// Alterando usuário
			Usuario usuario = new Usuario(id, nome, email, cpf, uf, localidade, cep, bairro, logradouro, numero);
			usuarioDAO.alterar(usuario);
			
			// Alterando a senha do usuario caso ele tenha preenchido
			if (!senha.isEmpty()) {
				usuarioDAO.alterarSenha(usuario);
			}
			
			// setando novo usuario alterado na sessão e redirecionando
			session.setAttribute("userSession", usuario);
			response.sendRedirect("area-cliente.jsp");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("userSession");
		
		// removendo usuário
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluirUsuario(usuario.getId());
		
		// limpando a sessão e redirecionando para o index
		session.setAttribute("userSession", null);
	}
}