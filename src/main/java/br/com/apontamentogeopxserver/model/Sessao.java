package br.com.apontamentogeopxserver.model;
/**
 * Classe responsavel por cirar os atributos do objeto Sessao
 * @author marcelly.paula
 *
 */
public class Sessao {
	private int id_sessao;
	private String Status;
	private String HoraSys_Inicio;
	private String HoraSys_Fim;
	private String DataSys_Inicio;
	private String DataSys_Fim;
	private String Data_Inicio;
	private String Data_Fim;
	private String Hota_Inicio;
	private String Hora_Fim;
	private String Sub_atividade;
	private String User_Name;
	private int Id_Projeto;
	private int Id_Atividade;
	
	
		
	public  Sessao() {
				
	}


	public int getId_Sessao() {
		return id_sessao;
	}


	public void setId_sessao(int id_sessao) {
		this.id_sessao = id_sessao;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public String getHoraSys_Inicio() {
		return HoraSys_Inicio;
	}


	public void setHoraSys_Inicio(String horaSys_Inicio) {
		HoraSys_Inicio = horaSys_Inicio;
	}


	public String getHoraSys_Fim() {
		return HoraSys_Fim;
	}


	public void setHoraSys_Fim(String horaSys_Fim) {
		HoraSys_Fim = horaSys_Fim;
	}


	public String getDataSys_Inicio() {
		return DataSys_Inicio;
	}


	public void setDataSys_Inicio(String dataSys_Inicio) {
		DataSys_Inicio = dataSys_Inicio;
	}


	public String getDataSys_Fim() {
		return DataSys_Fim;
	}


	public void setDataSys_Fim(String dataSys_Fim) {
		DataSys_Fim = dataSys_Fim;
	}

	public int getId_Projeto() {
		return Id_Projeto;
	}


	public void setId_Projeto(int id_Projeto) {
		Id_Projeto = id_Projeto;
	}


	public int getId_Atividade() {
		return Id_Atividade;
	}


	public void setId_Atividade(int id_Atividade) {
		Id_Atividade = id_Atividade;
	}


	public String getSub_atividade() {
		return Sub_atividade;
	}


	public void setSub_atividade(String sub_atividade) {
		Sub_atividade = sub_atividade;
	}


	public String getUser_Name() {
		return User_Name;
	}


	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}


	public String getData_Inicio() {
		return Data_Inicio;
	}


	public void setData_Inicio(String data_Inicio) {
		Data_Inicio = data_Inicio;
	}


	public String getData_Fim() {
		return Data_Fim;
	}


	public void setData_Fim(String data_Fim) {
		Data_Fim = data_Fim;
	}


	public String getHota_Inicio() {
		return Hota_Inicio;
	}


	public void setHota_Inicio(String hota_Inicio) {
		Hota_Inicio = hota_Inicio;
	}


	public String getHora_Fim() {
		return Hora_Fim;
	}


	public void setHora_Fim(String hora_Fim) {
		Hora_Fim = hora_Fim;
	}



}
