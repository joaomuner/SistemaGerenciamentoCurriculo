/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.Universidade;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface UniversidadeInterface {
    
    public void inserir (Universidade universidade);
    public List<Universidade> listarUniversidade ();
    public Universidade procurarUniversidade (Long id);
    public void atualizar (Universidade universidade);
}
