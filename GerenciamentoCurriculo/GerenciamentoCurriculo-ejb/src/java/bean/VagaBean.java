/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.AreaAtuacao;
import entidade.Vaga;
import facade.VagaFacade;
import interfaces.VagaInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "VagaBean")
public class VagaBean implements VagaInterface {

     @EJB
    public VagaFacade vagaFacade;
    public Vaga vaga;
    
    @Override
    public void inserir(Vaga vaga) {
        this.vagaFacade.create(vaga);
    }

    @Override
    public Vaga procurarVaga(Long id) {
        return this.vagaFacade.findVagas(id);
    }

    @Override
    public List<Vaga> listarVaga() {
        return this.vagaFacade.listVagas();
    }

    @Override
    public List<Vaga> listarVagaEmpresa(Long idEmpresa) {
        return this.vagaFacade.listVagasEmpresa(idEmpresa);
    }
}
