/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.CandidatoVaga;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuca
 */
@Stateless
public class CandidatoVagaFacade extends AbstractFacade<CandidatoVaga> {
    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatoVagaFacade() {
        super(CandidatoVaga.class);
    }
    
    public CandidatoVaga findCandidatoVaga(Long idCandidato, Long idVaga) {
        try {
            return (CandidatoVaga) em.createQuery("SELECT OBJECT(cv) FROM CandidatoVaga cv  WHERE  cv.idCandidato=" + idCandidato + " AND cv.idVaga=" + idVaga).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<CandidatoVaga> findCandidatosVaga(Long idVaga) {
        try {
            return (List<CandidatoVaga>) em.createQuery("SELECT OBJECT(cv) FROM CandidatoVaga cv  WHERE cv.idVaga=" + idVaga).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
