/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.ejb.Remote;

/**
 *
 * @author tuca
 */

@Remote
public interface ControleSessaoInterface<T> {
        
    public void setUsuario(T entity);
    public T getUsuario();
    public void encerrarSessao();
}
