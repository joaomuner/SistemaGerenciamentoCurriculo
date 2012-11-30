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
    
    private Long descricao;
    private Long valor;
    private String empresa;
    private String descAtividade;
    private String aprovado;
    private Long id;
    private String nome;

    public Long getValor() {
        return valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public Temp(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(String aprovado) {
        this.aprovado = aprovado;
    }

    public String getDescAtividade() {
        return descAtividade;
    }

    public void setDescAtividade(String descAtividade) {
        this.descAtividade = descAtividade;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
        
    public Temp(Long descricao, Long valor){
        this.descricao = descricao;
        this.valor = valor;
    }
    
    public Temp(String empresa, String descricaoAtv, String aprovado){
        this.empresa = empresa;
        this.descAtividade = descricaoAtv;
        this.aprovado = aprovado;
    }
    
     public Temp(String nome, Long valor){
        this.nome = nome;
        this.valor = valor;
    }
    public Long getDescricao() {
        return descricao;
    }


    public void setDescricao(Long descricao) {
        this.descricao = descricao;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
    
    
       
  
}
