/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.AreaAtuacao;
import facade.AreaAtuacaoFacade;
import interfaces.AreaAtuacaoInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author tuca
 */
@Stateless(mappedName = "AreaAtuacaoBean")
public class AreaAtuacaoBean implements AreaAtuacaoInterface {

    @EJB
    public AreaAtuacaoFacade areaAtuacaoFacade;
    public AreaAtuacao areaAtuacao;

    public AreaAtuacaoBean() {
        this.areaAtuacao = new AreaAtuacao();
    }

    @Override
    public void inserir(AreaAtuacao areaAtuacao) {
        this.areaAtuacaoFacade.create(areaAtuacao);
    }

    @Override
    public List<AreaAtuacao> listarAreaAtuacao() {
        return this.areaAtuacaoFacade.listAreaAtuacao();
    }

    @Override
    public AreaAtuacao procurarAreaAtuacao(Long id) {
        return this.areaAtuacaoFacade.findAreaAtuacao(id);
    }

    @Override
    public void atualizar(AreaAtuacao areaAtuacao) {
        this.areaAtuacaoFacade.edit(areaAtuacao);
    }
}
