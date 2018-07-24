package br.edu.ifg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

public class PdfParser {
	
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		  /*String url = "RevisaoTextual-PROI/area-restrita/orcamento.jsp";
		  String arquivos = "C:\\Users\\raula\\Documents\\RevisaoTextual-PROI\\WebContent\\web-app\\testepdf.pdf";
	      FileInputStream inputstream = new FileInputStream(new File(arquivos));*/
	     
	    String pathDoc = System.getProperty("user.home"); 
		res.setContentType("text/html;charset=UTF-8");
		 
        final Part filePart = (Part) req.getPart("file");
        String bookId = req.getParameter("bookId");
 
        InputStream pdfFileBytes = null;
        final PrintWriter writer = res.getWriter();
 
        try {
 
          if (!((ServletRequest) filePart).getContentType().equals("application/pdf"))
            {
                       writer.println("<br/> Invalid File");
                       return null;
            }
           else { 
               System.out.println("Erro");
           }
       
          pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
          
          final byte[] bytes = new byte[pdfFileBytes.available()];
           pdfFileBytes.read(bytes);
            
            
	      ParseContext pcontext = new ParseContext();
	      Metadata metadata = new Metadata();
	      BodyContentHandler handler = new BodyContentHandler();
	      
	      //parsing the document using PDF parser
	      PDFParser pdfparser = new PDFParser(); 
	      pdfparser.parse(inputstream, handler, metadata,pcontext);
	      String Caracteres = handler.toString();
	      String[] palavras = Caracteres.split(" ");

	      
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
					servic.setValor(((Caracteres.length() / 1250) * servic.getValor()));
					System.out.println(Caracteres.length() / 1250 * servic.getValor());
				}
				if (des.equalsIgnoreCase("caracter")) {
					servic.setValor((Caracteres.length() * servic.getValor()));
					System.out.println((Caracteres.length() * servic.getValor()));
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
	      
	      
	      
	      
		return url;
	}
}
