package br.edu.ifg.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;


import br.edu.ifg.model.Usuario;

public class GeradorPDF extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException {
		
		
		new GeradorPDF().exemplo();
	}

	final void exemplo() throws IOException {

		List<Boleto> boletos = crieBoletos(1);

		String template = "MeuTemplate.pdf";
		byte[] boletosInOnePDF = BoletoViewer.groupInOnePdfWithTemplate(boletos, new File(template));

		File arquivoPdf = new File("boletos.pdf");
		
		//Colocando conteúdo no arquivo
		FileUtils.writeByteArrayToFile(arquivoPdf, boletosInOnePDF); 

		// Agora veja o arquivo gerado na tela.
		mostreBoletoNaTela(arquivoPdf);
		
	}

	List<Boleto> crieBoletos(int quantidade) {
		
		ContaBancaria contaBancaria = crieUmaContaBancaria();
		
		Cedente cedente = crieUmCedente(); 
		
		Sacado sacado = null;
		try {
			sacado = crieUmSacado(null, null);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Boleto> boletos = new ArrayList<Boleto>(quantidade);
		
		for (int numero = 1; numero <= quantidade; numero++) {
		
			Titulo titulo = crieOsDadosDoNovoTitulo(new Titulo(contaBancaria,sacado,cedente), numero);
			Boleto boleto = crieOsDadosDoNovoBoleto(new Boleto(titulo));
			
			boletos.add(boleto);
		}
		return boletos;
	}

	final Boleto crieOsDadosDoNovoBoleto(Boleto boleto) {
		
		boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em qualquer Banco até o Vencimento.");
		boleto.setInstrucao1("ACEITAR SOMENTE ATÉ A DATA DE VENCIMENTO");
		
		return boleto;
	}
	
	
	final Titulo crieOsDadosDoNovoTitulo(Titulo titulo, int numero) {
		titulo.setNumeroDoDocumento("123456"+numero);
		titulo.setNossoNumero(String.format("99345678912", numero));
		titulo.setDigitoDoNossoNumero("5");
		titulo.setValor(BigDecimal.valueOf(10 + numero));
		titulo.setDataDoDocumento(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, numero);
		titulo.setDataDoVencimento(cal.getTime());		
		titulo.setTipoDeDocumento(TipoDeTitulo.FAT_FATURA);
		titulo.setAceite(Aceite.A);
		return titulo;
	}
	
	
	final Sacado crieUmSacado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("userSession");
		
		Sacado sacado = new Sacado(usuario.getNome(), usuario.getCpf());

		// Informe o endereço do sacado.
		Endereco enderecoSac = new Endereco();
		enderecoSac.setUF(UnidadeFederativa.GO);
		enderecoSac.setLocalidade(usuario.getLocalidade());
		enderecoSac.setCep(new CEP(usuario.getCep()));
		enderecoSac.setBairro(usuario.getBairro());
		enderecoSac.setLogradouro(usuario.getLogradouro());
		enderecoSac.setNumero(usuario.getNumero());
		sacado.addEndereco(enderecoSac);
		
		return sacado;
	}
	
	final Cedente crieUmCedente() {
		
		return new Cedente("LGPR Revisao Textual", "00.000.208/0001-00");
	}
	
	final ContaBancaria crieUmaContaBancaria(){
		
		ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
		contaBancaria.setCarteira(new Carteira(30));
		contaBancaria.setAgencia(new Agencia(1234, "1"));
		
		return contaBancaria;
	}

	final void mostreBoletoNaTela(File arquivoBoleto) {
		
		try {
		     java.awt.Desktop.getDesktop().open(arquivoBoleto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}