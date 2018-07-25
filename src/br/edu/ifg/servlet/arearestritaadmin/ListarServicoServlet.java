package br.edu.ifg.servlet.arearestritaadmin;

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

@WebServlet("/area-restrita-admin/listar-servicos")
public class ListarServicoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected Servico doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicoDAO servicoDAO = new ServicoDAO();
		List<Servico> servicos = servicoDAO.getAll();
		return sevicos; 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServicoDAO servicoDAO = new ServicoDAO();
		servicoDAO.recoverServico(getParamenter("id"));
	}
}