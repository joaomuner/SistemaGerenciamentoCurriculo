/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Candidato;
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
@ManagedBean(name = "CandidatoBean")
@RequestScoped
public class CandidatoBean {

    @EJB
    private CandidatoFacade candidatoFacade;
    private Candidato cadidato;
    private List<Candidato> listaCandidato;

    /**
     * Creates a new instance of CandidatoBean
     */
    public CandidatoBean() {
        this.cadidato = new Candidato();

    }
//------------------------------------------------------------------------------------------

    public String novo() {
        return "form";
    }

    public String index() {
        return "index";
    }

    public String confirmacao() {
        return "confirmacao";
    }

    public int NomeCandidato() {
        return candidatoFacade.findAll().size();
    }

    public String criaCandidato() {
        this.candidatoFacade.create(cadidato);
        // listaCandidato = candidatoFacade.findAll();
        return "confirmacao";
    }

    public String verLista() {
        listaCandidato = candidatoFacade.findAll();
        return "lista";
    }
//------------------------------------------------------------------------------------------

    public Candidato getCadidato() {
        return cadidato;
    }

    public void setCadidato(Candidato cadidato) {
        this.cadidato = cadidato;
    }

    public CandidatoFacade getCandidatoFacade() {
        return candidatoFacade;
    }

    public void setCandidatoFacade(CandidatoFacade candidatoFacade) {
        this.candidatoFacade = candidatoFacade;
    }
    
    public List<Candidato> getListaCandidato() {
        if (listaCandidato == null) {
            listaCandidato = new ArrayList<Candidato>();
        }

        return listaCandidato;
    }

    public void setListaCandidato(List<Candidato> listaCandidato) {
        this.listaCandidato = listaCandidato;
    }
}
