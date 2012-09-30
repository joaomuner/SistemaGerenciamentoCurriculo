/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Vaga;
import facade.VagaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jota
 */
@ManagedBean(name = "VagaBean")
@RequestScoped
public class VagaBean {

    @EJB
    private VagaFacade vagaFacade;
    private Vaga vaga;
    private String descVaga;

    public String getDescVaga() {
        return descVaga;
    }

    public void setDescVaga(String descVaga) {
        this.descVaga = descVaga;
    }

    public VagaFacade getVagaFacade() {
        return vagaFacade;
    }

    public void setVagaFacade(VagaFacade vagaFacade) {
        this.vagaFacade = vagaFacade;
    }

    public VagaBean() {
        this.vaga = new Vaga();
    }

    public String insereVaga() {
        this.vagaFacade.create(vaga);
        return "confirmacao";
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public List<Vaga> buscarVagas() {
        return this.vagaFacade.listVagas();
    }
    
    public String carregarDetalheVaga() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String idStr = (String) ctx.getExternalContext().getRequestParameterMap().get("idRow");
        Long id = Long.parseLong(idStr);
        Vaga v = this.vagaFacade.findVagas(id);
        if (v != null) {
            this.vaga.setEmpresa(v.getEmpresa());
            this.vaga.setDescricao(v.getDescricao());
            this.vaga.setAreaatuacao(v.getAreaatuacao());
            this.vaga.setLocalizacao(v.getLocalizacao());
            this.vaga.setRequisitos(v.getRequisitos());
            this.vaga.setSalario(v.getSalario());
            this.vaga.setTitulo(v.getTitulo());
        }

        return "visualizaVaga";
    }
}
