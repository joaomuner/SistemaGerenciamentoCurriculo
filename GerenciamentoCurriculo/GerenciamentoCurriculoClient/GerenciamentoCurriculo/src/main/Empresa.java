/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Jota
 */
public class Empresa {
    int idEmpresa;
    String Nome,Descricao,Endereco,Telefone,Email;

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa() {
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + ", Nome=" + Nome + ", Descricao=" + Descricao + ", Endereco=" + Endereco + ", Telefone=" + Telefone + ", Email=" + Email + '}';
    }
    
    
}
