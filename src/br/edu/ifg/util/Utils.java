package br.edu.ifg.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
	
	//Metodo usado para que o valor no input seja formatado adequadamente
	public static String formatarValor(Double valor) {
		NumberFormat fmt = NumberFormat.getInstance(new Locale("pt", "BR"));//Localização
		fmt.setMinimumFractionDigits(2);//Numeros de casas apos a virgula
		return fmt.format(valor);// Retorna formatado
	}
}
