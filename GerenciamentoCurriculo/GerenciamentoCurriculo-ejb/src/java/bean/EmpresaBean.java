/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Empresa;
import facade.EmpresaFacade;
import interfaces.EmpresaInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "EmpresaBean")
public class EmpresaBean implements EmpresaInterface {

    @EJB
    public EmpresaFacade empresaFacade;
    public Empresa empresa;
    
    public EmpresaBean(){
        this.empresa = new Empresa();
    }
    
    @Override
    public void inserir(Empresa empresa) {
        this.empresaFacade.create(empresa);
    }

    @Override
    public List<Empresa> listarEmpresa() {
        return this.empresaFacade.listEmpresas();
    }

    @Override
    public Empresa procurarEmpresa(Long id) {
        return this.empresaFacade.findEmpresa(id);
    }

    @Override
    public void atualizar(Empresa empresa) {
        this.empresaFacade.edit(empresa);
    }

    @Override
    public Empresa procurarEmpresaEmailSenha(String email, String senha) {
        return this.empresaFacade.findEmpresaEmailSenha(email, senha);
    }

    @Override
    public Empresa procurarEmpresaEmail(String email) {
        return this.empresaFacade.findEmpresaEmail(email);
    }
}
