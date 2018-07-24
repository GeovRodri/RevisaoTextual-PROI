package br.edu.ifg.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

public class PdfParser {

	public void executa(HttpServletRequest req, HttpServletResponse res) {
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
			String[] palavras = caracteres.split(" ");

			//getting the content of the document
			System.out.println("Contents of the PDF :" + handler.toString());

			//getting metadata of the document
			System.out.println("Metadata of the PDF:");
			String[] metadataNames = metadata.names();

			for(String name : metadataNames) {
				System.out.println(name+ " : " + metadata.get(name));
			}

			List<Servico> services = new ServicoDAO().getAll();
			List<ServicoValor> servicoOrcado = new ArrayList<>();
			for (Servico service : services) {
				ServicoValor servic = new ServicoValor();
				Servico serv = new Servico();
				serv.setCaracteristicas(service.getCaracteristicas());
				serv.setDescricao(service.getDescricao());
				servic.setFormaPagamento(servic.getFormaPagamento());

				String des = service.getDescricao();
				if (des.equalsIgnoreCase("pagina")) {
					servic.setValor((Integer.valueOf(metadata.get("xmpTPg:NPages")) * servic.getValor()));
				}
				if (des.equalsIgnoreCase("lauda")) {
					servic.setValor(((caracteres.length() / 1250) * servic.getValor()));
					System.out.println(caracteres.length() / 1250 * servic.getValor());
				}
				if (des.equalsIgnoreCase("caracter")) {
					servic.setValor((caracteres.length() * servic.getValor()));
					System.out.println((caracteres.length() * servic.getValor()));
				}
				if (des.equalsIgnoreCase("palavra")) {
					servic.setValor((palavras.length * servic.getValor()));
					System.out.println(palavras.length * servic.getValor());
				}
				servicoOrcado.add(servic);
			}

			req.getSession().setAttribute("servicoOrcado", servicoOrcado);
			req.getSession().setAttribute("srvSel", req.getParameter("servico"));
			req.getSession().setAttribute("pgSel", req.getParameter("pagamento")); 
			
			res.sendRedirect("pagamento.jsp");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}