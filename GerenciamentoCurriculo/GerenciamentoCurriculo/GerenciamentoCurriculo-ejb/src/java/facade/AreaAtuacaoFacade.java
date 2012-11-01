/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.AreaAtuacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuca
 */
@Stateless
public class AreaAtuacaoFacade extends AbstractFacade<AreaAtuacao> {
    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaAtuacaoFacade() {
        super(AreaAtuacao.class);
    }
    
      public List<AreaAtuacao> listAreaAtuacao() {
        return (List<AreaAtuacao>) em.createQuery("SELECT OBJECT(a) FROM AreaAtuacao a" ).getResultList();
    } 
    
}
