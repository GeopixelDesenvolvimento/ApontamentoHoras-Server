package br.com.geopixel.ApontamentoGeopx_Server;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.apontamentogeopxserver.controller.CtrlSassion;
import br.com.apontamentogeopxserver.model.Sessao;

/**
 * Classe responsavel por dar ação a pagina
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    /**
     * Metodo que inicia uma nova sessao para o usuario
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @POST
	@Path("/beginSassion")
    public Response beginSassion(
    		@FormParam("User_Name") String user_Name,
    		@FormParam("Id_Atividade") int Id_Atividade,
    		@FormParam("Id_Projeto") int Id_Projeto,
    		@FormParam("Data_Inicio") String Data_Inicio,
    		@FormParam("Hora_Inicio") String Hora_Inicio,
    		@FormParam("Status") String Status,
    		@FormParam("Sub_atividade") String Sub_atividade) throws ClassNotFoundException, SQLException{
		CtrlSassion controller = new CtrlSassion();
		Sessao sassion = new Sessao();
		
		sassion.setId_Atividade(Id_Atividade);
		sassion.setId_Projeto(Id_Projeto);
		sassion.setStatus(Status);
		sassion.setData_Inicio(Data_Inicio);
 		sassion.setHora_Inicio(Hora_Inicio);
		sassion.setUser_Name(user_Name);
		sassion.setSub_atividade(Sub_atividade);
		
		String result = "";
		
		try {
 			if(controller.IniciarSessao(sassion)){
				
				result = "true";
			}else{
				result = "false";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).entity(result).build();
	}
        
    /**
     * Metodo responsavel por encerrar a sessao do usuario
     * @param Id_Atividade
     * @param Id_Projeto
     * @param status
     * @param user_Name
     * @param Sub_atividade
     * @param Status
     * @param DataSys_Fim
     * @param HoraSys_Fim
     * @return
     */
   
    @POST
	@Path("/closeSassion")
    public Response closeSassion(
    		@FormParam("User_Name") String user_Name,
    		@FormParam("Id_Atividade") int Id_Atividade,
    		@FormParam("Id_Projeto") int Id_Projeto,
    		@FormParam("status") String status,
    		@FormParam("Data_Fim") String Data_Fim,
    		@FormParam("Hora_Fim") String Hora_Fim,
    		@FormParam("Status") String Status,
    		@FormParam("Sub_atividade") String Sub_atividade) {
    		
		
		CtrlSassion controller = new CtrlSassion();
		Sessao sassion = new Sessao();
		
		sassion.setId_Atividade(Id_Atividade);
		sassion.setId_Projeto(Id_Projeto);
		sassion.setStatus(Status);
		sassion.setData_Fim(Data_Fim);
		sassion.setHora_Fim(Hora_Fim);
		sassion.setUser_Name(user_Name);
		sassion.setSub_atividade(Sub_atividade);
		
		String result = "";
		
		try {
			if(controller.FinalizarSessao(sassion)){
				result = "true";
			}else{
				result = "false";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).entity(result).build();
	}
        
   
    /**
     * Metodo que retorna as informaçoes da tabela Atividade do BD e carrega para 
     * o combobox Atividade da pagina
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
  
    @GET
   	@Path("/getListActivites")
   	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
       public Response getListActivites() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
   		CtrlSassion sassion = new CtrlSassion();
   		String result = "";
   		try {
   			result = sassion.obterJsonAtividades();
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return Response.status(200).entity(result).build();
   	}
    /**
     * Metodo que retorna as informaçoes da tabela Projeto do BD e carrega para 
     * o combobox projeto da pagina
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
       
    @GET
   	@Path("/getListProjets")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
       public Response getListProjets() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
   		CtrlSassion sassion = new CtrlSassion();
   		
   		String result = "";
   		try {
   			
   			result = sassion.obterJsonProjetos();
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return Response.status(200).entity(result).build();
   	}
    /**
     * Metodo que carrega a ultima sessao em aberta do usuário
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    
    @GET
   	@Path("/getLastSassionByUser")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getLastSassionByUser(@QueryParam("user") String user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
   		CtrlSassion ctrlSassion = new CtrlSassion();
   		Sessao sassion = new Sessao();
   		String result = "";
   		try {
   			sassion.setUser_Name(user);  			
   			result = ctrlSassion.ObterJsonUltimaSessaoUsuario(sassion);
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return Response.status(200).entity(result).build();
   	}
   	
}
