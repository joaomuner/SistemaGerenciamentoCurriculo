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
public class ObjetivoProfissional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "DescricaoObjetivo", length = 200) 
    private String DescricaoObjetivo;
    @Column(name = "DescricaoPerfil", length = 200) 
    private String DescricaoPerfil;
    @Column(name = "PretensaoSalarial", length = 20) 
    private Float PretencaoSalarial;
    @Column(name = "OutraCidade", length = 20) 
    private String OutraCidade;
    @Column(name = "ViagemEmpresa", length = 20) 
    private String ViagemEmpresa;
    @Column(name = "Complemento", length = 100) 
    private String Complemento;

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public String getDescricaoObjetivo() {
        return DescricaoObjetivo;
    }

    public void setDescricaoObjetivo(String DescricaoObjetivo) {
        this.DescricaoObjetivo = DescricaoObjetivo;
    }

    public String getDescricaoPerfil() {
        return DescricaoPerfil;
    }

    public void setDescricaoPerfil(String DescricaoPerfil) {
        this.DescricaoPerfil = DescricaoPerfil;
    }

    public String getOutraCidade() {
        return OutraCidade;
    }

    public void setOutraCidade(String OutraCidade) {
        this.OutraCidade = OutraCidade;
    }

    public Float getPretencaoSalarial() {
        return PretencaoSalarial;
    }

    public void setPretencaoSalarial(Float PretencaoSalarial) {
        this.PretencaoSalarial = PretencaoSalarial;
    }

    public String getViagemEmpresa() {
        return ViagemEmpresa;
    }

    public void setViagemEmpresa(String ViagemEmpresa) {
        this.ViagemEmpresa = ViagemEmpresa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
        if (!(object instanceof ObjetivoProfissional)) {
            return false;
        }
        ObjetivoProfissional other = (ObjetivoProfissional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ObjetivoProfissional[ id=" + id + " ]";
    }
    
}
