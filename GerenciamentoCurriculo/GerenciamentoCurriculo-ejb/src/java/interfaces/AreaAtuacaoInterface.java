/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.AreaAtuacao;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tuca
 */
@Remote
public interface AreaAtuacaoInterface {
    
    public void inserir (AreaAtuacao areaAtuacao);
    public List<AreaAtuacao> listarAreaAtuacao ();
    public AreaAtuacao procurarAreaAtuacao (Long id);
    public void atualizar (AreaAtuacao areaAtuacao);
}
