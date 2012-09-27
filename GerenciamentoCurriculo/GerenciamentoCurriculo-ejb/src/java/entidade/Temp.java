/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author thaiane
 */
public class Temp {
    
    private String descricao;
    private Long valor;
    
    public Temp(String descricao, Long valor){
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
       
}
