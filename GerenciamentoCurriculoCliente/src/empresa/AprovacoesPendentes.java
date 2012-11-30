/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import Util.Janela;
import bean.ControleSessaoBean;
import entidade.Candidato;
import entidade.Empresa;
import entidade.ExperienciaAnterior;
import interfaces.CandidatoInterface;
import interfaces.ControleSessaoInterface;
import interfaces.ExperienciaInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author tuca
 */
public class AprovacoesPendentes extends Janela {

    JLabel lblTitulo, lblExperiencias, lblIdExperiencia, lblCandidatos, lblIdCandidato, lblNome, lblSalario, lblDataInicio, lblDataFim, lblDescricaoAtv;
    JTextArea txtNome, txtSalario, txtDataInicio, txtDataFim;
    Border border = BorderFactory.createLineBorder(Color.gray);
    JList lstExperiencia, lstCandidato;
    JScrollPane scrollExperiencias, scrollCandidatos;
    DefaultListModel lstModelExperiencias, lstModelCandidatos;
    JPanel pnlAprovacao;
    JTextArea txtDescricaoAtv;
    JButton btnAprovar, btnReprovar, btnCurriculo;

    public AprovacoesPendentes() throws Exception {
        super("Aprovações Pendentes", new Dimension(500, 545));

        lblTitulo = new JLabel("Aprovações Pendentes");
        lblTitulo.setBounds(180, 10, 200, 18);
        add(lblTitulo);

        lblExperiencias = new JLabel("Candidatos");
        lblExperiencias.setBounds(10, 50, 200, 20);
        add(lblExperiencias);

        //Instância ListModel
        lstModelCandidatos = new DefaultListModel();
        lblIdCandidato = new JLabel();
        //List Vagas
        lstCandidato = new JList(lstModelCandidatos);
        lstCandidato.setBounds(10, 70, 200, 424);
        lstCandidato.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scrollCandidatos = new JScrollPane(lstCandidato);
        scrollCandidatos.setPreferredSize(new Dimension(200, 424));
        scrollCandidatos.setBounds(10, 70, 200, 424);
        add(scrollCandidatos, BorderLayout.CENTER);
        CarregarListCandidatos();
        lstCandidato.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstCandidato.isFocusOwner()) {
                    try {
                        CarregarExperienciasCandidatoSelecionado();
                    } catch (Exception ex) {
                        Logger.getLogger(VagaCandidato.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        lblExperiencias = new JLabel("Experiencias");
        lblExperiencias.setBounds(230, 50, 200, 20);
        add(lblExperiencias);

        //Instância ListModel
        lstModelExperiencias = new DefaultListModel();
        lblIdExperiencia = new JLabel();
        //List Vagas
        lstExperiencia = new JList(lstModelExperiencias);
        lstExperiencia.setBounds(230, 70, 240, 140);
        lstExperiencia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scrollExperiencias = new JScrollPane(lstExperiencia);
        scrollExperiencias.setPreferredSize(new Dimension(240, 140));
        scrollExperiencias.setBounds(230, 70, 240, 140);
        add(scrollExperiencias, BorderLayout.CENTER);
        lstExperiencia.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstExperiencia.isFocusOwner()) {
                    try {
                        CarregarExperienciaSelecionada();
                    } catch (Exception ex) {
                        Logger.getLogger(VagaCandidato.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        pnlAprovacao = new JPanel();
        pnlAprovacao.setBounds(230, 230, 240, 265);
        pnlAprovacao.setBorder(border);
        pnlAprovacao.setLayout(null);
        pnlAprovacao.setVisible(false);
        add(pnlAprovacao);

        lblNome = new JLabel("Nome Candidato");
        lblNome.setBounds(5, 5, 100, 20);
        pnlAprovacao.add(lblNome);

        txtNome = new JTextArea();
        txtNome.setBounds(5, 25, 230, 20);
        txtNome.setEnabled(false);
        txtNome.setBorder(border);
        txtNome.setDisabledTextColor(Color.gray);
        pnlAprovacao.add(txtNome);

        lblDescricaoAtv = new JLabel("Descrição da Atividade");
        lblDescricaoAtv.setBounds(5, 45, 200, 20);
        pnlAprovacao.add(lblDescricaoAtv);

        txtDescricaoAtv = new JTextArea();
        txtDescricaoAtv.setBounds(5, 65, 230, 80);
        txtDescricaoAtv.setEnabled(false);
        txtDescricaoAtv.setDisabledTextColor(Color.gray);
        txtDescricaoAtv.setBorder(border);
        pnlAprovacao.add(txtDescricaoAtv);

        lblDataInicio = new JLabel("Data Contratação");
        lblDataInicio.setBounds(5, 150, 100, 20);
        pnlAprovacao.add(lblDataInicio);

        txtDataInicio = new JTextArea();
        txtDataInicio.setBounds(124, 150, 110, 20);
        txtDataInicio.setEnabled(false);
        txtDataInicio.setDisabledTextColor(Color.gray);
        txtDataInicio.setBorder(border);
        pnlAprovacao.add(txtDataInicio);

        lblDataFim = new JLabel("Data Final");
        lblDataFim.setBounds(5, 180, 100, 20);
        pnlAprovacao.add(lblDataFim);

        txtDataFim = new JTextArea();
        txtDataFim.setBounds(124, 180, 110, 20);
        txtDataFim.setEnabled(false);
        txtDataFim.setDisabledTextColor(Color.gray);
        txtDataFim.setBorder(border);
        pnlAprovacao.add(txtDataFim);

        lblSalario = new JLabel("Salário");
        lblSalario.setBounds(5, 210, 100, 20);
        pnlAprovacao.add(lblSalario);

        txtSalario = new JTextArea();
        txtSalario.setBounds(124, 210, 110, 20);
        txtSalario.setEnabled(false);
        txtSalario.setDisabledTextColor(Color.gray);
        txtSalario.setBorder(border);
        pnlAprovacao.add(txtSalario);

        btnAprovar = new JButton("Aprovar");
        btnAprovar.setBounds(135, 240, 100, 20);
        btnAprovar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            aprovar();
                        } catch (Exception ex) {
                            Logger.getLogger(AprovacoesPendentes.class.getName()).log(Level.SEVERE,
                                    null, ex);
                        }
                    }
                });
        btnAprovar.setMnemonic('A');
        pnlAprovacao.add(btnAprovar);

        btnReprovar = new JButton("Reprovar");
        btnReprovar.setBounds(135, 240, 100, 20);
        btnReprovar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            reprovar();
                        } catch (Exception ex) {
                            Logger.getLogger(AprovacoesPendentes.class.getName()).log(Level.SEVERE,
                                    null, ex);
                        }
                    }
                });
        btnReprovar.setMnemonic('R');
        pnlAprovacao.add(btnReprovar);

        btnCurriculo = new JButton("Currículo");
        btnCurriculo.setBounds(5, 240, 100, 20);
        btnCurriculo.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            CarregarCurriculoCandidato();
                        } catch (Exception ex) {
                            Logger.getLogger(AprovacoesPendentes.class.getName()).log(Level.SEVERE,
                                    null, ex);
                        }
                    }
                });
        btnCurriculo.setMnemonic('C');
        pnlAprovacao.add(btnCurriculo);
    }
    
    private void CarregarCurriculoCandidato() throws Exception{
        System.out.println("Encaminhando para a janela do currículo");
        CurriculoCandidato curriculo = new CurriculoCandidato();
        System.out.println("Carregando o currículo com o candidato:" + lblIdCandidato.getText());
        curriculo.CarregarCurriculo(Long.parseLong(lblIdCandidato.getText()));
        System.out.println("Tornando a janela visivel");
        curriculo.setVisible(true);
    }

    private void aprovar() throws Exception {
        ExperienciaAnterior experiencia = carregarExperiencia();
        experiencia.setFlagAprovado("S");

        System.out.println("-----\nIniciando busca da experiencia: " + lblIdExperiencia.getText());
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        ExperienciaInterface bean = (ExperienciaInterface) ctx.lookup("ExperienciaBean");
        System.out.println("Resgatando a experiencia");
        bean.editarExperiencia(experiencia);

        JOptionPane.showMessageDialog(null, "Experiência aprovada!","Experiência Anterior", JOptionPane.INFORMATION_MESSAGE);
        limparRegistros();
    }

    private void reprovar() throws Exception {
        ExperienciaAnterior experiencia = carregarExperiencia();
        experiencia.setFlagAprovado("N");

        System.out.println("-----\nIniciando busca da experiencia: " + lblIdExperiencia.getText());
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        ExperienciaInterface bean = (ExperienciaInterface) ctx.lookup("ExperienciaBean");
        System.out.println("Resgatando a experiencia");
        bean.editarExperiencia(experiencia);

        JOptionPane.showMessageDialog(null, "Experiência reprovada!", "Experiência Anterior", JOptionPane.INFORMATION_MESSAGE);
        limparRegistros();
    }

    private void limparRegistros() throws Exception {
        pnlAprovacao.setVisible(false);
        //Zera o JList para uma nova consulta
        CarregarExperienciasCandidatoSelecionado();
    }

    private void CarregarExperienciaSelecionada() throws Exception {
        String[] s = lstExperiencia.getSelectedValue().toString().split("-");
        lblIdExperiencia.setText(s[0]);
        pnlAprovacao.setVisible(true);

        ExperienciaAnterior experiencia = carregarExperiencia();

        txtDescricaoAtv.setText(experiencia.getDescricaoAtv());
        txtDataInicio.setText(experiencia.getDataInicio());
        txtDataFim.setText(experiencia.getDataFim());
        txtSalario.setText(experiencia.getSalario().toString());

        if (experiencia.getFlagAprovado().equals("S")) {
            btnAprovar.setVisible(false);
            btnReprovar.setVisible(true);
        } else {
            btnAprovar.setVisible(true);
            btnReprovar.setVisible(false);
        }
    }

    private ExperienciaAnterior carregarExperiencia() throws Exception {

        System.out.println("-----\nIniciando busca da experiencia: " + lblIdExperiencia.getText());
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        ExperienciaInterface bean = (ExperienciaInterface) ctx.lookup("ExperienciaBean");
        System.out.println("Resgatando a experiencia");
        return bean.buscarExperiencia(Long.parseLong(lblIdExperiencia.getText()));

    }

    private void CarregarExperienciasCandidatoSelecionado() throws Exception {
        String[] s = lstCandidato.getSelectedValue().toString().split("-");
        lblIdCandidato.setText(s[0]);
        txtNome.setText(s[1]);
        pnlAprovacao.setVisible(false);

        Empresa empresa = carregarEmpresa();

        //Zera o JList para uma nova consulta
        if (lstModelExperiencias != null) {
            lstModelExperiencias.removeAllElements();
        }

        System.out.println("-----\nIniciando busca de experiencias");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        ExperienciaInterface bean = (ExperienciaInterface) ctx.lookup("ExperienciaBean");
        System.out.println("Resgatando as experiencias do candidato:" + lblIdCandidato.getText());
        List<ExperienciaAnterior> lstExperiencias = bean.listarExperienciasCandidatoEmpresa(empresa.getId(), Long.parseLong(lblIdCandidato.getText()));
        InserirListExperiencia(lstExperiencias);
    }

    private Empresa carregarEmpresa() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
        ControleSessaoBean sessao = ControleSessaoBean.getInstance();
        return sessao.getUsuario();
    }

    private void CarregarListCandidatos() throws Exception {
        Empresa empresa = carregarEmpresa();

        //Zera o JList para uma nova consulta
        if (lstModelCandidatos != null) {
            lstModelCandidatos.removeAllElements();
        }
        System.out.println("-----\nIniciando busca das experiencias");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        ExperienciaInterface bean = (ExperienciaInterface) ctx.lookup("ExperienciaBean");
        System.out.println("Resgatando as experiencias:" + empresa.getId());
        List<Long> lstIdCandidatos = bean.listarExperienciasCandidatosEmpresa(empresa.getId());
        InserirListCandidatos(lstIdCandidatos);
    }

    private Candidato carregarCandidato(Long idCandidato) throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        CandidatoInterface bean = (CandidatoInterface) ctx.lookup("CandidatoBean");
        return bean.procurarCandidato(idCandidato);
    }

    private void InserirListCandidatos(List<Long> lstCandidatos) throws Exception {
        for (Iterator iter = lstCandidatos.iterator(); iter.hasNext();) {
            Long idCandidato = (Long) iter.next();
            if (idCandidato != null) {
                Candidato c = carregarCandidato(idCandidato);
                lstModelCandidatos.addElement(c.getId() + "-" + c.getNome());
            }
        }
    }

    private void InserirListExperiencia(List<ExperienciaAnterior> lstExperiencia) throws Exception {
        for (Iterator iter = lstExperiencia.iterator(); iter.hasNext();) {
            ExperienciaAnterior experiencia = (ExperienciaAnterior) iter.next();
            if (experiencia != null) {
                Candidato c = carregarCandidato(experiencia.getIdCandidato());
                String descricao = experiencia.getDescricaoAtv();
                if (experiencia.getDescricaoAtv().length() > 20) {
                    descricao = experiencia.getDescricaoAtv().substring(0, 20) + "...";
                }
                lstModelExperiencias.addElement(experiencia.getId() + "-" + descricao);
            }
        }
    }
}
