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
    
}
