/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.CandidatoVaga;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface CandidatoVagaInterface {
    public List<CandidatoVaga> listarCandidatoVaga(Long idVaga);
}
