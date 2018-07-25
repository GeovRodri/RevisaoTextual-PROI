package br.edu.ifg.util;
/**
 * Classe utilizada para extrair os metadados do pdf fazer o calculo para cada tipo de servi�o e retornar a pag de or�amento.
 *
 */
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
	
	/**
     * O metodo ir� receber a requisi��o e a resposta e ser� respons�vel por fazer os calculos do or�amento  e retornar a pag.
     *
     * @param req requisi��o
     * @param res resposta
     * @throws RuntimeException 
     */
	public void executa(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		res.setContentType("text/html;charset=UTF-8");

		try {
			final Part filePart = (Part) req.getPart("file");//Pegando o arquivo que foi feito upload atrav�s da pag area-cliente.jsp

			InputStream pdfFileBytes = null;
			final PrintWriter writer = res.getWriter();
			//No if abaixo verifica o tipo do arquivo e se for diferente de pdf, o arquivo � inv�lido
			if (!filePart.getContentType().equals("application/pdf")) {
				writer.println("<br/> Invalid File");
				return;
			} else { 
				System.out.println("Erro");
			}

			pdfFileBytes = filePart.getInputStream();  // para obter o corpo da requisi��o em dados bin�rios

			ParseContext pcontext = new ParseContext();
			Metadata metadata = new Metadata();
			BodyContentHandler handler = new BodyContentHandler();

			//Fazendo o parsing do documento com PDF parser
			PDFParser pdfparser = new PDFParser(); 
			pdfparser.parse(pdfFileBytes, handler, metadata, pcontext);
			String caracteres = handler.toString();//colocando o conte�do do documento em uma vari�vel.
			caracteres = caracteres.replace("\n", ""); //colocando na vari�vel os caracteres do documento, ignorando as quebras de linhas
			String[] palavras = caracteres.split(" ");//Colocando na vari�vel as palavras do documento, quebrando nos espa�os em branco

			//Mostrando o conteudo do pdf
			System.out.println("Contents of the PDF :" + handler.toString());

			//pegando os metadados do arquivo
			System.out.println("Metadata of the PDF:");
			String[] metadataNames = metadata.names();
			
			//Mostrando no console os metadados do arquivo
			for(String name : metadataNames) {
				System.out.println(name+ " : " + metadata.get(name));
			}
			
			ServicoDAO servicoDAO = new ServicoDAO();//Instanciando objeto DAO para buscar as informa��es do servi�o
			Servico servico = servicoDAO.getServico(1);// buscando o servico de acordo com o id selecionado na tela;

			for (ServicoValor servicoValor : servico.getServicoValores()) {
				//No if abaixo ser� verificado se a forma escolhida for por lauda ser� feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_LAUDA) == 0) {
					Double valorLauda = null;
					//O if abaixo � para o caso de a quantidade de caracteres for menor que 1250, dessa forma ser� considerada no m�nimo uma lauda.
					if(caracteres.length() < 1250) {
						valorLauda = 1 * servicoValor.getValor();
					} else {
					valorLauda = (caracteres.length() / 1250) * servicoValor.getValor();//Ir� setar para o valorLauda a quantidade de caracteres dividido por 1250, vezes o valor do servico.
					}
					System.out.println(valorLauda);
					session.setAttribute("valorPorLauda", valorLauda); //Setando o valor por lauda na sess�o
				}
				//No if abaixo ser� verificado se a forma escolhida for por pagina ser� feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PAGINA) == 0) {
					Double valor = (Integer.valueOf(metadata.get("xmpTPg:NPages")) * servicoValor.getValor());//Ir� setar para o valor, a quantidade de p�ginas, vezes o valor do servico.
					System.out.println(valor);
					
					session.setAttribute("valorPorPagina", valor);//Setando o valor por pagina na sess�o
				}
				//No if abaixo ser� verificado se a forma escolhida for por palavra ser� feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PALAVRA) == 0) {
					Double valor = (palavras.length * servicoValor.getValor());//Ir� setar para o valor, a quantidade de palavras, vezes o valor do servico.
					System.out.println(valor);
					
					session.setAttribute("valorPorPalavra", valor);//Setando o valor por palavra na sess�o
				}
			}
			
			res.sendRedirect("orcamento.jsp");//redirecionando para a p�gina de or�amento j� com os valores calculados para cada servi�o
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}