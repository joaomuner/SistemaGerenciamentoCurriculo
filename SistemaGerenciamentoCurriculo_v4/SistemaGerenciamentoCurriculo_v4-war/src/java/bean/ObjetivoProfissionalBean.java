/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.AreaAtuacao;
import entidade.Candidato;
import entidade.ObjetivoProfissional;
import facade.AreaAtuacaoFacade;
import facade.CandidatoFacade;
import facade.ObjetivoProfissionalFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jota
 */
@ManagedBean(name = "ObjetivoProfissionalBean")
@RequestScoped
public class ObjetivoProfissionalBean {
    @EJB
    private ObjetivoProfissionalFacade objetivoprofissionalFacade;
    
    private ObjetivoProfissional objetivoprofissional;
    private List<ObjetivoProfissional> listaObjetivoProfissional;

    /**
     * Creates a new instance of CandidatoBean
     */
    public ObjetivoProfissionalBean() {
        this.objetivoprofissional = new ObjetivoProfissional();
        
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
    
    public int NomeObjetivoProfissional(){
        return objetivoprofissionalFacade.findAll().size();
    }
    
    public String criaObjetivoProfissional(){
        this.objetivoprofissionalFacade.create(objetivoprofissional);
       // listaCandidato = candidatoFacade.findAll();
        return "confirmacao";
    }
    
    public String verLista(){
        listaObjetivoProfissional = objetivoprofissionalFacade.findAll();
        return "lista";
    }
//------------------------------------------------------------------------------------------

    public List<ObjetivoProfissional> getListaObjetivoProfissional() {
        return listaObjetivoProfissional;
    }

    public void setListaObjetivoProfissional(List<ObjetivoProfissional> listaObjetivoProfissional) {
        this.listaObjetivoProfissional = listaObjetivoProfissional;
    }

    public ObjetivoProfissional getObjetivoprofissional() {
        return objetivoprofissional;
    }

    public void setObjetivoprofissional(ObjetivoProfissional objetivoprofissional) {
        this.objetivoprofissional = objetivoprofissional;
    }

    public ObjetivoProfissionalFacade getObjetivoprofissionalFacade() {
        return objetivoprofissionalFacade;
    }

    public void setObjetivoprofissionalFacade(ObjetivoProfissionalFacade objetivoprofissionalFacade) {
        this.objetivoprofissionalFacade = objetivoprofissionalFacade;
    }
      
    
    
 
}
