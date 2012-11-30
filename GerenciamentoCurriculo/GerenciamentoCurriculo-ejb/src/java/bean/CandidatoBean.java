/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Candidato;
import entidade.Temp;
import facade.CandidatoFacade;
import interfaces.CandidatoInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "CandidatoBean")
public class CandidatoBean implements CandidatoInterface {

    @EJB
    public CandidatoFacade candidatoFacade;
    public Candidato candidato;

    public CandidatoBean(){
        this.candidato = new Candidato();
    }
    
    public CandidatoFacade getCandidatoFacade() {
        return candidatoFacade;
    }

    public void setCandidatoFacade(CandidatoFacade candidatoFacade) {
        this.candidatoFacade = candidatoFacade;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    @Override
    public Candidato procurarCandidato(Long id) {
        return this.candidatoFacade.findCandidatoLogado(id);
    }

}
