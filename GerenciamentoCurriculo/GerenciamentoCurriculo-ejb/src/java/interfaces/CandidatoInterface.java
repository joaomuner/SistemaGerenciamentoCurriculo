/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.Candidato;
import entidade.Temp;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface CandidatoInterface {
    public Candidato procurarCandidato(Long id);
}
