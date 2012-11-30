/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.Administrador;
import entidade.Empresa;
import interfaces.AdministradorInterface;
import interfaces.EmpresaInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author tuca
 */
public class NovoAdministrador extends Janela {

    JLabel jltitulo, jlnome, jlemail, jlsenha;
    JTextField jtfnome, jtfemail, jtfsenha;
    JButton jbInserir;

    public NovoAdministrador() throws Exception {
        super("Administrador", new Dimension(400, 210));

        jltitulo = new JLabel("Administrador");
        jltitulo.setBounds(160, 10, 200, 18);
        add(jltitulo);

        //Label Nome
        jlnome = new JLabel("Nome");
        jlnome.setBounds(20, 60, 50, 18);
        add(jlnome);

        //Text Nome
        jtfnome = new JTextField();
        jtfnome.setBounds(80, 60, 285, 20);
        add(jtfnome);

        //Label Email
        jlemail = new JLabel("Email");
        jlemail.setBounds(20, 85, 50, 18);
        add(jlemail);

        //Text Email
        jtfemail = new JTextField();
        jtfemail.setBounds(80, 85, 285, 20);
        add(jtfemail);

        //Label Senha
        jlsenha = new JLabel("Senha");
        jlsenha.setBounds(20, 110, 50, 18);
        add(jlsenha);

        //Text Senha
        jtfsenha = new JTextField();
        jtfsenha.setBounds(80, 110, 285, 20);
        add(jtfsenha);

        //Button Inserir
        jbInserir = new JButton("Inserir");
        jbInserir.setBounds(285, 140, 80, 23);
        jbInserir.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            if (validarUsuario()) {
                                inserir();
                            } else {
                                JOptionPane.showMessageDialog(null, "Já existe um administrador com esse email cadastrado!", "Administrador", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(NovaAreaAtuacao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        jbInserir.setMnemonic('i');
        add(jbInserir);
    }

    private boolean validarUsuario() throws Exception {
        System.out.println("-----\nIniciando inserção de Administrador");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        Administrador administrador = bean.procurarAdministradorEmail(jtfemail.getText());
        if (administrador == null) {
            return true;
        }

        return false;
    }

    public void inserir() throws Exception {
        System.out.println("-----\nIniciando inserção de Administrador");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        Administrador admin = new Administrador();
        System.out.println("Setando as variaveis");
        admin.setNome(jtfnome.getText());
        admin.setEmail(jtfemail.getText());
        admin.setSenha(jtfsenha.getText());
        bean.inserir(admin);
        System.out.println("Administrador inserido!\n-----");



        JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso!", "Administrador", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
    }

    public void limpaRegistros() {
        jtfnome.setText(null);
        jtfemail.setText(null);
        jtfsenha.setText(null);
    }
}
