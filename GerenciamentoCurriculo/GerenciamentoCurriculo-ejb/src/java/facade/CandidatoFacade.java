/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.*;
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

    public Candidato findCandidatoEmail(String email) {
        try {
            return (Candidato) em.createQuery("SELECT OBJECT(c) FROM Candidato c  WHERE  c.email='" + email + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Candidato findCandidatoLogado(Long id) {
        try {
            return (Candidato) em.createQuery("SELECT OBJECT(c) FROM Candidato c  WHERE  c.id=" + id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Empresa findEmpresa(Long id) {
        try {
            return (Empresa) em.createQuery("SELECT OBJECT(e) FROM Empresa e  WHERE  e.id=" + id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public AreaAtuacao findAreaAtuacao(Long id) {
        try {
            return (AreaAtuacao) em.createQuery("SELECT OBJECT(a) FROM AreaAtuacao a  WHERE  a.id=" + id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Universidade findUniversidade(Long id) {
        try {
            return (Universidade) em.createQuery("SELECT OBJECT(u) FROM Universidade u  WHERE  u.id=" + id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Temp> listExperiencia(long idCandidato) {
        return (List<Temp>) em.createQuery("SELECT NEW entidade.Temp(em.nome, e.descricaoAtv, e.flagAprovado) FROM ExperienciaAnterior e, Empresa em WHERE e.idEmpresa = em.id AND e.idCandidato=" + idCandidato).getResultList();
    }
}
