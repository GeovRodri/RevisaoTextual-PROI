package br.edu.ifg.util;
/**
 * Classe utilizada para extrair os metadados do pdf fazer o calculo para cada tipo de serviço e retornar a pag de orçamento.
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
     * O metodo irá receber a requisição e a resposta e será responsável por fazer os calculos do orçamento  e retornar a pag.
     *
     * @param req requisição
     * @param res resposta
     * @throws RuntimeException 
     */
	public void executa(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		res.setContentType("text/html;charset=UTF-8");

		try {
			final Part filePart = (Part) req.getPart("file");//Pegando o arquivo que foi feito upload através da pag area-cliente.jsp

			InputStream pdfFileBytes = null;
			final PrintWriter writer = res.getWriter();
			//No if abaixo verifica o tipo do arquivo e se for diferente de pdf, o arquivo é inválido
			if (!filePart.getContentType().equals("application/pdf")) {
				writer.println("<br/> Invalid File");
				return;
			} else { 
				System.out.println("Erro");
			}

			pdfFileBytes = filePart.getInputStream();  // para obter o corpo da requisição em dados binários

			ParseContext pcontext = new ParseContext();
			Metadata metadata = new Metadata();
			BodyContentHandler handler = new BodyContentHandler();

			//Fazendo o parsing do documento com PDF parser
			PDFParser pdfparser = new PDFParser(); 
			pdfparser.parse(pdfFileBytes, handler, metadata, pcontext);
			String caracteres = handler.toString();//colocando o conteúdo do documento em uma variável.
			caracteres = caracteres.replace("\n", ""); //colocando na variável os caracteres do documento, ignorando as quebras de linhas
			String[] palavras = caracteres.split(" ");//Colocando na variável as palavras do documento, quebrando nos espaços em branco

			//Mostrando o conteudo do pdf
			System.out.println("Contents of the PDF :" + handler.toString());

			//pegando os metadados do arquivo
			System.out.println("Metadata of the PDF:");
			String[] metadataNames = metadata.names();
			
			//Mostrando no console os metadados do arquivo
			for(String name : metadataNames) {
				System.out.println(name+ " : " + metadata.get(name));
			}
			
			ServicoDAO servicoDAO = new ServicoDAO();//Instanciando objeto DAO para buscar as informações do serviço
			Servico servico = servicoDAO.getServico(1);// buscando o servico de acordo com o id selecionado na tela;

			for (ServicoValor servicoValor : servico.getServicoValores()) {
				//No if abaixo será verificado se a forma escolhida for por lauda será feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_LAUDA) == 0) {
					Double valorLauda = null;
					//O if abaixo é para o caso de a quantidade de caracteres for menor que 1250, dessa forma será considerada no mínimo uma lauda.
					if(caracteres.length() < 1250) {
						valorLauda = 1 * servicoValor.getValor();
					} else {
					valorLauda = (caracteres.length() / 1250) * servicoValor.getValor();//Irá setar para o valorLauda a quantidade de caracteres dividido por 1250, vezes o valor do servico.
					}
					System.out.println(valorLauda);
					session.setAttribute("valorPorLauda", valorLauda); //Setando o valor por lauda na sessão
				}
				//No if abaixo será verificado se a forma escolhida for por pagina será feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PAGINA) == 0) {
					Double valor = (Integer.valueOf(metadata.get("xmpTPg:NPages")) * servicoValor.getValor());//Irá setar para o valor, a quantidade de páginas, vezes o valor do servico.
					System.out.println(valor);
					
					session.setAttribute("valorPorPagina", valor);//Setando o valor por pagina na sessão
				}
				//No if abaixo será verificado se a forma escolhida for por palavra será feito o calculo.
				if (servicoValor.getFormaPagamento().compareTo(CobrancaEnum.POR_PALAVRA) == 0) {
					Double valor = (palavras.length * servicoValor.getValor());//Irá setar para o valor, a quantidade de palavras, vezes o valor do servico.
					System.out.println(valor);
					
					session.setAttribute("valorPorPalavra", valor);//Setando o valor por palavra na sessão
				}
			}
			
			res.sendRedirect("orcamento.jsp");//redirecionando para a página de orçamento já com os valores calculados para cada serviço
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}