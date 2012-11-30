/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mBean;

import entidade.AreaAtuacao;
import facade.AreaAtuacaoFacade;
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
@ManagedBean(name = "AreaAtuacaoMBean")
@RequestScoped
public class AreaAtuacaoMBean  {

    @EJB
    private AreaAtuacaoFacade areaAtuacaoFacade;
    private AreaAtuacao areaAtuacao;
    private Map areaAtuacaoItem = null;
    private ListDataModel model;

    public AreaAtuacaoMBean() {
    }

    public AreaAtuacao getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public AreaAtuacaoFacade getAreaAtuacaoFacade() {
        return areaAtuacaoFacade;
    }

    public void setAreaAtuacaoFacade(AreaAtuacaoFacade areaAtuacaoFacade) {
        this.areaAtuacaoFacade = areaAtuacaoFacade;
    }

    public Map getAreasAtuacao() {
        List<AreaAtuacao> lstEmpresas = this.areaAtuacaoFacade.listAreaAtuacao();
        areaAtuacaoItem = new LinkedHashMap();
        for (Iterator iter = lstEmpresas.iterator(); iter.hasNext();) {
            AreaAtuacao a = (AreaAtuacao) iter.next();
            //armazena as areas de atuacao encontradas no bd em um map
            areaAtuacaoItem.put(a.getDescricao(), a.getId());
        }
        return areaAtuacaoItem;
    }
}
