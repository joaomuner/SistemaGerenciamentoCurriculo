/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Candidato;
import entidade.Temp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jota
 */
@Stateless
public class CandidatoFacade extends AbstractFacade<Candidato> {

    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatoFacade() {
        super(Candidato.class);
    }

    public Candidato findCandidatoEmail(String email, String senha) {
        try {
            return (Candidato) em.createQuery("SELECT OBJECT(c) FROM Candidato c  WHERE  c.email='" + email + "' AND c.senha='" + senha + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Temp> candidatoList() {
        return (List<Temp>) em.createQuery("SELECT NEW entidade.Temp(c.areaatuacao, count(c.id)) FROM Candidato c GROUP BY c.areaatuacao").getResultList();
    }


}
