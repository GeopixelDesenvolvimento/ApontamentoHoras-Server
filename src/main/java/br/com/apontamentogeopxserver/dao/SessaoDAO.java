package br.com.apontamentogeopxserver.dao;

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
	 * Realizar a inserção de informações no Banco de Dados.
	 * @param sessao
	 * @return
	 * @throws ClassNotFoundException
	 */
	public boolean InsertSassion (Sessao sessao) throws ClassNotFoundException {
	
        Connection connection = conexaobd.abreconexao();
            // invoca o statement
        try (PreparedStatement stmt = connection.prepareStatement("insert into sessao (Id_Sessao, Data_Inicio, DataSystem_Inicio, Hora_Inicio, HoraSystem_Inicio, Id_Atividade, Id_Projeto, User_Name, Sub_atividade, Status, Data_Fim, DataSystem_Fim, Hora_Fim, HoraSystem_Fim) "
	                + "values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
	        
	    		stmt.setInt(1, sessao.getId_Sessao());
				stmt.setString(2, sessao.getData_Inicio());
				stmt.setString(3, sessao.getDataSystem_Inicio());
	            stmt.setString(4, sessao.getHora_Inicio());
	            stmt.setString(5, sessao.getHoraSystem_Inicio());
	            stmt.setInt(6, sessao.getId_Atividade());
	            stmt.setInt(7, sessao.getId_Projeto());
	            stmt.setString(8, sessao.getUser_Name());
	            stmt.setString(9, sessao.getSub_atividade());
	            stmt.setString(10, sessao.getStatus());
	            stmt.setString(11, sessao.getData_Fim());
	            stmt.setString(12, sessao.getDataSystem_Fim());
	            stmt.setString(13, sessao.getHora_Fim());
	            stmt.setString(14, sessao.getHoraSystem_Fim());
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
	
	/**
	 * Cria uma lista de array com as infromações escolhidas pelo usuário a atraves do ID da sessao (Id_Sessao).
	 * @return sessao completa do usuário
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
    public ArrayList<Sessao> lista() throws SQLException, ClassNotFoundException{
        String sql = "select * from sessao";
        ArrayList<Sessao> sessao = new ArrayList<>();
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
                while (rs.next()){
                    Sessao ses = new Sessao();
                    ses.setId_Sessao(rs.getInt("id"));
                    ses.setData_Inicio(rs.getString(2));
                    ses.setDataSystem_Inicio(rs.getString(3));
					ses.setHora_Inicio(rs.getString(4));
					ses.setHoraSystem_Inicio(rs.getString(5));
					ses.setId_Atividade(rs.getInt(6));
					ses.setId_Projeto(rs.getInt(7));
					ses.setUser_Name(rs.getString(8));
					ses.setSub_atividade(rs.getString(9));
					ses.setStatus(rs.getString(10));
                    ses.setData_Fim(rs.getString(11));
                    ses.setDataSystem_Fim(rs.getString(12));
					ses.setHora_Fim(rs.getString(13));
					ses.setHoraSystem_Fim(rs.getString(14));
                    
                    sessao.add(ses);

            }
                conexaobd.FechaConexao();
                return sessao;
        }
    }
    /**
     * Metodo responsavel por fazer a atualização do banco de dados, passando para o mesmo as informações de Data/Hora escolhida pelo usuário e Data/Hora do sistema.
     * @param sessao_Up
     * @return Sessao atualizada 
     */
	public boolean UpdateSassion(Sessao sessao_Up) {
		 				
		try {
			Connection connection = conexaobd.abreconexao();
						
			String sql = "UPDATE sessao SET Status=?, Data_Fim=?, DataSystem_Fim=?, Hora_Fim=?, HoraSystem_Fim=? WHERE Id_Sessao = ?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, sessao_Up.getStatus());
			stmt.setString(2, sessao_Up.getData_Fim());
			stmt.setString(3, sessao_Up.getDataSystem_Fim());
            stmt.setString(4, sessao_Up.getHora_Fim());
            stmt.setString(5, sessao_Up.getHoraSystem_Fim());
            stmt.setInt(6, sessao_Up.getId_Sessao());
            
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
	 * @return Jeson com todas os atributos no conjunto do resultado
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
	 * Metodo responsável por fazer a verificação da sessao do usuario
	 * @param sessao
	 * @return Recupera a sessao em aberta 
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
				sessao.setId_Sessao(rs.getInt("Id_Sessao"));
			}
			rs.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		conexaobd.FechaConexao();
		return sessao;
	}
	
	/**
	 * Metodo que retorna a ultima sessao em berta pelo usuario
	 * @param sessao_User
	 * @return Retornar um Json com os atributos da ultima sessao em aberta pdo usuário
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