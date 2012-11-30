/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.ExperienciaAnterior;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface ExperienciaInterface {
    public List<ExperienciaAnterior> listarExperiencias ();
    public List<ExperienciaAnterior> listarExperienciasCandidato (Long idCandidato);
    public List<ExperienciaAnterior> listarExperienciasCandidatoEmpresa(Long idEmpresa, Long idCandidato);
    public List<Long> listarExperienciasCandidatosEmpresa(Long idEmpresa);
    public ExperienciaAnterior buscarExperiencia(Long id);
    public void editarExperiencia(ExperienciaAnterior experiencia);
}
