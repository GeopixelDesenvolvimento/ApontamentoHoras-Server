package br.com.apontamentogeopxserver.model;

import java.text.SimpleDateFormat;
/**
 * Classe responsavel por cirar os atributos do objeto sistema
 * será atraves desta classio que as infromações de data/hora serão coletadas do sistema
 */
import java.util.Calendar;
import java.util.Date;

public class Sistema {
	
	/**
	 * Metodo responsavel por capturar a data do sistema
	 * @return
	 */
	public static String ObterData(){
	
			Calendar c = Calendar.getInstance();
			Date data = c.getTime();
			
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); //Data COmpleta
			return f.format(data);
	    }
	
	/**
	 * Metodo responsavel por coletar a hora do sistema
	 * @return
	 */
	public static String ObterHora(){
	       //pega data para converter em horas
	       Date data = new Date();
	       //cria o formatador
	       SimpleDateFormat hformatador = new SimpleDateFormat("HH:mm:ss");
	       // cria a string
	       String Hora = hformatador.format(data);
	       //retorna o pedido 
	       return Hora;
	      // System.out.println(Hora);
	    }

}
