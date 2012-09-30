/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Candidato;
import entidade.Temp;
import facade.CandidatoFacade;
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
    private Candidato cadidato;
    private List<Candidato> listaCandidato;
    private ControleSessaoBean controleSessao;

    /**
     * Creates a new instance of CandidatoBean
     */
    public CandidatoBean() {
        this.cadidato = new Candidato();

    }
//------------------------------------------------------------------------------------------

    public String novo() {
        return "form";
    }

    public String index() {
        return "index";
    }

    public String confirmacao() {
        return "confirmacao";
    }

    public int NomeCandidato() {
        return candidatoFacade.findAll().size();
    }

    public String criaCandidato() {
        this.candidatoFacade.create(cadidato);
        // listaCandidato = candidatoFacade.findAll();
        return "confirmacao";
    }

    public String verLista() {
        listaCandidato = candidatoFacade.findAll();
        return "lista";
    }
//------------------------------------------------------------------------------------------

    public Candidato getCadidato() {
        return cadidato;
    }

    public void setCadidato(Candidato cadidato) {
        this.cadidato = cadidato;
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
            System.out.println("Passou aqui Controle Sessão Bean!!\n\n\n\n\n ID" + (Long)session.getAttribute("idCandidato"));
            g = (Long) session.getAttribute("idCandidato");
        }
       
        Candidato c = this.candidatoFacade.findCandidatoLogado(g);
        //Agora eu vou ter que setar as nossas variveis do candidato os valores eu está no Nossa candidato encontrado
        //Entao farecmos assim
        if (c != null) {
            this.cadidato.setNome(c.getNome());
            this.cadidato.setEmail(c.getEmail());
            this.cadidato.setCPF(c.getCPF());
            this.cadidato.setTelefone(c.getTelefone());
            this.cadidato.setRG(c.getRG());
            this.cadidato.setDatanascimento(c.getDatanascimento());
            this.cadidato.setEndereco(c.getEndereco());
            this.cadidato.setBairro(c.getBairro());
            this.cadidato.setCidade(c.getCidade());
            this.cadidato.setCEP(c.getCEP());
            this.cadidato.setSexo(c.getSexo());
            this.cadidato.setPerfilprofissional(c.getPerfilprofissional());
            this.cadidato.setSalario(c.getSalario());
            this.cadidato.setAreaatuacao(c.getAreaatuacao());
            this.cadidato.setObjetivoprofissional(c.getObjetivoprofissional());
            this.cadidato.setGrauensino(c.getGrauensino());
            this.cadidato.setInstitutoensino(c.getInstitutoensino());
            this.cadidato.setConclusao(c.getConclusao());
            
            this.cadidato.setEmpresa(c.getEmpresa());
            this.cadidato.setAreaatuacaoexperiencia(c.getAreaatuacaoexperiencia());
            this.cadidato.setSalarioexperiencia(c.getSalarioexperiencia());
            this.cadidato.setDatainicio(c.getDatainicio());
            this.cadidato.setDatafinal(c.getDatafinal());
            this.cadidato.setDescatividade(c.getDescatividade());
            this.cadidato.setBeneficios(c.getBeneficios());
            
            
        }

        return "visualizaCurriculo";
    }
}
