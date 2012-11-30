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
import interfaces.ControleSessaoInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.*;
import javax.naming.InitialContext;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import manutencao.*;

/**
 *
 * @author thaiane
 */
public class MenuAdmin extends Janela {

    JLabel lblBemVindo;

    public MenuAdmin() throws Exception {
        super("Bem Vindo", new Dimension(500, 250));
        getContentPane().setLayout(null);

        Administrador admin = carregarAdministrador();

        lblBemVindo = new JLabel("Bem vindo, " + admin.getNome() + "!");
        lblBemVindo.setBounds(10, 20, 300, 20);
        add(lblBemVindo);

        JMenuBar Barra = new JMenuBar();
        setJMenuBar(Barra);

        // Menu NOVO
        JMenu mnuNovo = new JMenu("Novo");
        Barra.add(mnuNovo);

        //Novo Administrador
        JMenuItem mnuNovoAdministrador = new JMenuItem("Administrador");
        mnuNovo.add(mnuNovoAdministrador);
        mnuNovoAdministrador.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new NovoAdministrador().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });


        //Nova Área de Atuação
        JMenuItem mnuNovaAreaAtuacao = new JMenuItem("Área de Atuação");
        mnuNovo.add(mnuNovaAreaAtuacao);
        mnuNovaAreaAtuacao.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new NovaAreaAtuacao().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        //Nova Empresa
        JMenuItem mnuNovaEmpresa = new JMenuItem("Empresa");
        mnuNovo.add(mnuNovaEmpresa);
        mnuNovaEmpresa.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new NovaEmpresa().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });


        //Nova Instituição
        JMenuItem mnuNovaInstituicao = new JMenuItem("Instituição");
        mnuNovo.add(mnuNovaInstituicao);
        mnuNovaInstituicao.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new NovaInstituicaoEnsino().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        // Menu EDITAR
        JMenu mnuEditar = new JMenu("Editar");
        Barra.add(mnuEditar);


        //Editar Administrador
        JMenuItem mnuEditarAdministrador = new JMenuItem("Administrador");
        mnuEditar.add(mnuEditarAdministrador);
        mnuEditarAdministrador.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarAdministrador().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });


        //Editar AreaAtuacao
        JMenuItem mnuEditarAreaAtuacao = new JMenuItem("Área de Atuação");
        mnuEditar.add(mnuEditarAreaAtuacao);
        mnuEditarAreaAtuacao.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarAreaAtuacao().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });


        //Editar Empresa
        JMenuItem mnuEditarEmpresa = new JMenuItem("Empresa");
        mnuEditar.add(mnuEditarEmpresa);
        mnuEditarEmpresa.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarEmpresa().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });


        //Editar AreaAtuacao
        JMenuItem mnuEditarInstituicao = new JMenuItem("Instituição");
        mnuEditar.add(mnuEditarInstituicao);
        mnuEditarInstituicao.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new EditarInstituicaoEnsino().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
        
        // Menu SAIR
        JMenu mnuSair = new JMenu("Sair");
        Barra.add(mnuSair);

        //SAIR Sair
        JMenuItem mnuSairSair = new JMenuItem("Sair");
        mnuSair.add(mnuSairSair);
        mnuSairSair.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            encerrarSessaoAdministrador();
                            dispose();
                            new Login().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }

    private Administrador carregarAdministrador() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoAdminBean");
        ControleSessaoAdminBean sessao = ControleSessaoAdminBean.getInstance();
        return sessao.getUsuario();
    }
    
    private void encerrarSessaoAdministrador() throws Exception {
        //Inicia a sessão do usuário
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
        ControleSessaoAdminBean sessao = ControleSessaoAdminBean.getInstance();
        sessao.encerrarSessao();
    }
}
