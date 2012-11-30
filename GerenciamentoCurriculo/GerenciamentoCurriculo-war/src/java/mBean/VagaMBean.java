/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mBean;

import entidade.AreaAtuacao;
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
@ManagedBean(name = "VagaMBean")
@RequestScoped
public class VagaMBean {

    @EJB
    private VagaFacade vagaFacade;
    private Vaga vaga;
    private String descVaga;
    private Empresa empresa;
    private AreaAtuacao areaAtuacao;

    public AreaAtuacao getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

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

    public VagaMBean() {
        this.vaga = new Vaga();
        this.empresa = new Empresa();
        this.areaAtuacao = new AreaAtuacao();
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
                Empresa e = this.vagaFacade.findEmpresa(v.getEmpresa());
                if (e != null) {
                    this.empresa.setNome(e.getNome());
                }
            }
            this.vaga.setEmpresa(v.getEmpresa());
            this.vaga.setDescricao(v.getDescricao());
            if (v.getAreaatuacao() != null) {
                AreaAtuacao a = this.vagaFacade.findAreaAtuacao(v.getAreaatuacao());
                if (a != null) {
                    this.areaAtuacao.setDescricao(a.getDescricao());
                }
            }
            this.vaga.setLocalizacao(v.getLocalizacao());
            this.vaga.setAreaatuacao(v.getAreaatuacao());
            this.vaga.setRequisitos(v.getRequisitos());
            this.vaga.setSalario(v.getSalario());
            this.vaga.setTitulo(v.getTitulo());
        }

        return "visualizaVaga";
    }
}
