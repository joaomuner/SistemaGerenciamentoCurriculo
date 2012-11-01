/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Empresa;
import facade.EmpresaFacade;
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
@ManagedBean(name = "EmpresaBean")
@RequestScoped
public class EmpresaBean {
    
    @EJB
    private EmpresaFacade empresaFacade;
    private Empresa empresa;
    private Map empresaItem = null;
    private ListDataModel model;

     public EmpresaBean() {
    }
     
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EmpresaFacade getEmpresaFacade() {
        return empresaFacade;
    }

    public void setEmpresaFacade(EmpresaFacade empresaFacade) {
        this.empresaFacade = empresaFacade;
    }
      
    public Map getEmpresas() {
        List<Empresa> lstEmpresas = this.empresaFacade.listVagas();
        empresaItem = new LinkedHashMap();
        for (Iterator iter = lstEmpresas.iterator(); iter.hasNext();) {
            Empresa e = (Empresa) iter.next();
            //armazena as empresas encontradas no bd em um map
            empresaItem.put(e.getNome(), e.getId());
        }
        return empresaItem;
    }
}
