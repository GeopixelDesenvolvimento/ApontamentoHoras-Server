package br.com.apontamentogeopxserver.model;

/**
 * Classe responsável por cirar os atibutos do objeto Projeto 
 * @author marcelly.paula
 *
 */

public class Projeto {
	private int id_proj;
	private String Projeto;
	
	public int getId_proj() {
		return id_proj;
	}
	public void setId_proj(int id_projeto) {
		this.id_proj = id_projeto;
	}
	public String getProjeto() {
		return Projeto;
	}
	public void setProjeto(String projeto) {
		this.Projeto = projeto;
	}

}