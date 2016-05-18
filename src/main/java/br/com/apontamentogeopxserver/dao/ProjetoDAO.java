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
 * Classe responsavel por selecionar no banco de dados a escolha do usuario
 * @author marcelly.paula
 *
 */
public class ProjetoDAO {
	
	private PreparedStatement stmt;
	private ConnectionFactory conexaobd = new ConnectionFactory();
	/**
	 * Responsável por selecionar no banco a informação escolhida pelo usuario
	 * @param projeto
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public void SalvaProjeto (Projeto projeto) throws SQLException, ClassNotFoundException {
        Connection connection = conexaobd.abreconexao();
        try(PreparedStatement stmt = connection.prepareStatement("insert into projeto(Id_Projeto, Projeto) values(?,?)",
                        Statement.RETURN_GENERATED_KEYS)){
        	stmt.setString(2, projeto.getProjeto());
            stmt.executeUpdate();
            stmt.getConnection().createStatement();
            
            /**
             * Retorna o ID referente a escolha feita acima
             */

            try (ResultSet keys = stmt.getGeneratedKeys()){
            	keys.next();
                int id = keys.getInt("id");	
                projeto.setId_proj(id);
            }
        }
        
        stmt.execute();
        conexaobd.FechaConexao();
    }
	/**
	 * Cria uma lista de array com as infromações escolhidas pelo usuário a atraves do ID do projeto (Id_Projeto).
	 * @return Projeto
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
	/**
	 * Retorna o valor do Id_Projeto
	 * @param projeto
	 * @return Id_Projeto
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
    /**
     * Metodo responsável por retornar o Json com os atributos do conjunto Projeto
     * @return Json com todos os atributos do conjunto projeto
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
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
	
