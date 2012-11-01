package conexao.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Anotação que informa que a classe mapeada é persistente
@Entity
//Informando nome e esquema da tabela mapeada
@Table(name="empresa", schema="anotacoes")



public class Empresa  implements java.io.Serializable  {
//Definição da chave primária
@Id
//Definição do mecanismo de definição da chave primária
@GeneratedValue(strategy = GenerationType.SEQUENCE)
//Informa o nome da coluna mapeada para o atributo
@Column(name="idEmpresa")
     

     private Long id;
     private String nome;
     private String descricao;
     private String endereco;
     private String telefone;
     private String email;

    public Empresa() {
    }

	
    public Empresa(Long id) {
        this.id = id;
    }
    public Empresa(String nome, String descricao, String endereco, String telefone, String email) {
       this.nome = nome;
       this.descricao = descricao;
       this.endereco = endereco;
       this.telefone = telefone;
       this.email = email;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


