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
	
	/**
	 * Responsável por selecionar no banco a informação escolhida pelo usuario
	 * @param atividade
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
		
	public void SalvaAtividade(Atividade atividade) throws SQLException, ClassNotFoundException{
		Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement("insert into atividades(Id_Atividade, Atividade) values (?, ?)", 
                        Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(2, atividade.getAtividade());
            stmt.execute();
            stmt.getConnection().createStatement();
            
            /**
             * Retorna o ID referente a escolha feita acima
             */

            try (ResultSet keys = stmt.getGeneratedKeys()){
                    keys.next();
                    int id = keys.getInt("id");
                    atividade.setId_Atividade(id);
                    }
        }
        
            stmt.execute();
            
            conexaobd.FechaConexao();
    }  
	
	/**
	 * Cria uma lista de array com as infromações escolhidas pelo usuário a atraves do ID do atividade (Id_Atividade).
	 * @return Atividade
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

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

	/**
	 * Retorna o valor do Id_Atividade
	 * @param atividade
	 * @return Id_Atividade
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
	/**
	 * Metodo responsável por retornar o Json com os atributos do conjunto de Ativadades
	 * @return Json com todos os atributos do conjunto de atividades
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
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
