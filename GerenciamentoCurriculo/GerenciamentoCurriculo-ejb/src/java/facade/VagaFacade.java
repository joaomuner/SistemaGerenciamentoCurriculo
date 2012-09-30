/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Temp;
import entidade.Vaga;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jota
 */
@Stateless
public class VagaFacade extends AbstractFacade<Vaga> {
    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VagaFacade() {
        super(Vaga.class);
    }
    
    public List<Temp> areaAtuacaoList() {
        return (List<Temp>) em.createQuery("SELECT NEW entidade.Temp(v.areaatuacao, count(v.id)) FROM Vaga v GROUP BY v.areaatuacao").getResultList();
    }
    
    public List<Vaga> listVagas() {
        return (List<Vaga>) em.createQuery("SELECT OBJECT(v) FROM Vaga v" ).getResultList();
    }
    
    public Vaga findVagas(Long id) {
        try {
            return (Vaga) em.createQuery("SELECT OBJECT(v) FROM Vaga v  WHERE  v.id=" + id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
