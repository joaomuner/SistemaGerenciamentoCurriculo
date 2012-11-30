/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.ExperienciaAnterior;
import facade.ExperienciaAnteriorFacade;
import interfaces.ExperienciaInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "ExperienciaBean")
public class ExperienciaBean implements ExperienciaInterface {

    @EJB
    public ExperienciaAnteriorFacade experienciaFacade;
    public ExperienciaAnterior experiencia;

    @Override
    public List<ExperienciaAnterior> listarExperiencias() {
        return this.experienciaFacade.listExperiencia();
    }

    @Override
    public List<Long> listarExperienciasCandidatosEmpresa(Long idEmpresa) {
        return this.experienciaFacade.listExperienciaCandidatosEmpresa(idEmpresa);
    }

    @Override
    public List<ExperienciaAnterior> listarExperienciasCandidatoEmpresa(Long idEmpresa, Long idCandidato) {
        return this.experienciaFacade.listExperienciaCandidatoEmpresa(idEmpresa, idCandidato);
    }

    @Override
    public ExperienciaAnterior buscarExperiencia(Long id) {
        return this.experienciaFacade.findExperiencia(id);
    }

    @Override
    public void editarExperiencia(ExperienciaAnterior experiencia) {
        this.experienciaFacade.edit(experiencia);
    }

    @Override
    public List<ExperienciaAnterior> listarExperienciasCandidato(Long idCandidato) {
        return this.experienciaFacade.listExperienciaCandidato(idCandidato);
    }
}
