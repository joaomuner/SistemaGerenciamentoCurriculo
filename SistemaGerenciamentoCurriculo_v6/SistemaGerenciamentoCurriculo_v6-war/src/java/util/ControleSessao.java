/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tuca
 */
@ManagedBean(name = "ControleSessao")
@RequestScoped
public class ControleSessao {

    @EJB
    private String email;

    /**
     * @return the email
     */
    public ControleSessao() {
    }

    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String criaSession() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        session.setAttribute("email", email);
        return "form";
    }

    public String retornaEmailSession() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        return (String) session.getAttribute("email");
    }
}
