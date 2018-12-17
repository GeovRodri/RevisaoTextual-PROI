package br.edu.ifg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import br.edu.ifg.dao.ServicoDAO;
import br.edu.ifg.enums.CobrancaEnum;
import br.edu.ifg.model.Servico;
import br.edu.ifg.model.ServicoValor;

/**
 * Classe utilizada para extrair os metadados do pdf fazer o calculo para cada tipo de servi�o e retornar a pag de or�amento.
 *
 */
public class PdfParser {
	
	/**
     * O metodo ir� receber a requisi��o e a resposta e sera responsavel por fazer os calculos do or�amento  e retornar a pag.
     *
     * @param req requisi�ao
     * @param res resposta
     * @throws RuntimeException 
     */
	public void executa(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		res.setContentType("text/html;charset=UTF-8");
		
		Integer servicoId = Integer.valueOf(req.getParameter("servicoId"));

		try {
			final Part filePart = (Part) req.getPart("file"); //Pegando o arquivo que foi feito upload atraves da pag area-cliente.jsp

			InputStream pdfFileBytes = null;
			final PrintWriter writer = res.getWriter();
			
			//No if abaixo verifica o tipo do arquivo e se for diferente de pdf, o arquivo � invalido
			if (!filePart.getContentType().equals("application/pdf")) {
				writer.println("<br/> Invalid File");
				return;
			} else { 
				System.out.println("Erro");
			}

			pdfFileBytes = filePart.getInputStream();  // para obter o corpo da requisi��o em dados bin�rios
			
			// Criando backup do arquivo para criar o pdf
			InputStream newPdfFileBytes = filePart.getInputStream();

			ParseContext pcontext = new ParseContext();
			Metadata metadata = new Metadata();
			BodyContentHandler handler = new BodyContentHandler();

			//Fazendo o parsing do documento com PDF parser
			PDFParser pdfparser = new PDFParser(); 
			pdfparser.parse(pdfFileBytes, handler, metadata, pcontext);
			String caracteres = handler.toString();//colocando o conteudo do documento em uma variavel.
			caracteres = caracteres.replace("\n", ""); //colocando na variavel os caracteres do documento, ignorando as quebras de linhas
			String[] palavras = caracteres.split(" ");//Colocando na variavel as palavras do documento, quebrando nos espa�os em branco

			//Mostrando o conteudo do pdf
			System.out.println("Contents of the PDF :" + handler.toString());

			//pegando os metadados do arquivo
			System.out.println("Metadata of the PDF:");
			String[] metadataNames = metadata.names();
			
			//Mostrando no console os metadados do arquivo
			for(String name : metadataNames) {
				System.out.println(name+ " : " + metadata.get(name));
			}
			
			ServicoDAO servicoDAO = new ServicoDAO(); //Instanciando objeto DAO para buscar as informa�oes do servi�o
			Servico servico = servicoDAO.getServico(servicoId); // buscando o servico de acordo com o id selecionado na tela;

			for (ServicoValor servicoValor : servico.getServicoValores()) {
				
				//No if abaixo sera verificado se a forma escolhida for por lauda ser� feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_LAUDA) == 0) {
					Double valorLauda = null;
					//O if abaixo � para o caso de a quantidade de caracteres for menor que 1250, dessa forma sera considerada no m�nimo uma lauda.
					if(caracteres.length() < 1250) {
						valorLauda = 1 * servicoValor.getValor();
					} else {
					valorLauda = (caracteres.length() / 1250) * servicoValor.getValor();//Ira setar para o valorLauda a quantidade de caracteres dividido por 1250, vezes o valor do servico.
					}
					System.out.println(valorLauda);
					session.setAttribute("valorPorLauda", valorLauda); //Setando o valor por lauda na sessao
				}
				
				//No if abaixo sera verificado se a forma escolhida for por pagina sera feito o calculo.
				else if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PAGINA) == 0) {
					Double valor = (Integer.valueOf(metadata.get("xmpTPg:NPages")) * servicoValor.getValor());//Ira setar para o valor, a quantidade de paginas, vezes o valor do servico.
					System.out.println(valor);
					
					session.setAttribute("valorPorPagina", valor);//Setando o valor por pagina na sessao
				}
				
				//No if abaixo sera verificado se a forma escolhida for por palavra sera feito o calculo.
				else if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PALAVRA) == 0) {
					Double valor = (palavras.length * servicoValor.getValor());//Ira setar para o valor, a quantidade de palavras, vezes o valor do servico.
					System.out.println(valor);
					
					session.setAttribute("valorPorPalavra", valor);//Setando o valor por palavra na sessao
				}
			}
			
			// Verificando se o path temp existe, se não existir criar para salvar os pdfs
			File path = new File("temp");
            if (!path.exists()) {
                path.mkdirs();
            }
			
			// Gerando file na pasta /temp o arquivo com uuid como nome para não acontecer de ter rapetido
			File arquivoPdf = new File(path + "/" + UUID.randomUUID().toString() + ".pdf");
			
			// Salvando no arquivo
			System.out.println(arquivoPdf.getAbsolutePath());
			OutputStream outputStream = new FileOutputStream(arquivoPdf);
		    IOUtils.copy(newPdfFileBytes, outputStream);
		    
		    outputStream.close();
		    pdfFileBytes.close();
		    newPdfFileBytes.close();
			res.sendRedirect("orcamento.jsp");//redirecionando para a pagina de or�amento jaS com os valores calculados para cada servi�o
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}