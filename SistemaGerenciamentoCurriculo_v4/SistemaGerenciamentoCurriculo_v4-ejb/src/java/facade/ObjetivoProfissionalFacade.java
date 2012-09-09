/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ObjetivoProfissional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jota
 */
@Stateless
public class ObjetivoProfissionalFacade extends AbstractFacade<ObjetivoProfissional> {
    @PersistenceContext(unitName = "SistemaGerenciamentoCurriculo_v4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoProfissionalFacade() {
        super(ObjetivoProfissional.class);
    }
    
}
