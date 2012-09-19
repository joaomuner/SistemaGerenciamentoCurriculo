/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;


/**
 *
 * @author Jota
 */
@Entity

public class Candidato implements Serializable {
    private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String bairro;
    private String cidade;
    private BigInteger CEP;
    private String sexo;
    private BigInteger RG;
    private BigInteger CPF;
    private String datanascimento;
    private String senha;
    
    private String areaatuacao;
    private String grauensino;
    private String institutoensino;
    private String objetivoprofissional;
    private double salario;
    private String perfilprofissional;
    private String conclusao;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public BigInteger getCEP() {
        return CEP;
    }

    public void setCEP(BigInteger CEP) {
        this.CEP = CEP;
    }

    public BigInteger getCPF() {
        return CPF;
    }

    public void setCPF(BigInteger CPF) {
        this.CPF = CPF;
    }

    public BigInteger getRG() {
        return RG;
    }

    public void setRG(BigInteger RG) {
        this.RG = RG;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAreaatuacao() {
        return areaatuacao;
    }

    public void setAreaatuacao(String areaatuacao) {
        this.areaatuacao = areaatuacao;
    }

    public String getGrauensino() {
        return grauensino;
    }

    public void setGrauensino(String grauensino) {
        this.grauensino = grauensino;
    }

    public String getInstitutoensino() {
        return institutoensino;
    }

    public void setInstitutoensino(String institutoensino) {
        this.institutoensino = institutoensino;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getObjetivoprofissional() {
        return objetivoprofissional;
    }

    public void setObjetivoprofissional(String objetivoprofissional) {
        this.objetivoprofissional = objetivoprofissional;
    }

    public String getPerfilprofissional() {
        return perfilprofissional;
    }

    public void setPerfilprofissional(String perfilprofissional) {
        this.perfilprofissional = perfilprofissional;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Candidato[ id=" + id + " ]";
    }
    
}
