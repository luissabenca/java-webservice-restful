/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienterest;

public class Informacao 
{
    private String informacao;
    
    public Informacao(String info)
    {
        this.informacao = info;
    }
    
    public Informacao()
    {
        this.informacao = null;
    }
    
    public String getInformacao()
    {
        return this.informacao;
    }
    
    public void setInformacao(String info)
    {
        this.informacao = info;
    }
}
