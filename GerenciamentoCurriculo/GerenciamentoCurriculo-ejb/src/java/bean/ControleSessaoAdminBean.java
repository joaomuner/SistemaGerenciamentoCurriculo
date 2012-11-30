/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Administrador;
import interfaces.ControleSessaoInterface;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author tuca
 */
@Singleton(mappedName = "ControleSessaoAdminBean")
public class ControleSessaoAdminBean implements ControleSessaoInterface<Administrador> {

    @EJB
    private static ControleSessaoAdminBean instance = null;
    private Administrador administrador;
   
    public static ControleSessaoAdminBean getInstance() {
        if (instance == null) {
            instance = new ControleSessaoAdminBean();
        }
        return instance;
    }

    @Override
    public void setUsuario(Administrador entity) {
        this.administrador = entity;
    }

    @Override
    public Administrador getUsuario() {
        return administrador;
    }

    @Override
    public void encerrarSessao() {
        instance = null;
    }
}
