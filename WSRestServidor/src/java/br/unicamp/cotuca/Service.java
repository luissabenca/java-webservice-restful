/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicamp.cotuca;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.Pattern.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("generic")
public class Service {
    
    private static ArrayList<Aluno> listaAluno = 
                           new ArrayList<Aluno>();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Service
     */
    public Service() 
    {
        
    }

    /**
     * Retrieves representation of an instance of br.unicamp.cotuca.Service
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Service
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaAluno()
    {
        ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
        
        br.unicamp.bd.daos.Alunos alunos = new br.unicamp.bd.daos.Alunos();
        
        try
        {
            br.unicamp.bd.core.MeuResultSet resultSet = alunos.getAlunos();
            resultSet.beforeFirst();
            while(resultSet.next())
            {
                listaAluno.add(new Aluno(resultSet.getInt("ra"),resultSet.getString("nome"),resultSet.getString("email")));
            }
        }
        catch(Exception ex)
        {
            listaAluno.clear();
            
            listaAluno.add(new Aluno(0,"erro","erro"));
        }
        
        return listaAluno;
        
    }
    
    @GET
    @Path("consultaRa/{Ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno consultaRaAluno(@PathParam("Ra")int ra){
        
        br.unicamp.bd.daos.Alunos alunos = new br.unicamp.bd.daos.Alunos();
        
        try 
        {
            return alunos.getAluno(ra);
        } 
        catch (Exception ex) 
        {
            return new Aluno(0,"erro","erro");
        }
    }
    
    @GET
    @Path("consultaNome/{Nome}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaNomeAluno(@PathParam("Nome")String nome)
    {
        
         ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
        
        br.unicamp.bd.daos.Alunos alunos = new br.unicamp.bd.daos.Alunos();
        
        try
        {
            br.unicamp.bd.core.MeuResultSet resultSet = alunos.getAluno(nome);
            resultSet.beforeFirst();
            while(resultSet.next())
            {
                listaAluno.add(new Aluno(resultSet.getInt("ra"),resultSet.getString("nome"),resultSet.getString("email")));
            }
        }
        catch(Exception ex)
        {
            listaAluno.clear();
            
            listaAluno.add(new Aluno(0,"erro","erro"));
        }
        
        return listaAluno;
        
    } 
    
    
    
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Informacao incluiAluno(Aluno aluno)
    {                
        br.unicamp.bd.daos.Alunos alunos = new br.unicamp.bd.daos.Alunos();
        
        try 
        {
            alunos.incluir(aluno);
        } 
        catch (Exception ex) 
        {
            return new Informacao("Erro ao incluir o aluno. Por favor verifique os dados");
        }
        
        return new Informacao("Sucesso ao incluir o aluno");
    }
    
    @PUT
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    public Informacao alterarAluno (Aluno aluno)
    {        
        br.unicamp.bd.daos.Alunos alunos = new br.unicamp.bd.daos.Alunos();
        
        try 
        {
            alunos.alterar(aluno);
        } 
        catch (Exception ex) 
        {
            return new Informacao("Erro ao alterar o aluno. Por favor verifique os dados.");
        }
        
        return new Informacao("Sucesso ao alterar o aluno");
    }   
    
    @GET
    @Path("/excluirAluno/{Ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Informacao excluirAluno(@PathParam("Ra") Integer ra) throws Exception
    {        
        br.unicamp.bd.daos.Alunos alunos = new br.unicamp.bd.daos.Alunos();
        
        try 
        {
            alunos.excluir(ra);
        } 
        catch (Exception ex) 
        {
            return new Informacao("Erro ao excluir o aluno. Por favor verifique os dados.");
        }
        
        return new Informacao("Sucesso ao excluir o aluno");
    }
}
