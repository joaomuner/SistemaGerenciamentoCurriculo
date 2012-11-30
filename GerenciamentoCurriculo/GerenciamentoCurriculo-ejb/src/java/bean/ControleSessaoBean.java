/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Empresa;
import interfaces.ControleSessaoInterface;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author tuca
 */
@Singleton(mappedName = "ControleSessaoBean")
public class ControleSessaoBean implements ControleSessaoInterface<Empresa> {

    @EJB
    private static ControleSessaoBean instance = null;
    private Empresa empresa;
   
    public static ControleSessaoBean getInstance() {
        if (instance == null) {
            instance = new ControleSessaoBean();
        }
        return instance;
    }

    @Override
    public void setUsuario(Empresa entity) {
        this.empresa = entity;
    }

    @Override
    public Empresa getUsuario() {
        return empresa;
    }

    @Override
    public void encerrarSessao() {
        instance = null;
    }

}