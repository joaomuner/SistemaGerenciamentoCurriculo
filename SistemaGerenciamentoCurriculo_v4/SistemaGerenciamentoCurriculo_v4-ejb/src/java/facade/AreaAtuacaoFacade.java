/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.AreaAtuacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jota
 */
@Stateless
public class AreaAtuacaoFacade extends AbstractFacade<AreaAtuacao> {
    @PersistenceContext(unitName = "SistemaGerenciamentoCurriculo_v4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaAtuacaoFacade() {
        super(AreaAtuacao.class);
    }
    
}
