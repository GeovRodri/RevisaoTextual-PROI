package br.edu.ifg.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.enums.CobrancaEnum;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

public class PdfParser {

	public void executa(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		res.setContentType("text/html;charset=UTF-8");

		try {
			final Part filePart = (Part) req.getPart("file");

			InputStream pdfFileBytes = null;
			final PrintWriter writer = res.getWriter();

			if (!filePart.getContentType().equals("application/pdf")) {
				writer.println("<br/> Invalid File");
				return;
			} else { 
				System.out.println("Erro");
			}

			pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data

			ParseContext pcontext = new ParseContext();
			Metadata metadata = new Metadata();
			BodyContentHandler handler = new BodyContentHandler();

			//parsing the document using PDF parser
			PDFParser pdfparser = new PDFParser(); 
			pdfparser.parse(pdfFileBytes, handler, metadata, pcontext);
			String caracteres = handler.toString();
			caracteres = caracteres.replace("\n", "");
			String[] palavras = caracteres.split(" ");

			//getting the content of the document
			System.out.println("Contents of the PDF :" + handler.toString());

			//getting metadata of the document
			System.out.println("Metadata of the PDF:");
			String[] metadataNames = metadata.names();

			for(String name : metadataNames) {
				System.out.println(name+ " : " + metadata.get(name));
			}
			
			ServicoDAO servicoDAO = new ServicoDAO();
			Servico servico = servicoDAO.getServico(1);// buscar apenas um servico de acordo com o id selecionado na tela;

			System.out.println("descricao" + servico.getDescricao());
			
			for (ServicoValor servicoValor : servico.getServicoValores()) {
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_LAUDA) == 0) {
					Double valorLauda = null;
					if(caracteres.length() < 1250) {
						valorLauda = 1 * servicoValor.getValor();
					} else {
					valorLauda = (caracteres.length() / 1250) * servicoValor.getValor();
					}
					System.out.println(valorLauda);
					session.setAttribute("valorPorLauda", valorLauda);
				}
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PAGINA) == 0) {
					Double valor = (Integer.valueOf(metadata.get("xmpTPg:NPages")) * servicoValor.getValor());
					System.out.println(valor);
					
					session.setAttribute("valorPorPagina", valor);
				}
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PALAVRA) == 0) {
					Double valor = (palavras.length * servicoValor.getValor());
					System.out.println(valor);
					
					session.setAttribute("valorPorPalavra", valor);
				}
			}
			
			res.sendRedirect("pagamento.jsp");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}