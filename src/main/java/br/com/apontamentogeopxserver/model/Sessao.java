package br.com.apontamentogeopxserver.model;
/**
 * Classe responsavel por cirar os atributos do objeto Sessao
 * @author marcelly.paula
 *
 */
public class Sessao {
	private int Id_Sessao;
	private String Data_Inicio;
	private String DataSystem_Inicio;
	private String Hora_Inicio;
	private String HoraSystem_Inicio;
	private int Id_Atividade;
	private int Id_Projeto;
	private String User_Name;
	private String Sub_atividade;
	private String Status;
	private String Data_Fim;
	private String DataSystem_Fim;
	private String Hora_Fim;
	private String HoraSystem_Fim;
	
	
		
	public  Sessao() {
				
	}



	public int getId_Sessao() {
		return Id_Sessao;
	}



	public void setId_Sessao(int Id_Sessao) {
		this.Id_Sessao = Id_Sessao;
	}



	public String getData_Inicio() {
		return Data_Inicio;
	}



	public void setData_Inicio(String data_Inicio) {
		Data_Inicio = data_Inicio;
	}



	public String getDataSystem_Inicio() {
		return DataSystem_Inicio;
	}



	public void setDataSystem_Inicio(String dataSystem_Inicio) {
		DataSystem_Inicio = dataSystem_Inicio;
	}



	public String getHora_Inicio() {
		return Hora_Inicio;
	}



	public void setHora_Inicio(String hora_Inicio) {
		Hora_Inicio = hora_Inicio;
	}



	public String getHoraSystem_Inicio() {
		return HoraSystem_Inicio;
	}



	public void setHoraSystem_Inicio(String horaSystem_Inicio) {
		HoraSystem_Inicio = horaSystem_Inicio;
	}



	public int getId_Atividade() {
		return Id_Atividade;
	}



	public void setId_Atividade(int id_Atividade) {
		Id_Atividade = id_Atividade;
	}



	public int getId_Projeto() {
		return Id_Projeto;
	}



	public void setId_Projeto(int id_Projeto) {
		Id_Projeto = id_Projeto;
	}



	public String getUser_Name() {
		return User_Name;
	}



	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}



	public String getSub_atividade() {
		return Sub_atividade;
	}



	public void setSub_atividade(String sub_atividade) {
		Sub_atividade = sub_atividade;
	}



	public String getStatus() {
		return Status;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public String getData_Fim() {
		return Data_Fim;
	}



	public void setData_Fim(String data_Fim) {
		Data_Fim = data_Fim;
	}



	public String getDataSystem_Fim() {
		return DataSystem_Fim;
	}



	public void setDataSystem_Fim(String dataSystem_Fim) {
		DataSystem_Fim = dataSystem_Fim;
	}



	public String getHora_Fim() {
		return Hora_Fim;
	}



	public void setHora_Fim(String hora_Fim) {
		Hora_Fim = hora_Fim;
	}



	public String getHoraSystem_Fim() {
		return HoraSystem_Fim;
	}



	public void setHoraSystem_Fim(String horaSystem_Fim) {
		HoraSystem_Fim = horaSystem_Fim;
	}


	


}
