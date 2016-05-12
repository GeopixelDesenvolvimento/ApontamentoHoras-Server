package br.com.apontamentogeopxserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.apontamentogeopxserver.factory.ConnectionFactory;
import br.com.apontamentogeopxserver.model.Atividade;
import br.com.apontamentogeopxserver.utils.JSonUtils;


/**
 * Classe responsavel por conter os mestodos de inser��o 
 * @author marcelly.paula
 *
 */

public class AtividadeDAO {
	private PreparedStatement stmt;
	private ConnectionFactory conexaobd = new ConnectionFactory();
		
	public void SalvaAtividade(Atividade atividade) throws SQLException, ClassNotFoundException{
		Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement("insert into atividades(Id_Atividade, Atividade) values (?, ?)", 
                        Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(2, atividade.getAtividade());
            stmt.execute();
            stmt.getConnection().createStatement();

            try (ResultSet keys = stmt.getGeneratedKeys()){
                    keys.next();
                    int id = keys.getInt("id");
                    atividade.setId_Atividade(id);
                    }
        }
        
            stmt.execute();
            
            conexaobd.FechaConexao();
    }       

	public ArrayList<Atividade> lista() throws SQLException, ClassNotFoundException {
        String query = "select * from atividades";
        ArrayList<Atividade> atividade = new ArrayList<>();
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.execute();
                ResultSet rs = stmt.getResultSet();
                while (rs.next()){
                        Atividade ativ = new Atividade();
                        ativ.setId_Atividade(rs.getInt("Id_Atividade"));
                        ativ.setAtividade(rs.getString("Atividade"));
                        atividade.add(ativ); 
                    }
        }
        
        conexaobd.FechaConexao();
        return atividade;
    }

	public int getIdAtividade(String atividade) throws SQLException, ClassNotFoundException{
    	Connection connection = conexaobd.abreconexao();
        
        int id=0;
        String  query = "select Id_Atividade from Atividades where atividade =\""+atividade+"\"";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                id = rs.getInt("Id_Atividade");
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
	
	public String getJsonAtividades() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String query = "select * from atividades order by atividade";
       
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
