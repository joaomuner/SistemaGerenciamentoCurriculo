/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.Temp;
import entidade.Vaga;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface VagaInterface {
    public void inserir (Vaga vaga);
    public Vaga procurarVaga (Long id);
    public List<Vaga> listarVaga();
    public List<Vaga> listarVagaEmpresa(Long idEmpresa);
}
