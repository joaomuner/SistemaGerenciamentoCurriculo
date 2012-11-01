/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import main.*;


public class MenuPrincipal extends Janela implements ActionListener {
    JLabel jltitulo;
    
    public MenuPrincipal() {
        super("Bem Vindo - Área de Manutenção", new Dimension(500, 250));
        getContentPane().setLayout(null);

        JMenuBar Barra = new JMenuBar();
        setJMenuBar(Barra);

        JMenu mnuNovo = new JMenu("Novo");
        Barra.add(mnuNovo);

        JMenu mnuEditar = new JMenu("Editar");
        Barra.add(mnuEditar);

        JMenuItem mnuNovaAreaAtuacao = new JMenuItem("Área de Atuação");
        
        mnuNovo.add(mnuNovaAreaAtuacao);
        mnuNovaAreaAtuacao.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new JanelaAtuacao().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        JMenuItem mnuNovaEmpresa = new JMenuItem("Empresa");
        
        mnuNovo.add(mnuNovaEmpresa);
        mnuNovaEmpresa.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new JanelaEmpresa().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        JMenuItem mnuNovaUniversidade = new JMenuItem("Universidade");
        
        mnuNovo.add(mnuNovaUniversidade);
        mnuNovaUniversidade.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new JanelaUniversidade().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        JMenuItem mnuEditarAreaAtuacao = new JMenuItem("Área de Atuação");
        
        mnuEditar.add(mnuEditarAreaAtuacao);
        mnuEditarAreaAtuacao.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarAtuacao().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        JMenuItem mnuEditarEmpresa = new JMenuItem("Empresa");
        
        mnuEditar.add(mnuEditarEmpresa);
        mnuEditarEmpresa.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarEmpresa().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        JMenuItem mnuEditarUniversidade = new JMenuItem("Universidade");
        
        mnuEditar.add(mnuEditarUniversidade);
        mnuEditarUniversidade.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarUniversidade().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        
        jltitulo = new JLabel("Manutenção");
        jltitulo.setBounds(10, 70, 120, 18);
        jltitulo.setFont(new Font("Manutenção", Font.PLAIN,20));
        jltitulo.setHorizontalAlignment(SwingConstants.LEFT);
        add(jltitulo);

    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
