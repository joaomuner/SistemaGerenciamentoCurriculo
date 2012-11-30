/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import entidade.CandidatoVaga;
import facade.CandidatoVagaFacade;
import interfaces.CandidatoVagaInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "CandidatoVagaBean")
public class CandidatoVagaBean implements CandidatoVagaInterface {

    @EJB
    public CandidatoVagaFacade candidatoVagaFacade;
    public CandidatoVaga candidatoVaga;
    
    @Override
    public List<CandidatoVaga> listarCandidatoVaga(Long idVaga) {
        return this.candidatoVagaFacade.findCandidatosVaga(idVaga);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
