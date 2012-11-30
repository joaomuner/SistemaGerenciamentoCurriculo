/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.AreaAtuacao;
import entidade.Empresa;
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
        return (List<Temp>) em.createQuery("SELECT NEW entidade.Temp(a.descricao, count(v.id)) FROM AreaAtuacao a, Vaga v WHERE a.id = v.areaatuacao GROUP BY a.descricao").getResultList();
    }

    public List<Vaga> listVagas() {
        return (List<Vaga>) em.createQuery("SELECT OBJECT(v) FROM Vaga v").getResultList();
    }

    public Vaga findVagas(Long id) {
        try {
            return (Vaga) em.createQuery("SELECT OBJECT(v) FROM Vaga v  WHERE  v.id=" + id).getSingleResult();
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

    public List<Vaga> listVagasEmpresa(Long idEmpresa) {
        return (List<Vaga>) em.createQuery("SELECT OBJECT(v) FROM Vaga v WHERE v.empresa=" + idEmpresa).getResultList();
    }
}
