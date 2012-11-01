/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Candidato;
import facade.CandidatoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jota
 */
@ManagedBean(name = "ControleSessaoBean")
@RequestScoped
public class ControleSessaoBean {

    @EJB
    private CandidatoFacade candidatoFacade;
    private String email;
    private String senha;

    public String validarUsuario() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        Candidato c = this.candidatoFacade.findCandidatoEmail(email, senha);
        if (c != null) {
            session.setAttribute("nome", c.getNome());
            session.setAttribute("idCandidato", c.getId());
            return "index";
        }

        return "usuarioNaoEncontrado";
    }

    public String sair() {
        //Contexto da Aplicação  
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        //Encerra a sessao
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }

    public String getNomeUsuarioLogado() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        //Se não houver sessao retorna EMPTY!
        if (session != null) {
            return (String) session.getAttribute("nome");
        }
        return "";
    }

    public Long getIdUsuarioLogado() {

        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        if (session != null) {
            return (Long) session.getAttribute("idCandidato");
        } else {
            return null;
        }

    }

    public boolean getUsuarioLogado() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        if (session != null && getIdUsuarioLogado() != null) {
            return true;
        }
        return false;
    }

    public String login() {
        if (getUsuarioLogado()) {
            return "usuarioLogado";
        }
        return "login";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
