package br.com.apontamentogeopxserver.controller;

import java.sql.SQLException;
import br.com.apontamentogeopxserver.dao.AtividadeDAO;
import br.com.apontamentogeopxserver.dao.ProjetoDAO;
import br.com.apontamentogeopxserver.dao.SessaoDAO;
import br.com.apontamentogeopxserver.model.Sessao;
import br.com.apontamentogeopxserver.model.Sistema;

/**
 * classe responsavel por inicar a sessao
 * @author marcelly.paula
 *
 */
public class CtrlSassion {
	/**
	 * Metodo resposavel por inicar a sessao do usuário	
	 * @param sessao_Inicio
	 * @return Sessao_Inicio
	 * @throws ClassNotFoundException
	 */
	public boolean IniciarSessao(Sessao sessao_Inicio) throws ClassNotFoundException{
	     SessaoDAO sessaodao = new SessaoDAO();
	    
	    sessao_Inicio.setId_Sessao(0);
	    sessao_Inicio.setDataSystem_Inicio(Sistema.ObterData());
	    sessao_Inicio.setHoraSystem_Inicio(Sistema.ObterHora());
	    sessao_Inicio = sessaodao.CheckSassion(sessao_Inicio);
	   
	  //metodo que verifica se o Id_Sessao, Id_Atividade e Id_Projeto...
	    if (sessao_Inicio.getId_Sessao() == 0 && sessao_Inicio.getId_Projeto() !=0 && sessao_Inicio.getId_Atividade() !=0 ){
	    	 return sessaodao.InsertSassion(sessao_Inicio);
	    }else{
	     	return false;
	    }
	}
	/**
	 * Metodo resposavel por finalizar a sessao do usuário
	 * @param sessao_Fim
	 * @return Sessao_Fim
	 * @throws ClassNotFoundException
	 */
	public boolean FinalizarSessao(Sessao sessao_Fim) throws ClassNotFoundException{
		
		SessaoDAO sessaodao = new SessaoDAO();
		sessao_Fim.setDataSystem_Fim(Sistema.ObterData());
		sessao_Fim.setHoraSystem_Fim(Sistema.ObterHora());
		sessao_Fim = sessaodao.CheckSassion(sessao_Fim);
		
		//metodo que verifica a sessao aberta...
		if(sessao_Fim.getId_Sessao() > 0){
			return sessaodao.UpdateSassion(sessao_Fim);
			
		}else{
			return false;
		}
	}
	/**
	 * Metodo responsaver por obter um Json com os atributos do conjunto de atividade
	 * @return getJsonAtividades()
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	public String obterJsonAtividades() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		AtividadeDAO atividadedao = new AtividadeDAO();
	    
	    return atividadedao.getJsonAtividades();
	} 
	/**
	 * Metodo responsaver por obter um Json com os atributos do conjunto de projeto
	 * @return getJsonProjetos()
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public String obterJsonProjetos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ProjetoDAO projetodao = new ProjetoDAO();
		
		return projetodao.getJsonProjetos();
	}
	/**
	 * Metodo responsaver por obter um Json com os atributos do conjunto da ultima sessao do usuário
	 * @param sessao
	 * @return getJsonUltimaSessaoUsuario 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	
	public String ObterJsonUltimaSessaoUsuario(Sessao sessao) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		SessaoDAO sessaodao = new SessaoDAO();
			
		return sessaodao.getJsonUltimaSessaoUsuario(sessao);	
		
	}
	
}
