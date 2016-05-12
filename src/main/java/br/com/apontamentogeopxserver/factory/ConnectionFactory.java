package br.com.apontamentogeopxserver.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsavel por abir e fechar a conexao com o banco de dados 
 * Banco de Dados criado com o nome apontamentogeopx;
 * com as tabelas: Atividades que contem o id e descricao das mesmas. tebala Projeto que tambpem contem o id e decricao 
 * e a tabela Sessoa_user, onde sera nesta tabela que ira armazenas as informacoes da sessao dde cada usuario, ou seja, 
 * qual o projeto, atividade escolhido na data e hora da execucao da mesma
 * @author marcelly.paula
 *
 */


public class ConnectionFactory {

    //private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String Url = "jdbc:mysql://127.0.0.1:3306/apontamentogeopx";
    private static final String User = "root";
    private static final String Passwd = "";
    private Connection conexao;
    private Statement stmt;
    private ResultSet rs;
	/**
	 * Metodo reponsavel por criar a conexao com o bando de dados apontamentogeopx
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public Connection abreconexao() throws ClassNotFoundException {
		
		if (conexao == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(Url, User, Passwd);
				stmt = conexao.createStatement();
				System.out.println("Conectado com sucesso!");
				return conexao;
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.out.println("Erro de Conexao! \n Erro:"
						+ ex.getMessage());
				return null;
			}
		} else {
			return conexao;
		}
	}
    /**
     * Metodo responsavel por encerrar a conexao com o banco de dados apontamento geopx 
     * @param conexao
     * @param stmt
     * @param rs
     */
    public void FechaConexao(){
    	try{
    		if (conexao != null){
    			conexao.close();
    			conexao = null;
    		}
    		if (stmt != null){
    			stmt.close();
    		}
    		if (rs != null){
    			rs.close();
    		}
    	}catch (Exception ex){
    		//ex.printStackTrace();
    		System.out.println("Erro ao fechar a conexao com o banco de dados!" + Url);
    	
    	}
    	
    }
}
