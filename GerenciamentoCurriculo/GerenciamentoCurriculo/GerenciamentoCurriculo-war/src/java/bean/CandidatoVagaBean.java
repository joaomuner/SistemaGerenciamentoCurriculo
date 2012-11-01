/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.CandidatoVaga;
import facade.CandidatoVagaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tuca
 */
@ManagedBean
@RequestScoped
public class CandidatoVagaBean {

    @EJB
    private CandidatoVagaFacade candidatoVagaFacade;
    private CandidatoVaga candidatoVaga;

    public CandidatoVagaBean() {
        this.candidatoVaga = new CandidatoVaga();
    }

    public CandidatoVaga getCandidatoVaga() {
        return candidatoVaga;
    }

    public void setCandidatoVaga(CandidatoVaga candidatoVaga) {
        this.candidatoVaga = candidatoVaga;
    }

    public CandidatoVagaFacade getCandidatoVagaFacade() {
        return candidatoVagaFacade;
    }

    public void setCandidatoVagaFacade(CandidatoVagaFacade candidatoVagaFacade) {
        this.candidatoVagaFacade = candidatoVagaFacade;
    }

    public String insereCandidatoVaga() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String idCandidatoStr = (String) ctx.getExternalContext().getRequestParameterMap().get("idCandidato");
        String idVagaStr = (String) ctx.getExternalContext().getRequestParameterMap().get("idVaga");

        if (ValidarCandidatoVaga(Long.parseLong(idCandidatoStr), Long.parseLong(idVagaStr))) {
            candidatoVaga.setIdCandidato(Long.parseLong(idCandidatoStr));
            candidatoVaga.setIdVaga(Long.parseLong(idVagaStr));

            this.candidatoVagaFacade.create(candidatoVaga);

            return "confirmacaoVaga";
        }
        
        return "candidatoCadastadoVaga";
    }
    
    private boolean ValidarCandidatoVaga(Long idCandidato, Long idVaga) {
        CandidatoVaga cv = this.candidatoVagaFacade.findCandidatoVaga(idCandidato, idVaga);
        if (cv != null) {
            return false;
        }
        return true;
    }
}
