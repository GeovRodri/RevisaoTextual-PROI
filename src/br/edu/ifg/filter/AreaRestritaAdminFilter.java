package br.edu.ifg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.enums.TipoUsuarioEnum;
import br.edu.ifg.model.Usuario;

@WebFilter("/area-restrita-admin/*")
public class AreaRestritaAdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
		
		// Verificando se o usuario existe na sessão
		Usuario usuario = (Usuario) session.getAttribute("userSession");

		// Verificando se o usuario existe e se ele é admin
		if (usuario != null && usuario.getTipo().compareTo(TipoUsuarioEnum.ADMIN) == 0) {
			chain.doFilter(req, res);
		} else {
			response.sendRedirect("../index.jsp");
		}
	}
}