/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ExperienciaAnterior;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuca
 */
@Stateless
public class ExperienciaAnteriorFacade extends AbstractFacade<ExperienciaAnterior> {

    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienciaAnteriorFacade() {
        super(ExperienciaAnterior.class);
    }

    public List<ExperienciaAnterior> listExperiencia() {
        return (List<ExperienciaAnterior>) em.createQuery("SELECT OBJECT(e) FROM ExperienciaAnterior e").getResultList();
    }

    public List<ExperienciaAnterior> listExperienciaEmpresa(Long idEmpresa) {
        return (List<ExperienciaAnterior>) em.createQuery("SELECT OBJECT(e) FROM ExperienciaAnterior e where e.idEmpresa=" + idEmpresa).getResultList();
    }

    public List<ExperienciaAnterior> listExperienciaCandidatoEmpresa(Long idEmpresa, Long idCandidato) {
        return (List<ExperienciaAnterior>) em.createQuery("SELECT OBJECT(e) FROM ExperienciaAnterior e where e.idEmpresa=" + idEmpresa + " AND e.idCandidato=" + idCandidato).getResultList();
    }

    public List<Long> listExperienciaCandidatosEmpresa(Long idEmpresa) {
        return (List<Long>) em.createQuery("SELECT DISTINCT e.idCandidato FROM ExperienciaAnterior e where e.idEmpresa=" + idEmpresa).getResultList();
    }
    
    public List<ExperienciaAnterior> listExperienciaCandidato(Long idCandidato) {
        return (List<ExperienciaAnterior>) em.createQuery("SELECT OBJECT(e) FROM ExperienciaAnterior e WHERE e.idCandidato=" + idCandidato + "AND e.flagAprovado='S'").getResultList();
    }
    
     public ExperienciaAnterior findExperiencia(Long id) {
        return (ExperienciaAnterior) em.createQuery("SELECT OBJECT(e) FROM ExperienciaAnterior e WHERE e.id=" + id).getSingleResult();
    }
}
