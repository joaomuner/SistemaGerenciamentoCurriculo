/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.AreaAtuacao;
import interfaces.AreaAtuacaoInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.*;

/**
 *
 * @author tuca
 */
public class NovaAreaAtuacao extends Janela {

    JLabel jltitulo, jlarea, jlDescricao;
    JTextField jtfarea;
    JButton jbInserir;
    JTextArea jTextArea1;

    public NovaAreaAtuacao() throws Exception {
        super("Área de Atuação", new Dimension(400, 180));

        jltitulo = new JLabel("Área de Atuação");
        jltitulo.setBounds(150, 10, 200, 18);
        add(jltitulo);
        
        //Label Nome
        jlarea = new JLabel("Nome");
        jlarea.setBounds(20, 60, 50, 18);
        add(jlarea);
        
        //Text Nome
        jtfarea = new JTextField();
        jtfarea.setBounds(80, 60, 285, 20);
        add(jtfarea);

       //Button Inserir
        jbInserir = new JButton("Inserir");
        jbInserir.setBounds(285, 100, 80, 23);
        jbInserir.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            inserir();
                        } catch (Exception ex) {
                            Logger.getLogger(NovaAreaAtuacao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        jbInserir.setMnemonic('i');
        add(jbInserir);
    }

    public void inserir() throws Exception {
        System.out.println("-----\nIniciando inserção de área de atuação");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AreaAtuacaoInterface bean = (AreaAtuacaoInterface) ctx.lookup("AreaAtuacaoBean");
        AreaAtuacao area = new AreaAtuacao();
        System.out.println("Setando as variaveis");
        area.setDescricao(jtfarea.getText().toUpperCase());
        bean.inserir(area);
        System.out.println("Área de atuação inserida!\n-----");



        JOptionPane.showMessageDialog(null, "Área de Atuação cadastrada com sucesso!", "Área de Atuação", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
    }

    public void limpaRegistros() {
        jtfarea.setText(null);
    }
}
