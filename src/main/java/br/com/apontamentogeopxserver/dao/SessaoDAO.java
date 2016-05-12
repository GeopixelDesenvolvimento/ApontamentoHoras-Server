package br.com.apontamentogeopxserver.dao;
/**
 * Classe responsavel por criar e gerenciar as sessao de cada usuario atraves dos metodos descritos abaixo:
 * InsertSassion -> Metodo responsável por inserir no banco de dados a sessao iniciada pelo usuário;
 * ArrayList<Sessao> -> Metodo responsavel por criar uma lista com as informações das sessoes em banco;
 * UpdateSassion -> Metodo responsável por autlizar o banco de dados;
 * getJsonSassion ->  Metodo responsavel por recuperar o Json com as informações do usuario
 * CheckSassion -> Metodo responsavel por fazer a verificação da sessao
 * getJsonUltimaSessaoUsuario -> Metodo responsavel por recuperar um json com a ultima sessao em aberta pelo usuario
 *  
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.apontamentogeopxserver.factory.ConnectionFactory;
import br.com.apontamentogeopxserver.model.Sessao;
import br.com.apontamentogeopxserver.utils.JSonUtils;

public class SessaoDAO {
	
	private ConnectionFactory conexaobd = new ConnectionFactory();
	
	/**
	 * Metodo responsável por inserir as infromações escolhidas pelo usuário no banco 
	 * @param sessao
	 * @return
	 * @throws ClassNotFoundException
	 */
	public boolean InsertSassion (Sessao sessao) throws ClassNotFoundException {
	
        Connection connection = conexaobd.abreconexao();
            // invoca o statement
        try (PreparedStatement stmt = connection.prepareStatement("insert into sessao (Id_Sessao, DataSys_Inicio, HoraSys_Inicio, Id_Atividade, Id_Projeto, User_Name, Sub_atividade, Status, DataSys_Fim, HoraSys_Fim) "
	                + "values  (?,?,?,?,?,?,?,?,?,?)")) {
	        
	    		stmt.setInt(1, sessao.getId_Sessao());
				stmt.setString(2, sessao.getDataSys_Inicio());
	            stmt.setString(3, sessao.getHoraSys_Inicio());
	            stmt.setInt(4, sessao.getId_Atividade());
	            stmt.setInt(5, sessao.getId_Projeto());
	            stmt.setString(6, sessao.getUser_Name());
	            stmt.setString(7, sessao.getSub_atividade());
	            stmt.setString(8, sessao.getStatus());
	            stmt.setString(9, sessao.getDataSys_Fim());
	            stmt.setString(10, sessao.getHoraSys_Fim());
	            stmt.getConnection().createStatement();
	            
	           stmt.executeUpdate();
	           stmt.close();
	           conexaobd.FechaConexao();
	      
	          return true;
	          
        }catch (SQLException e) {
        	e.printStackTrace();
        	return false;
		}
	}
	
	
    public ArrayList<Sessao> lista() throws SQLException, ClassNotFoundException{
        String sql = "select * from sessao";
        ArrayList<Sessao> sessao = new ArrayList<>();
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
                while (rs.next()){
                    Sessao ses = new Sessao();
                    ses.setId_sessao(rs.getInt("id"));
                    ses.setDataSys_Inicio(rs.getString(2));
					ses.setHoraSys_Inicio(rs.getString(3));
					ses.setId_Atividade(rs.getInt(4));
					ses.setId_Projeto(rs.getInt(5));
					ses.setUser_Name(rs.getString(6));
					ses.setSub_atividade(rs.getString(7));
					ses.setStatus(rs.getString(8));
                    ses.setDataSys_Fim(rs.getString(9));
					ses.setHoraSys_Fim(rs.getString(10));
                    
                    sessao.add(ses);

            }
                conexaobd.FechaConexao();
                return sessao;
        }
    }
    /**
     * Metodo responsavel por fazer a atualização do banco de dados
     * @param sessao_Up
     * @return
     */
	public boolean UpdateSassion(Sessao sessao_Up) {
		 				
		try {
			Connection connection = conexaobd.abreconexao();
						
			String sql = "UPDATE sessao SET Status=?, DataSys_Fim=?, HoraSys_Fim=? WHERE Id_Sessao = ?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, sessao_Up.getStatus());
			stmt.setString(2, sessao_Up.getDataSys_Fim());
            stmt.setString(3, sessao_Up.getHoraSys_Fim());
            stmt.setInt(4, sessao_Up.getId_Sessao());
            
            stmt.executeUpdate();
            stmt.close();
            conexaobd.FechaConexao();
            
            return true;
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
	}
	/**
	 * Metodo responsável por retornar o Json da sessao aberta pelo usuario
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	
	public String getJsonSassion() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String query = "select * from sessao";
       
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.execute();
                ResultSet rs = stmt.getResultSet();
               return JSonUtils.resultSet2Json(rs);
        }
        catch(Exception ex){
        	//ex.printStackTrace();
            conexaobd.FechaConexao();
            return "";
        }
	}
	/**
	 * Metodo responsável por fazer a verificação da sessaao do usuario
	 * @param sessao
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Sessao CheckSassion(Sessao sessao) throws ClassNotFoundException{
		
		String sql = "select Id_Sessao from sessao WHERE User_Name=? and Status=? and Id_Projeto=? and Id_Atividade=?";
		
		 Connection connection = conexaobd.abreconexao();
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, sessao.getUser_Name());
			stmt.setString(2, "Inicio");
			stmt.setInt(3, sessao.getId_Projeto());
			stmt.setInt(4, sessao.getId_Atividade());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				sessao.setId_sessao(rs.getInt("Id_Sessao"));
			}
			rs.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		conexaobd.FechaConexao();
		return sessao;
	}
	
	/**
	 * Metodo que retorna a ultima sessao em berto do usuario
	 * @param sessao_User
	 * @return
	 * @throws ClassNotFoundException
	 */
	
	public String getJsonUltimaSessaoUsuario(Sessao sessao_User) throws ClassNotFoundException {
		String sql = "select * from sessao WHERE User_Name=? and Status=?";

		Connection connection = conexaobd.abreconexao();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, sessao_User.getUser_Name());
			stmt.setString(2, "Inicio");
			stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if(rs != null){
            	return JSonUtils.resultSet2Json(rs);
            }else{
            	return "[]";            		
            }
		} catch (Exception ex) {
			// ex.printStackTrace();
			conexaobd.FechaConexao();
			return "";
		}
	}
}