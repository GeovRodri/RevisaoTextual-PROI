package br.edu.ifg.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
	
	public static String formatarValor(Double valor) {
		NumberFormat fmt = NumberFormat.getInstance(new Locale("pt", "BR"));
		fmt.setMinimumFractionDigits(2);
		return fmt.format(valor);
	}
}
