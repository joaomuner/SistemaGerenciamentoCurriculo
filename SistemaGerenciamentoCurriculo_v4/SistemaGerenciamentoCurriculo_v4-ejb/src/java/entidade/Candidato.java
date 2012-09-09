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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
       
    @Column(name = "nome", length = 100) 
    private String nome;
    @Column(name = "telefone", length = 30) 
    private String telefone;
    @Column(name = "email", length = 100) 
    private String email;
    @Column(name = "endereco", length = 100) 
    private String endereco;
    @Column(name = "bairro", length = 100) 
    private String bairro;
    @Column(name = "cidade", length = 100) 
    private String cidade;
    @Column(name = "CEP") 
    private BigInteger CEP;
    @Column(name = "sexo", length = 30) 
    private String sexo;
    @Column(name = "RG") 
    private BigInteger RG;
    @Column(name = "CPF") 
    private BigInteger CPF;

   
//    @JoinColumn(name = "ObjetivoProfissional_IdObjetivoProfissional", referencedColumnName = "IdObjetivoProfissional")  
//    @ManyToOne  
//    private ObjetivoProfissional Idobjetivopessoal;
//    
//    @JoinColumn(name = "AreaAtuacao_Id", referencedColumnName = "Id")  
//    @ManyToOne  
//    private AreaAtuacao Id;
    
    
    private String areaatuacao;
    private String grauensino;
    private String institutoensino;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
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

//    public AreaAtuacao getIdAreaAtuacao() {
//        return IdAreaAtuacao;
//    }
//
//    public void setIdAreaAtuacao(AreaAtuacao IdAreaAtuacao) {
//        this.IdAreaAtuacao = IdAreaAtuacao;
//    }
//
//    public ObjetivoProfissional getIdobjetivopessoal() {
//        return Idobjetivopessoal;
//    }
//
//    public void setIdobjetivopessoal(ObjetivoProfissional Idobjetivopessoal) {
//        this.Idobjetivopessoal = Idobjetivopessoal;
//    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Candidato[ id=" + Id + " ]";
    }
    
}
