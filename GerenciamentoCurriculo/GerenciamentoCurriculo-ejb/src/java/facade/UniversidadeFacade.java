/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Universidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuca
 */
@Stateless
public class UniversidadeFacade extends AbstractFacade<Universidade> {
    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UniversidadeFacade() {
        super(Universidade.class);
    }
    
    public List<Universidade> listUniversidade() {
        return (List<Universidade>) em.createQuery("SELECT OBJECT(u) FROM Universidade u" ).getResultList();
    } 
    
     public Universidade findUniversidade(Long id) {
        return (Universidade) em.createQuery("SELECT OBJECT(u) FROM Universidade u WHERE u.id=" + id).getSingleResult();
    }
}
