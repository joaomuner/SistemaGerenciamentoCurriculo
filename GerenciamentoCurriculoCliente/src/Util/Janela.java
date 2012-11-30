/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author tuca
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Janela extends JFrame {

    public Janela(String titulo, Dimension tamanho) {
        setTitle(titulo);
        setSize(tamanho);
        centralize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(250, 250, 250));
    }

    public void centralize() {
        Dimension T = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension J = getSize();
        if (J.height > T.height) {
            setSize(J.width, T.height);
        }
        if (J.width > T.width) {
            setSize(J.width, T.height);
        }
        setLocation((T.width - J.width) / 2, (T.height - J.height) / 2);
    }

  
}
