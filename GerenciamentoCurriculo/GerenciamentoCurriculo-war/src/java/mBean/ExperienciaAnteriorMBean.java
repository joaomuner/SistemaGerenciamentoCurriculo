/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mBean;

import entidade.Candidato;
import entidade.ExperienciaAnterior;
import facade.ExperienciaAnteriorFacade;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tuca
 */
@ManagedBean(name = "ExperienciaAnteriorMBean")
@RequestScoped
public class ExperienciaAnteriorMBean {

    @EJB
    private ExperienciaAnteriorFacade experienciaFacade;
    private ExperienciaAnterior experiencia;
    
    public ExperienciaAnterior getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaAnterior experiencia) {
        this.experiencia = experiencia;
    }

    public ExperienciaAnteriorFacade getExperienciaFacade() {
        return experienciaFacade;
    }

    public void setExperienciaFacade(ExperienciaAnteriorFacade experienciaFacade) {
        this.experienciaFacade = experienciaFacade;
    }

    public ExperienciaAnteriorMBean() {
        this.experiencia = new ExperienciaAnterior();
    }

    public String inserirExperiencia() {
        Long idCandidato = Long.parseLong("0");
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        if (session != null) {
            idCandidato = (Long) session.getAttribute("idCandidato");
        }

        this.experiencia.setFlagAprovado("N");
        System.out.println("IdCandidato logado:" + idCandidato);
        this.experiencia.setIdCandidato(idCandidato);
        this.experienciaFacade.create(experiencia);
     
        return "experienciaSucesso";
    }
}
