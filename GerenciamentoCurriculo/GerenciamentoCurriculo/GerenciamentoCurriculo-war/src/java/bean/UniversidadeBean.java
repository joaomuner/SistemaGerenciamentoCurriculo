/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Universidade;
import facade.UniversidadeFacade;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tuca
 */
@ManagedBean
@RequestScoped
public class UniversidadeBean {

     @EJB
    private UniversidadeFacade universidadeFacade;
    private Universidade universidade;
    private Map universidadeItem = null;
    private ListDataModel model;

    public UniversidadeBean() {
    }     
    
    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    public UniversidadeFacade getUniversidadeFacade() {
        return universidadeFacade;
    }

    public void setUniversidadeFacade(UniversidadeFacade universidadeFacade) {
        this.universidadeFacade = universidadeFacade;
    }
    
     public Map getUniversidades() {
        List<Universidade> lstEmpresas = this.universidadeFacade.listUniversidade();
        universidadeItem = new LinkedHashMap();
        for (Iterator iter = lstEmpresas.iterator(); iter.hasNext();) {
            Universidade e = (Universidade) iter.next();
            //armazena as universidades encontradas no bd em um map
            universidadeItem.put(e.getNome(), e.getId());
        }
        return universidadeItem;
    }
}
