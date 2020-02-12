package br.unicamp.bd.daos;

import java.sql.*;
import br.unicamp.bd.BD;
import br.unicamp.bd.core.*;
import br.unicamp.bd.daos.*;
import br.unicamp.cotuca.*;


public class Alunos
{
    

    public void incluir (Aluno aluno) throws Exception
    {
        try
        {
            String sql;

            sql = "INSERT INTO aluno_restful " +
                  "(ra,nome,email) " +
                  "VALUES " +
                  "(?,?,?)";

            br.unicamp.bd.BD.COMANDO.prepareStatement (sql);

            br.unicamp.bd.BD.COMANDO.setInt    (1, aluno.getRa());
            br.unicamp.bd.BD.COMANDO.setString (2, aluno.getNome());
            br.unicamp.bd.BD.COMANDO.setString  (3, aluno.getEmailAluno());

            br.unicamp.bd.BD.COMANDO.executeUpdate ();
            br.unicamp.bd.BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir aluno");
        }
    }

    public void excluir (int codigo) throws Exception
    {
        try
        {
            String sql;

            sql = "DELETE FROM aluno_restful " +
                  "WHERE ra=?";

            br.unicamp.bd.BD.COMANDO.prepareStatement (sql);

            br.unicamp.bd.BD.COMANDO.setInt (1, codigo);

            br.unicamp.bd.BD.COMANDO.executeUpdate ();
            br.unicamp.bd.BD.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir aluno");
        }
    }

    public void alterar (Aluno aluno) throws Exception
    {
        try
        {
            String sql;

            sql = "UPDATE aluno_restful SET nome=?, email=? WHERE ra = ?";

            br.unicamp.bd.BD.COMANDO.prepareStatement (sql);

            br.unicamp.bd.BD.COMANDO.setString (1, aluno.getNome());
            br.unicamp.bd.BD.COMANDO.setString  (2, aluno.getEmailAluno());
            br.unicamp.bd.BD.COMANDO.setInt    (3, aluno.getRa());

            br.unicamp.bd.BD.COMANDO.executeUpdate ();
            br.unicamp.bd.BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do aluno");
        }
    }

    public Aluno getAluno (int codigo) throws Exception
    {
        Aluno aluno = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_restful " +
                  "WHERE ra = ?";

            br.unicamp.bd.BD.COMANDO.prepareStatement (sql);

            br.unicamp.bd.BD.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)br.unicamp.bd.BD.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            aluno = new Aluno (resultado.getInt   ("ra"),
                               resultado.getString("nome"),
                               resultado.getString ("email"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }

        return aluno;
    }
    
    public MeuResultSet getAluno (String nome) throws Exception
    {
        Aluno aluno = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_restful " +
                  "WHERE nome = ?";

            br.unicamp.bd.BD.COMANDO.prepareStatement (sql);

            br.unicamp.bd.BD.COMANDO.setString(1, nome);

            MeuResultSet resultado = (MeuResultSet)br.unicamp.bd.BD.COMANDO.executeQuery ();
            return resultado;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }
    }

    public MeuResultSet getAlunos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_restful";

            br.unicamp.bd.BD.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)br.unicamp.bd.BD.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao selecionar alunos");
        }

        return resultado;
    }
}