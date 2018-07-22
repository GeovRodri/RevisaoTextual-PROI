package br.edu.ifg.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOExceptionWithCause;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;

import br.edu.ifg.util.FileBean;
import br.edu.ifg.util.PdfParser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/area-restrita/OrcamentoServlet")
public class OrcamentoServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		 BodyContentHandler handler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\raula\\Documents\\RevisaoTextual-PROI\\WebContent\\web-app"));
	      ParseContext pcontext = new ParseContext();
		
	    PDFParser pdfparser = new PDFParser(null);
		
		pdfparser.parse(); //(inputstream, handler, metadata,pcontext);
		
		 //getting the content of the document
	      System.out.println("Contents of the PDF :" + handler.toString());
	      
	      //getting metadata of the document
	      System.out.println("Metadata of the PDF:");
	      String[] metadataNames = metadata.names();
	      
	      for(String name : metadataNames) {
	         System.out.println(name+ " : " + metadata.get(name));
	      }
		
	}
	
}