/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jota
 */
@Entity
@Table(name = "CANDIDATO")
@NamedQueries({
    @NamedQuery(name = "Candidato.findAll", query = "SELECT c FROM Candidato c")
})
public class Candidato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdCandidato")
    private Long IdCandidato;
    @Column(name = "IdObjetivoProffisional")
    private Long IdObjetivoProfisional;
    @Size(max = 250)
    @Column(name = "Nome")
    private String nome;
    @Size(max = 250)
    @Column(name = "Endereco")
    private String endereco;
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @Size(max = 100)
    @Column(name = "Bairro")
    private String bairro;
    @Size(max = 100)
    @Column(name = "Cidade")
    private String cidade;
    @Column(name = "CEP")
    private BigInteger cep;
    @Column(name = "Telefone")
    private BigInteger telefone;
    @Size(max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "CPF")
    private BigDecimal cpf;
    @Column(name = "RG")
    private BigInteger rg;
    @Column(name = "DataNascimento")
    private BigInteger datanascimento;

     public Candidato() {
    }

    public Candidato(Long IdCandidato) {
        this.IdCandidato = IdCandidato;
    }

    public Candidato(Long IdCandidato, Long IdObjetivoProfisional) {
        this.IdCandidato = IdCandidato;
        this.IdObjetivoProfisional = IdObjetivoProfisional;
    }
    
    public Long getIdCandidato() {
        return IdCandidato;
    }

    public void setIdCandidato(Long IdCandidato) {
        this.IdCandidato = IdCandidato;
    }

    public Long getIdObjetivoProfisional() {
        return IdObjetivoProfisional;
    }

    public void setIdObjetivoProfisional(Long IdObjetivoProffisional) {
        this.IdObjetivoProfisional = IdObjetivoProffisional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public BigInteger getCep() {
        return cep;
    }

    public void setCep(BigInteger cep) {
        this.cep = cep;
    }

    public BigInteger getTelefone() {
        return telefone;
    }

    public void setTelefone(BigInteger telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigDecimal getCpf() {
        return cpf;
    }

    public void setCpf(BigDecimal cpf) {
        this.cpf = cpf;
    }

    public BigInteger getRg() {
        return rg;
    }

    public void setRg(BigInteger rg) {
        this.rg = rg;
    }

    public BigInteger getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(BigInteger datanascimento) {
        this.datanascimento = datanascimento;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IdCandidato != null ? IdCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.IdCandidato == null && other.IdCandidato != null) || (this.IdCandidato != null && !this.IdCandidato.equals(other.IdCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Candidato[ id=" + IdCandidato + " ]";
    }
    
}
