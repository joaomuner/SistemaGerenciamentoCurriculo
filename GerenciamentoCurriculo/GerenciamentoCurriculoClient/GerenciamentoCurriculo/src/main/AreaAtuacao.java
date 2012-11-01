/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Dimension;
import menu.Janela;

/**
 *
 * @author Jota
 */
public class AreaAtuacao extends Janela {

    /**
     * @param args the command line arguments
     */
    public AreaAtuacao() throws Exception {
        super("Área de Atuação", new Dimension(400, 300));
        
        
    }
    
    public static void main(String[] args)throws Exception  {
        // TODO code application logic here
        JanelaAtuacao janela = new JanelaAtuacao();
        janela.setVisible(true);
    }
}
