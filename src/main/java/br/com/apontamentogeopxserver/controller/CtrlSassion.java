package br.com.apontamentogeopxserver.controller;

import java.sql.SQLException;
import br.com.apontamentogeopxserver.dao.AtividadeDAO;
import br.com.apontamentogeopxserver.dao.ProjetoDAO;
import br.com.apontamentogeopxserver.dao.SessaoDAO;
import br.com.apontamentogeopxserver.model.Sessao;

/**
 * classe responsavel por controlar as demais
 * @author marcelly.paula
 *
 */
public class CtrlSassion {
	
		
	public boolean IniciarSessao(Sessao sessao_Inicio) throws ClassNotFoundException{
	     SessaoDAO sessaodao = new SessaoDAO();
	    
	    sessao_Inicio.setId_sessao(0);
	    sessao_Inicio = sessaodao.CheckSassion(sessao_Inicio);
	   
	  //metodo que verifica se o Id_Sessao, Id_Atividade e Id_Projeto...
	    if (sessao_Inicio.getId_Sessao() == 0 && sessao_Inicio.getId_Projeto() !=0 && sessao_Inicio.getId_Atividade() !=0 ){
	    	 return sessaodao.InsertSassion(sessao_Inicio);
	    }else{
	     	return false;
	    }
	}

	public boolean FinalizarSessao(Sessao sessao_Fim) throws ClassNotFoundException{
		
		SessaoDAO sessaodao = new SessaoDAO();
		sessao_Fim = sessaodao.CheckSassion(sessao_Fim);
		
		//metodo que verifica a sessao aberta...
		if(sessao_Fim.getId_Sessao() > 0){
			return sessaodao.UpdateSassion(sessao_Fim);
			
		}else{
			return false;
		}
	}

	public String obterJsonAtividades() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		AtividadeDAO atividadedao = new AtividadeDAO();
	    
	    return atividadedao.getJsonAtividades();
	}  
	public String obterJsonProjetos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ProjetoDAO projetodao = new ProjetoDAO();
		
		return projetodao.getJsonProjetos();
	}
	
	public String ObterJsonUltimaSessaoUsuario(Sessao sessao) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		SessaoDAO sessaodao = new SessaoDAO();
			
		return sessaodao.getJsonUltimaSessaoUsuario(sessao);	
		
	}
	
}
