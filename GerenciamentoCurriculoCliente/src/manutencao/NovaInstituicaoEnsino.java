/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.Universidade;
import interfaces.UniversidadeInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;

/**
 *
 * @author tuca
 */
public class NovaInstituicaoEnsino extends Janela {

    JLabel jltitulo, jlnome, jlDescricao;
    JTextField jtfnome;
    JButton jbInserir;
    JTextArea jTextArea1;

    public NovaInstituicaoEnsino() throws Exception {
        super("Instituição de Ensino", new Dimension(400, 180));


        jltitulo = new JLabel("Instituição de Ensino");
        jltitulo.setBounds(140, 10, 200, 18);
        add(jltitulo);

        //Label Nome
        jlnome = new JLabel("Nome");
        jlnome.setBounds(20, 60, 50, 18);
        add(jlnome);
        
        //TextField Nome
        jtfnome = new JTextField();
        jtfnome.setBounds(80, 60, 285, 20);
        add(jtfnome);


        //ButtonInserir
        jbInserir = new JButton("Inserir");
        jbInserir.setBounds(285, 100, 80, 23);
        jbInserir.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                try {
                    inserir();
                } catch (IOException ex) {
                    Logger.getLogger(NovaInstituicaoEnsino.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(NovaInstituicaoEnsino.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                });

        jbInserir.setMnemonic('i');
        add(jbInserir);
    }

    public void inserir() throws IOException, NamingException {
    
        System.out.println("-----\nIniciando inserção da universidade");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        UniversidadeInterface bean = (UniversidadeInterface) ctx.lookup("UniversidadeBean");
        Universidade universidade = new Universidade();
        System.out.println("Setando as variaveis");
        universidade.setNome(jtfnome.getText().toUpperCase());
        bean.inserir(universidade);
        System.out.println("Universidade inserida!\n-----");

        
        
        JOptionPane.showMessageDialog(null, "Instituição cadastrada com sucesso!", "Instituição de Ensino", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
    }

    public void limpaRegistros() {
        jtfnome.setText(null);
    }

}