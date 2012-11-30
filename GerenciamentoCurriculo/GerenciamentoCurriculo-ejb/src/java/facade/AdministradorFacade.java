/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Administrador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuca
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> {
    @PersistenceContext(unitName = "GerenciamentoCurriculo-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
      public List<Administrador> listAdministrador() {
        return (List<Administrador>) em.createQuery("SELECT OBJECT(a) FROM Administrador a").getResultList();
    }
    
    public Administrador findAdministrador(Long id) {
        return (Administrador) em.createQuery("SELECT OBJECT(a) FROM Administrador a WHERE a.id=" + id).getSingleResult();
    }
    
     public Administrador findAdministradorEmailSenha(String email, String senha) {
        try {
            return (Administrador) em.createQuery("SELECT OBJECT(a) FROM Administrador a WHERE a.email='" + email + "' and a.senha='" + senha + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
     
      public Administrador findAdministradorEmail(String email) {
        try {
            return (Administrador) em.createQuery("SELECT OBJECT(a) FROM Administrador a WHERE a.email='" + email + "'").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
