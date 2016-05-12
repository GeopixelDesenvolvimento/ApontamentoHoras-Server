package br.com.apontamentogeopxserver.model;

/**
 * Classe responsavel por manter os atributos do objeto Atividade
 * @author marcelly.paula
 * 
 */

public class Atividade {
	private int Id_Atividade;
	private String Atividade;
	
	public int getId_Atividade() {
		return Id_Atividade;
	}
	public void setId_Atividade(int id_Atividade) {
		this.Id_Atividade = id_Atividade;
	}
	public String getAtividade() {
		return Atividade;
	}
	public void setAtividade(String atividade) {
		this.Atividade = atividade;
	}
}
