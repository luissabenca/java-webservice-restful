/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienterest;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ClienteRest {

    /**
     * @param args the command line arguments
     */
    
    static String urlConsultaTodos     = "http://localhost:8080/WSRestServidor/webresources/generic/consulta";
    static String urlConsultaRa        = "http://localhost:8080/WSRestServidor/webresources/generic/consultaRa/";
    static String urlConsultaNome      = "http://localhost:8080/WSRestServidor/webresources/generic/consultaNome/";
    static String urlIncluirAluno      = "http://localhost:8080/WSRestServidor/webresources/generic/incluirAluno/";
    static String urlAlterarAluno      = "http://localhost:8080/WSRestServidor/webresources/generic/alterarAluno/";
    static String urlExcluirAluno      = "http://localhost:8080/WSRestServidor/webresources/generic/excluirAluno/";
        
    public static void main(String[] args) throws IOException 
    {
        ClienteRest clienteRest = new ClienteRest();
        
        while(true)
        {
            System.out.println("Cliente Restfull Java");
            System.out.println("-----------------------");
            System.out.println("1 - Consultar todos os alunos");
            System.out.println("2 - Consultar aluno por RA");
            System.out.println("3 - Consultar aluno por nome");
            System.out.println("4 - Inserir aluno");
            System.out.println("5 - Alterar nome/email por RA");
            System.out.println("6 - Excluir aluno por RA");
            System.out.println("7 - Sair do programa");
            System.out.print("Digite a opção desejada:");
            
            Scanner teclado = new Scanner(System.in);
            int opcao = teclado.nextInt();
            
            switch(opcao)
            {
                case 1:
                    Aluno[] ret = clienteRest.consultaTodos();
                    for(int i=0;i<ret.length;i++)
                        System.out.println("RA:" + ret[i].getRa() + " Nome:" + ret[i].getNome() + " Email:" + ret[i].getEmailAluno());
                    System.out.println("");
                break;
                    
                case 2:
                    try
                    {
                        System.out.print("Digite o RA do aluno:");
                        int ra = teclado.nextInt();
                        Aluno ret2 = clienteRest.consultaAlunoRa(ra);
                        System.out.println("RA:" + ret2.getRa() + " Nome:" + ret2.getNome() + " Email:" + ret2.getEmailAluno() + "\n");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Por favor insira dados corretos");
                    }
                break;
                
                case 3:
                    try
                    {
                        System.out.print("Digite o nome do aluno:");
                        String nome = teclado.next();
                        Aluno[] ret3 = clienteRest.consultaAlunoNome(nome);
                        for(int i=0;i<ret3.length;i++)
                            System.out.println("RA:" + ret3[i].getRa() + " Nome:" + ret3[i].getNome() + " Email:" + ret3[i].getEmailAluno());
                        System.out.println("");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Por favor insira dados corretos");
                    }
                break;
                
                case 4:
                    try
                    {
                        Aluno novo = new Aluno();
                        System.out.print("Digite o RA do aluno:");
                        novo.setRa(teclado.nextInt());
                        System.out.print("Digite o nome do aluno:");
                        novo.setNome(teclado.next());
                        System.out.print("Digite o email do aluno:");
                        novo.setEmailAluno(teclado.next());
                        Informacao ret4 = clienteRest.incluiAluno(novo);
                        System.out.println(ret4.getInformacao()+"\n");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Por favor insira dados corretos");
                    }
                break;
                
                case 5:
                    try
                    {
                        Aluno alterado = new Aluno();
                        System.out.print("Digite o RA do aluno:");
                        alterado.setRa(teclado.nextInt());
                        System.out.print("Digite o novo nome do aluno:");
                        alterado.setNome(teclado.next());
                        System.out.print("Digite o novo email do aluno:");
                        alterado.setEmailAluno(teclado.next());
                        Informacao ret5 = clienteRest.alteraAluno(alterado);
                        System.out.println(ret5.getInformacao()+"\n");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Por favor insira dados corretos");
                    }
                break;
                
                case 6:
                    try
                    {
                        System.out.print("Digite o RA do aluno:");
                        int raExcluir = teclado.nextInt();
                        Informacao ret6 = clienteRest.excluiAluno(raExcluir);
                        System.out.println(ret6.getInformacao()+"\n");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Por favor insira dados corretos");
                    }
                break;
                
                case 7:
                    System.exit(0);
                    
            }
        }        
    }
    
    public Aluno[] consultaTodos() throws MalformedURLException, IOException
    {
        
        URL objURL = new URL(urlConsultaTodos);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
                
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        Gson gson = new Gson();
        
        Aluno[] retorno = gson.fromJson(br, Aluno[].class);
        
        br.close();
        con.disconnect();
                
        return retorno;
    }
    
    public Aluno consultaAlunoRa(int ra) throws MalformedURLException, IOException{
        
        URL objURL = new URL(urlConsultaRa + ra);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
                
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
         
        Gson gson = new Gson();
        
        Aluno retorno = gson.fromJson(br, Aluno.class);
        
        br.close();
        con.disconnect();
        
        
        
        return retorno;
    }
    
    public Aluno[] consultaAlunoNome(String nome) throws MalformedURLException, IOException{
        URL objURL = new URL(urlConsultaNome + nome);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
                
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        Gson gson = new Gson();
        
        Aluno[] retorno = gson.fromJson(br, Aluno[].class);
        
        br.close();
        con.disconnect();
        
        return retorno;
    }
    
    public Informacao incluiAluno(Aluno aluno) throws MalformedURLException, IOException{
        
        URL objURL = new URL(urlIncluirAluno);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        
        con.setDoOutput(true);
        
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        
        Gson gson = new Gson();
        //Formata em json o item da lista a ser inserido com POST
        String output = gson.toJson(aluno);
                
        //Pega a conexão aberta em con (getOutputStream()) e faz OutputStream, ouseja, faz o fluxo de dados do cliente para o servidor
        OutputStream os = con.getOutputStream();
        
        //Escreve o output tranformado em Bytes        
        os.write(output.getBytes());
        
        
        int responseCode = con.getResponseCode();
        //Se retornar 200 significa que deu certo
                
        //Armazena o retorno do método POST do servidor        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 

        Informacao retorno = gson.fromJson(br, Informacao.class);
        
        br.close();
        con.disconnect();
        return retorno;
    }
    
    
    public Informacao alteraAluno(Aluno aluno) throws MalformedURLException, IOException{
        
        URL objURL = new URL(urlAlterarAluno);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        
        con.setDoOutput(true);
        
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        
        Gson gson = new Gson();
        //Formata em json o item da lista a ser inserido com POST
        String output = gson.toJson(aluno);
         
                
        //Pega a conexão aberta em con (getOutputStream()) e faz OutputStream, ouseja, faz o fluxo de dados do cliente para o servidor
        OutputStream os = con.getOutputStream();
        
        //Escreve o output tranformado em Bytes        
        os.write(output.getBytes());
        
        
        int responseCode = con.getResponseCode();
        //Se retornar 200 significa que deu certo
                
        //Armazena o retorno do método POST do servidor        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
        
        Informacao retorno = gson.fromJson(br, Informacao.class);
        
        br.close();
        con.disconnect();
        return retorno;
    }
    
    public Informacao excluiAluno(int ra) throws MalformedURLException, IOException{
        
        URL objURL = new URL(urlExcluirAluno + ra);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
                
        BufferedReader br = 
             new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        Gson gson = new Gson();
         
        Informacao retorno = gson.fromJson(br, Informacao.class);
        
        br.close();
        con.disconnect();
        return retorno;
    }
}
