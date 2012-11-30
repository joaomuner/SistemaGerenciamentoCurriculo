/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Util.Janela;
import bean.ControleSessaoBean;
import empresa.AprovacoesPendentes;
import empresa.Editar;
import empresa.NovaVaga;
import empresa.VagaCandidato;
import entidade.Empresa;
import interfaces.ControleSessaoInterface;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.*;
import javax.naming.InitialContext;
import javax.swing.*;

/**
 *
 * @author thaiane
 */
public class MenuEmpresa extends Janela {

    JLabel lblBemVindo;

    public MenuEmpresa() throws Exception {
        super("Bem Vindo", new Dimension(500, 250));
        getContentPane().setLayout(null);

        Empresa empresa = carregarEmpresa();

        lblBemVindo = new JLabel("Bem vindo, " + empresa.getNome() + "!");
        lblBemVindo.setBounds(10, 20, 300, 20);
        add(lblBemVindo);

        JMenuBar Barra = new JMenuBar();
        setJMenuBar(Barra);

        // Menu NOVO
        JMenu mnuNovo = new JMenu("Novo");
        Barra.add(mnuNovo);

        //Nova Vaga
        JMenuItem mnuNovaVaga = new JMenuItem("Vaga");
        mnuNovo.add(mnuNovaVaga);
        mnuNovaVaga.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new NovaVaga().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        // Menu EDITAR
        JMenu mnuEditar = new JMenu("Editar");
        Barra.add(mnuEditar);

        //Editar Empresa
        JMenuItem mnuEditarEditar = new JMenuItem("Editar");
        mnuEditar.add(mnuEditarEditar);
        mnuEditarEditar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new Editar().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });


        // Menu VISUALIZAR
        JMenu mnuVisualizar = new JMenu("Visualizar");
        Barra.add(mnuVisualizar);

        //Visualizar Candidatos as Vagas
        JMenuItem mnuVisualizarCandidatos = new JMenuItem("Candidatos às Vagas");
        mnuVisualizar.add(mnuVisualizarCandidatos);
        mnuVisualizarCandidatos.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new VagaCandidato().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        //Visualizar Candidatos as Vagas
        JMenuItem mnuVisualizarAprovacoes = new JMenuItem("Aprovações de Projetos");
        mnuVisualizar.add(mnuVisualizarAprovacoes);
        mnuVisualizarAprovacoes.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {
                            new AprovacoesPendentes().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuEmpresa.class.getName()).log(Level.SEVERE, null, ex);
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
                            encerrarSessaoEmpresa();
                            dispose();
                            new Login().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MenuEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }

    private void encerrarSessaoEmpresa() throws Exception {
        //Busca da empresa logada
        Empresa empresa = carregarEmpresa();

        //Inicia a sessão do usuário
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
        ControleSessaoBean sessao = ControleSessaoBean.getInstance();
        sessao.encerrarSessao();
    }

    private Empresa carregarEmpresa() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
        ControleSessaoBean sessao = ControleSessaoBean.getInstance();
        return sessao.getUsuario();
    }
}
