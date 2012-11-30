/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.Empresa;
import interfaces.EmpresaInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.*;
import javax.naming.InitialContext;
import javax.swing.*;

/**
 *
 * @author tuca
 */
public class NovaEmpresa extends Janela {

    JLabel jltitulo, jlnome, jlemail, jlsenha;
    JTextField jtfempresa, jtfemail, jtfsenha;
    JButton jbInserir;

    public NovaEmpresa() throws Exception {
        super("Empresa", new Dimension(400, 210));

        jltitulo = new JLabel("Empresa");
        jltitulo.setBounds(170, 10, 200, 18);
        add(jltitulo);

        //Label Nome
        jlnome = new JLabel("Nome");
        jlnome.setBounds(20, 60, 50, 18);
        add(jlnome);

        //Text Nome
        jtfempresa = new JTextField();
        jtfempresa.setBounds(80, 60, 285, 20);
        add(jtfempresa);

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
                                JOptionPane.showMessageDialog(null, "Já existe uma empresa com esse email cadastrado!", "Empresa", JOptionPane.INFORMATION_MESSAGE);
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
        System.out.println("-----\nIniciando inserção de empresa");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        Empresa empresa = bean.procurarEmpresaEmail(jtfemail.getText());
        if (empresa == null) {
            return true;
        }

        return false;
    }

    public void inserir() throws Exception {
        System.out.println("-----\nIniciando inserção de empresa");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        Empresa empresa = new Empresa();
        System.out.println("Setando as variaveis");
        empresa.setNome(jtfempresa.getText());
        empresa.setEmail(jtfemail.getText());
        empresa.setSenha(jtfsenha.getText());
        bean.inserir(empresa);
        System.out.println("Empresa inserida!\n-----");



        JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!", "Empresa", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
    }

    public void limpaRegistros() {
        jtfempresa.setText(null);
        jtfemail.setText(null);
        jtfsenha.setText(null);
    }
}
