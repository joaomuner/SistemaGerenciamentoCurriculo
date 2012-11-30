/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.Empresa;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface EmpresaInterface {
    public void inserir (Empresa empresa);
    public List<Empresa> listarEmpresa ();
    public Empresa procurarEmpresa (Long id);
    public Empresa procurarEmpresaEmailSenha (String email, String senha);
    public Empresa procurarEmpresaEmail (String email);
    public void atualizar (Empresa empresa);
}
