/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mBean;

import entidade.*;
import facade.CandidatoFacade;
import facade.EmpresaFacade;
import facade.ExperienciaAnteriorFacade;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javassist.expr.Cast;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jota
 */
@ManagedBean(name = "CandidatoMBean")
@RequestScoped
public class CandidatoMBean {

    @EJB
    private CandidatoFacade candidatoFacade;
    private Candidato candidato;
    private Empresa empresa;
    private AreaAtuacao areaAtuacao;
    private Universidade universidade;
    private ExperienciaAnterior experiencia;
    private ExperienciaAnteriorFacade experienciaFacade;

    public CandidatoMBean() {
        System.out.println("Criou um novo candidato!");
        this.candidato = new Candidato();
        this.empresa = new Empresa();
        this.areaAtuacao = new AreaAtuacao();
        this.universidade = new Universidade();
        this.experiencia = new ExperienciaAnterior();
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

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
    private List<Candidato> listaCandidato;

    public String criaCandidato() {
        if (ValidaCandidato()) {
            this.candidatoFacade.create(candidato);
            return "confirmacao";
        }
        return "usuarioRegistrado";
    }

    public String editarCandidato() {
        Candidato c = carregarCandidato();
        System.out.println("editando o candidato:" + c.getId());
        //Setta as variveis do candidato com os valores eu está no candidato encontrado
        if (c != null) {
            c.setCPF(candidato.getCPF());
            c.setRG(candidato.getRG());
            c.setTelefone(candidato.getTelefone());
            c.setRG(candidato.getRG());
            c.setDatanascimento(candidato.getDatanascimento());
            c.setEndereco(candidato.getEndereco());
            c.setBairro(candidato.getBairro());
            c.setCidade(candidato.getCidade());
            c.setCEP(candidato.getCEP());
            c.setSexo(candidato.getSexo());
            c.setPerfilprofissional(candidato.getPerfilprofissional());
            c.setSalario(candidato.getSalario());
            c.setAreaatuacao(candidato.getAreaatuacao());
            c.setObjetivoprofissional(candidato.getObjetivoprofissional());
            c.setGrauensino(candidato.getGrauensino());
            c.setInstitutoensino(candidato.getInstitutoensino());
            c.setConclusao(candidato.getConclusao());
        }
        this.candidatoFacade.edit(c);
        return "alteracaoSucesso";
    }

    public String novaExperiencia() {
        return "novaExperiencia";
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public CandidatoFacade getCandidatoFacade() {
        return candidatoFacade;
    }

    public void setCandidatoFacade(CandidatoFacade candidatoFacade) {
        this.candidatoFacade = candidatoFacade;
    }

    public List<Candidato> getListaCandidato() {
        if (listaCandidato == null) {
            listaCandidato = new ArrayList<Candidato>();
        }

        return listaCandidato;
    }

    public void setListaCandidato(List<Candidato> listaCandidato) {
        this.listaCandidato = listaCandidato;
    }

    private Candidato carregarCandidato() {
        Long g = Long.parseLong("0");
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        if (session != null) {
            g = (Long) session.getAttribute("idCandidato");
            System.out.println("Candidato c ID logado=" + g);
        }

        return this.candidatoFacade.findCandidatoLogado(g);
    }

    public String carregarCurriculo() {
        System.out.println("Carregando Currículo");
        //Procura o candidato logado
        Candidato c = carregarCandidato();
        if (c != null) {
            this.candidato = c;
            System.out.println("IdCandidato: " + candidato.getId());
        }
        return "visualizaCurriculo";
    }

    public boolean ValidaCandidato() {
        Candidato c = this.candidatoFacade.findCandidatoEmail(candidato.getEmail());
        if (c != null) {
            return false;
        }
        return true;
    }

    public List<Temp> listarExperiencias() {
        Candidato c = carregarCandidato();
        return this.candidatoFacade.listExperiencia(c.getId());
    }
}
