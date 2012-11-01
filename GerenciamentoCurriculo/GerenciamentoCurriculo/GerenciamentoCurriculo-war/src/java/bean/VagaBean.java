/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Empresa;
import entidade.Vaga;
import facade.VagaFacade;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

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
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

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
        this.empresa = new Empresa();
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
            this.vaga.setId(id);
             if (v.getEmpresa() != null) {
                 System.out.println("ID EMRPESA DIFERENTE DE NULL - BEAN" + v.getEmpresa());
                Empresa e = this.vagaFacade.findEmpresa(Long.parseLong(v.getEmpresa()));
                if (e != null) {
                    System.out.println("EMRPESA DIFERENTE DE NULL - BEAN 2 e!=null");
                    this.empresa.setNome(e.getNome());
                    this.empresa.setEndereco(e.getEndereco());
                }
            }
            this.vaga.setEmpresa(v.getEmpresa());
            this.vaga.setDescricao(v.getDescricao());
            this.vaga.setAreaatuacao(v.getAreaatuacao());
            this.vaga.setRequisitos(v.getRequisitos());
            this.vaga.setSalario(v.getSalario());
            this.vaga.setTitulo(v.getTitulo());
        }

        return "visualizaVaga";
    }
}
