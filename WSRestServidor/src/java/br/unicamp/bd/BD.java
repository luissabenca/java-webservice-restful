package br.unicamp.bd;

import br.unicamp.bd.BD;
import br.unicamp.bd.core.*;
import br.unicamp.bd.daos.*;
import br.unicamp.cotuca.*;

public class BD
{
    public static final MeuPreparedStatement COMANDO;
    public static final Alunos ALUNOS; //um como esse para cada classe DAO

    static
    {
    	MeuPreparedStatement comando = null;
     	Alunos               alunos  = null; //um como esse para cada classe DAO

    	try
        {
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD17192",
            "BD17192", "123456");

            alunos = new Alunos (); //um como esse para cada classe DAO
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
        ALUNOS  = alunos; //um como esse para cada classe DAO
    }
}