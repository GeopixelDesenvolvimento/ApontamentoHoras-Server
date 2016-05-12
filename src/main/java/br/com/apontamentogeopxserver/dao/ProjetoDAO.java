package br.com.apontamentogeopxserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.apontamentogeopxserver.factory.ConnectionFactory;
import br.com.apontamentogeopxserver.model.Projeto;
import br.com.apontamentogeopxserver.utils.JSonUtils;


/**
 * Classe respons�vel por selecionar no banco de dados a escolha do usu�rio
 * @author marcelly.paula
 *
 */
public class ProjetoDAO {
	
	private PreparedStatement stmt;
	private ConnectionFactory conexaobd = new ConnectionFactory();
	
	public void SalvaProjeto (Projeto projeto) throws SQLException, ClassNotFoundException {
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement("insert into projeto(Id_Projeto, Projeto) values(?,?)",
                        Statement.RETURN_GENERATED_KEYS)){
        	stmt.setString(2, projeto.getProjeto());
            stmt.executeUpdate();
            stmt.getConnection().createStatement();

            try (ResultSet keys = stmt.getGeneratedKeys()){
            	keys.next();
                int id = keys.getInt("id");	
                projeto.setId_proj(id);
            }
        }
        
        stmt.execute();
        conexaobd.FechaConexao();
    }
	public ArrayList<Projeto> lista() throws SQLException, ClassNotFoundException{
		String query = "select * from projeto";
        ArrayList<Projeto> projeto = new ArrayList<>();
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                    Projeto proj = new Projeto();
                    proj.setId_proj(rs.getInt("Id_Projeto"));
                    proj.setProjeto(rs.getString("Projeto"));
                    projeto.add(proj);	
        }
            conexaobd.FechaConexao();
            return projeto;
        }
    }
    public int getIdProjeto(String projeto) throws SQLException, ClassNotFoundException{
	    Connection connection = conexaobd.abreconexao();
	    
	    int id=0;
	    String  query = "select Id_Projeto from Projeto where projeto =\""+projeto+"\"";
	    try(PreparedStatement stmt = connection.prepareStatement(query)){
	        stmt.execute();
	        ResultSet rs = stmt.getResultSet();
	        while (rs.next()){
	            id = rs.getInt("Id_Projeto");
	        }
	        conexaobd.FechaConexao();
	        return id;
	    }
	    catch(Exception ex){
	    	//ex.printStackTrace();
	    	conexaobd.FechaConexao();
	        return 0;
	    }
	}
	public String getJsonProjetos() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String query = "select * from projeto order by projeto";
       
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
}
	
