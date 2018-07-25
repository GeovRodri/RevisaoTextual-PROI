package br.edu.ifg.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class GeradorPDF {
	
	//Aqui será gerado o Boleto
		
	public void crieBoletos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Metodo que ira criar o boleto a partir das informações necesarias
		response.setContentType("application/pdf");
		
		ContaBancaria contaBancaria = crieUmaContaBancaria();//Cria conta do cedente
		Cedente cedente = crieUmCedente(); //Cria objeto Cedente
		
		Sacado sacado = crieUmSacado(request, response);//Cria quem ira pagar o boleto
		
		Titulo titulo = crieOsDadosDoNovoTitulo(new Titulo(contaBancaria, sacado, cedente), 1);//Dados de input do boleto
		Boleto boleto = crieOsDadosDoNovoBoleto(new Boleto(titulo));//Criação do titulo
		
		File pathBoletos = new File("boletos");//Criado para armazenar o boleto
        if (!pathBoletos.exists()) {
        	pathBoletos.mkdirs();
        }
				
		BoletoViewer boletoViewer = new BoletoViewer(boleto);//Vizualizador do boelto
		boletoViewer.getPdfAsFile((pathBoletos + "/" + UUID.randomUUID().toString() + ".pdf"));
		
		byte[] arquivo = boletoViewer.getPdfAsByteArray();
		response.setContentLength(arquivo.length);
		
		ServletOutputStream ouputStream = response.getOutputStream();//Output do boleto
		ouputStream.write(arquivo, 0, arquivo.length);
		ouputStream.flush();
		ouputStream.close();
	}

	private Boleto crieOsDadosDoNovoBoleto(Boleto boleto) {	
		//Cria informações adicionais que devem estar no boleto
		boleto.setLocalPagamento("Pagï¿½vel preferencialmente na Rede X ou em qualquer Banco atï¿½ o Vencimento.");
		boleto.setInstrucao1("ACEITAR SOMENTE ATï¿½ A DATA DE VENCIMENTO");
		
		return boleto;
	}
	
	
	private Titulo crieOsDadosDoNovoTitulo(Titulo titulo, int numero) {
		//Dados que estarão no titulo do boleto
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
	
	
	private Sacado crieUmSacado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cria o sacado(pessoa que paga o boleto)
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("userSession");
		
		Sacado sacado = new Sacado(usuario.getNome(), usuario.getCpf());//Nome e cpf do usuario

		// Informe o endereï¿½o do sacado.
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
	
	private Cedente crieUmCedente() {
		//Nome e CNPJ do beneficiario do boleto
		return new Cedente("LGPR Revisao Textual", "00.000.208/0001-00");
	}
	
	private ContaBancaria crieUmaContaBancaria(){	
		//Cria informações da conta bancaria que ira receber o dinheiro
		ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
		contaBancaria.setCarteira(new Carteira(30));
		contaBancaria.setAgencia(new Agencia(1234, "1"));
		
		return contaBancaria;
	}
}