package br.com.apontamentogeopxserver.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sistema {
	public static String ObterData(){
	
			Calendar c = Calendar.getInstance();
			Date data = c.getTime();
			
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); //Data COmpleta
			return f.format(data);
	    }
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
