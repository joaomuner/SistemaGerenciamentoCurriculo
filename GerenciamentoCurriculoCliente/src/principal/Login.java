/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Util.Janela;
import bean.ControleSessaoAdminBean;
import bean.ControleSessaoBean;
import entidade.Administrador;
import entidade.Empresa;
import interfaces.AdministradorInterface;
import interfaces.ControleSessaoInterface;
import interfaces.EmpresaInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.*;
import manutencao.NovaEmpresa;

/**
 *
 * @author tuca
 */
public class Login extends Janela {

    JButton btnEntrar;
    JComboBox cmbPerfil;
    JLabel lblPerfil, lblEmail, lblSenha, lblTitulo;
    JTextField txtEmail, txtSenha;

    public Login() throws Exception {
        super("Login", new Dimension(290, 240));

        //Insere administrador default
        inserirAdminDefault();
                
        lblTitulo = new JLabel("Login");
        lblTitulo.setBounds(125, 10, 50, 18);
        add(lblTitulo);

        //JLabel Perfil
        lblPerfil = new JLabel("Perfil");
        lblPerfil.setBounds(10, 40, 40, 18);
        add(lblPerfil);

        //JCombo Perfil
        cmbPerfil = new JComboBox();
        cmbPerfil.addItem("Administrador");
        cmbPerfil.addItem("Empresa");
        cmbPerfil.setBounds(60, 40, 200, 18);
        add(cmbPerfil);

        //JLabel Email
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 70, 40, 18);
        add(lblEmail);

        //JTextField Email
        txtEmail = new JTextField();
        txtEmail.setBounds(60, 70, 200, 18);
        add(txtEmail);

        //JLabel Senha
        lblSenha = new JLabel("Senha");
        lblSenha.setBounds(10, 100, 40, 18);
        add(lblSenha);

        //JTextField Senha
        txtSenha = new JTextField();
        txtSenha.setBounds(60, 100, 200, 18);
        add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 150, 80, 20);
        btnEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ValidarUsuario()) {
                        if (cmbPerfil.getSelectedIndex() == 1) {
                            //Busca da empresa logada
                            Empresa empresa = buscarEmpresa();

                            //Inicia a sessão do usuário
                            Properties props = new Properties();
                            props.load(new java.io.FileInputStream("jndi.properties"));
                            InitialContext ctx = new InitialContext(props);
                            ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
                            ControleSessaoBean sessao = ControleSessaoBean.getInstance();
                            sessao.setUsuario(empresa);

                            //Encaminha para a janela correspondente ao perfil
                            MenuEmpresa mnuEmpresa = new MenuEmpresa();
                            mnuEmpresa.setVisible(true);

                            dispose();
                        } else {
                            //Busca do admin logado
                            Administrador administrador = buscarAdministrador();

                            //Inicia a sessão do usuário
                            Properties props = new Properties();
                            props.load(new java.io.FileInputStream("jndi.properties"));
                            InitialContext ctx = new InitialContext(props);
                            ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
                            ControleSessaoAdminBean sessao = ControleSessaoAdminBean.getInstance();
                            sessao.setUsuario(administrador);

                            //Encaminha para a janela correspondente ao perfil
                            MenuAdmin mnuAdmin = new MenuAdmin();
                            mnuAdmin.setVisible(true);

                            dispose();
                        }

                    } else {
                        limparCampos();
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btnEntrar.setMnemonic('E');
        add(btnEntrar);

    }

    private Empresa buscarEmpresa() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        System.out.println("Procurando a empresa");
        Empresa empresa = bean.procurarEmpresaEmailSenha(txtEmail.getText(), txtSenha.getText());
        return empresa;
    }

    private Administrador buscarAdministrador() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        System.out.println("Procurando o admin");
        Administrador administrador = bean.procurarAdministradorEmailSenha(txtEmail.getText(), txtSenha.getText());
        return administrador;
    }

    private void inserirAdminDefault() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        System.out.println("Procurando o admin");
        List<Administrador> lstAdministrador = bean.listarAdministrador();

        //Se não tiver administrador, insere um default
        if (lstAdministrador.isEmpty()) {
            System.out.println("Nenhum admin no sistema!");
            Administrador admin = new Administrador();
            System.out.println("Setando as variaveis");
            admin.setNome("Administrador");
            admin.setEmail("admin@admin.com");
            admin.setSenha("admin123");
            bean.inserir(admin);
            System.out.println("Administrador inserido!\n-----");
        }

    }

    private boolean ValidarUsuario() throws Exception {
        //Se o perfil selecionado for Empresa
        if (cmbPerfil.getSelectedIndex() == 1) {
            Empresa empresa = buscarEmpresa();
            if (empresa != null) {
                return true;
            }
        } else {
            Administrador administrador = buscarAdministrador();
            if (administrador != null) {
                return true;
            }
        }
        return false;
    }

    private void limparCampos() {
        txtEmail.setText(null);
        txtSenha.setText(null);
    }

    public static void main(String[] args) throws Exception {
        new Login().setVisible(true);
    }
}
