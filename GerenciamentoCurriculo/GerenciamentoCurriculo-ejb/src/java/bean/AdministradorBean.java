/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Administrador;
import facade.AdministradorFacade;
import interfaces.AdministradorInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "AdministradorBean")
public class AdministradorBean implements AdministradorInterface {

    @EJB
    public AdministradorFacade administradorFacade;
    public Administrador administrador;

    public AdministradorBean() {
        this.administrador = new Administrador();
    }

    @Override
    public void inserir(Administrador administrador) {
        this.administradorFacade.create(administrador);
    }

    @Override
    public List<Administrador> listarAdministrador() {
        return this.administradorFacade.listAdministrador();
    }

    @Override
    public Administrador procurarAdministrador(Long id) {
        return this.administradorFacade.find(id);
    }

    @Override
    public void atualizar(Administrador administrador) {
        this.administradorFacade.edit(administrador);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Administrador procurarAdministradorEmailSenha(String email, String senha) {
        return this.administradorFacade.findAdministradorEmailSenha(email, senha);
    }

    @Override
    public Administrador procurarAdministradorEmail(String email) {
        return this.administradorFacade.findAdministradorEmail(email);
    }
}
