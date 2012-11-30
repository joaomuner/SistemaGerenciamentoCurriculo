/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuca
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }

    public List<Empresa> listEmpresas() {
        return (List<Empresa>) em.createQuery("SELECT OBJECT(e) FROM Empresa e").getResultList();
    }

    public Empresa findEmpresa(Long id) {
        return (Empresa) em.createQuery("SELECT OBJECT(e) FROM Empresa e WHERE e.id=" + id).getSingleResult();
    }

    public Empresa findEmpresaEmailSenha(String email, String senha) {
        try {
            return (Empresa) em.createQuery("SELECT OBJECT(e) FROM Empresa e WHERE e.email='" + email + "' and e.senha='" + senha + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
     public Empresa findEmpresaEmail(String email) {
        try {
            return (Empresa) em.createQuery("SELECT OBJECT(e) FROM Empresa e WHERE e.email='" + email + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
