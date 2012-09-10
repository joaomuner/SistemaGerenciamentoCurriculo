/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Candidato;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jota
 */
@Stateless
public class CandidatoFacade extends AbstractFacade<Candidato> {

    @PersistenceContext(unitName = "SistemaGerenciamentoCurriculo_v3-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatoFacade() {
        super(Candidato.class);
    }

    public Candidato findCandidatoEmail(String email) {
        try {
            return (Candidato) em.createQuery("SELECT OBJECT(c) FROM Candidato c  WHERE  c.email='" + email + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
