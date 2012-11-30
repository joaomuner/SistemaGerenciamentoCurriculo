/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Universidade;
import facade.UniversidadeFacade;
import interfaces.UniversidadeInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "UniversidadeBean")
public class UniversidadeBean implements UniversidadeInterface {

    @EJB
    public UniversidadeFacade universidadeFacade;
    public Universidade universidade;

    public UniversidadeBean() {
        this.universidade = new Universidade();
    }
    
    @Override
    public void inserir(Universidade universidade) {
        this.universidadeFacade.create(universidade);
    }

    @Override
    public List<Universidade> listarUniversidade() {
        return this.universidadeFacade.listUniversidade();
    }

    @Override
    public Universidade procurarUniversidade(Long id) {
        return this.universidadeFacade.findUniversidade(id);
    }

    @Override
    public void atualizar(Universidade universidade) {
        this.universidadeFacade.edit(universidade);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
