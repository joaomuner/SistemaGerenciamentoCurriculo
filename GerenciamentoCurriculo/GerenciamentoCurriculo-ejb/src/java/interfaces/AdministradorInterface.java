/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.Administrador;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface AdministradorInterface {

    public void inserir(Administrador administrador);
    public List<Administrador> listarAdministrador();
    public Administrador procurarAdministrador(Long id);
    public void atualizar(Administrador administrador);
    public Administrador procurarAdministradorEmailSenha (String email, String senha);
    public Administrador procurarAdministradorEmail (String email);
}
