/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.*;
import facade.CandidatoFacade;
import facade.EmpresaFacade;
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
@ManagedBean(name = "CandidatoBean")
@RequestScoped
public class CandidatoBean {

    @EJB
    private CandidatoFacade candidatoFacade;
    private Candidato candidato;
    private Empresa empresa;
    private AreaAtuacao areaAtuacao;
    private Universidade universidade;

    public CandidatoBean() {
        this.candidato = new Candidato();
        this.empresa = new Empresa();
        this.areaAtuacao = new AreaAtuacao();
        this.universidade = new Universidade();
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

    public String carregarCurriculo() {
        //Procura o candidato logado
        Long g = Long.parseLong("0");
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        if (session != null) {
            g = (Long) session.getAttribute("idCandidato");
        }

        Candidato c = this.candidatoFacade.findCandidatoLogado(g);

        //Setta as variveis do candidato com os valores eu está no candidato encontrado
        if (c != null) {
            this.candidato.setNome(c.getNome());
            this.candidato.setEmail(c.getEmail());
            this.candidato.setCPF(c.getCPF());
            this.candidato.setTelefone(c.getTelefone());
            this.candidato.setRG(c.getRG());
            this.candidato.setDatanascimento(c.getDatanascimento());
            this.candidato.setEndereco(c.getEndereco());
            this.candidato.setBairro(c.getBairro());
            this.candidato.setCidade(c.getCidade());
            this.candidato.setCEP(c.getCEP());
            this.candidato.setSexo(c.getSexo());
            this.candidato.setPerfilprofissional(c.getPerfilprofissional());
            this.candidato.setSalario(c.getSalario());

            if (c.getAreaatuacao() != null) {
                AreaAtuacao a = this.candidatoFacade.findAreaAtuacao(Long.parseLong(c.getAreaatuacao()));
                if (a != null) {
                    this.areaAtuacao.setDescricao(a.getDescricao());
                }
            }

            this.candidato.setObjetivoprofissional(c.getObjetivoprofissional());
            this.candidato.setGrauensino(c.getGrauensino());
            
            if (c.getInstitutoensino() != null) {
                Universidade u = this.candidatoFacade.findUniversidade(Long.parseLong(c.getInstitutoensino()));
                if (u != null) {
                    this.universidade.setNome(u.getNome());
                }
            }
            this.candidato.setInstitutoensino(c.getInstitutoensino());
            this.candidato.setConclusao(c.getConclusao());

            if (c.getEmpresa() != null) {
                Empresa e = this.candidatoFacade.findEmpresa(Long.parseLong(c.getEmpresa()));
                if (e != null) {
                    this.empresa.setNome(e.getNome());
                }
            }

            this.candidato.setAreaatuacaoexperiencia(c.getAreaatuacaoexperiencia());
            this.candidato.setSalarioexperiencia(c.getSalarioexperiencia());
            this.candidato.setDatainicio(c.getDatainicio());
            this.candidato.setDatafinal(c.getDatafinal());
            this.candidato.setDescatividade(c.getDescatividade());
            this.candidato.setBeneficios(c.getBeneficios());
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
}
