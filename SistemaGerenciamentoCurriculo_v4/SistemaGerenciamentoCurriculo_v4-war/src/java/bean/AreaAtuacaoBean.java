/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.AreaAtuacao;
import entidade.Candidato;
import facade.AreaAtuacaoFacade;
import facade.CandidatoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jota
 */
@ManagedBean(name = "AreaAtuacaoBean")
@RequestScoped
public class AreaAtuacaoBean {
    @EJB
    private AreaAtuacaoFacade areaatuacaoFacade;
    
    private AreaAtuacao areaatuacao;
    private List<AreaAtuacao> listaAreaAtuacao;

    /**
     * Creates a new instance of CandidatoBean
     */
    public AreaAtuacaoBean() {
        this.areaatuacao = new AreaAtuacao();
        
    }
//------------------------------------------------------------------------------------------
    public String novo(){
        return "form";
    }
    
    public String index(){
        return "index";
    }
    
    public String confirmacao(){
        return "confirmacao";
    }
    
    public int NomeAreaAtuacao(){
        return areaatuacaoFacade.findAll().size();
    }
    
    public String criaAreaAtuacao(){
        this.areaatuacaoFacade.create(areaatuacao);
       // listaCandidato = candidatoFacade.findAll();
        return "confirmacao";
    }
    
    public String verLista(){
        listaAreaAtuacao = areaatuacaoFacade.findAll();
        return "lista";
    }
//------------------------------------------------------------------------------------------

    public AreaAtuacao getAreaatuacao() {
        return areaatuacao;
    }

    public void setAreaatuacao(AreaAtuacao areaatuacao) {
        this.areaatuacao = areaatuacao;
    }

    public AreaAtuacaoFacade getAreaatuacaoFacade() {
        return areaatuacaoFacade;
    }

    public void setAreaatuacaoFacade(AreaAtuacaoFacade areaatuacaoFacade) {
        this.areaatuacaoFacade = areaatuacaoFacade;
    }

    public List<AreaAtuacao> getListaAreaAtuacao() {
        return listaAreaAtuacao;
    }

    public void setListaAreaAtuacao(List<AreaAtuacao> listaAreaAtuacao) {
        this.listaAreaAtuacao = listaAreaAtuacao;
    }
    
    
    
    
    
    
    
}
