/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import Util.Janela;
import bean.ControleSessaoBean;
import entidade.Candidato;
import entidade.CandidatoVaga;
import entidade.Empresa;
import entidade.Vaga;
import interfaces.CandidatoInterface;
import interfaces.CandidatoVagaInterface;
import interfaces.ControleSessaoInterface;
import interfaces.VagaInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.*;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author tuca
 */
public class VagaCandidato extends Janela {

    JLabel lblTitulo, lblCandidatos, lblVagas, lblIdVaga, lblIdCandidato;
    Border border = BorderFactory.createLineBorder(Color.gray);
    JList lstCandidato, lstVaga;
    JScrollPane scrollCandidato, scrollVaga;
    DefaultListModel lstModelCandidato, lstModelVaga;

    public VagaCandidato() throws Exception {
        super("Vaga Candidato", new Dimension(460, 400));

        lblTitulo = new JLabel("Vaga Candidato");
        lblTitulo.setBounds(180, 10, 200, 18);
        add(lblTitulo);

        lblVagas = new JLabel("Vagas cadastradas");
        lblVagas.setBounds(10, 50, 200, 20);
        add(lblVagas);

        //Instância ListModel
        lstModelVaga = new DefaultListModel();
        lblIdVaga = new JLabel();
        //List Vagas
        lstVaga = new JList(lstModelVaga);
        lstVaga.setBounds(10, 70, 200, 280);
        lstVaga.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scrollVaga = new JScrollPane(lstVaga);
        scrollVaga.setPreferredSize(new Dimension(200, 280));
        scrollVaga.setBounds(10, 70, 200, 280);
        add(scrollVaga, BorderLayout.CENTER);
        CarregarListVaga();

        lstVaga.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstVaga.isFocusOwner()) {
                    try {
                        CarregarVagaSelecionada();
                    } catch (Exception ex) {
                        Logger.getLogger(VagaCandidato.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        lblCandidatos = new JLabel("Candidatos inscritos");
        lblCandidatos.setBounds(230, 50, 200, 20);
        add(lblCandidatos);

        //Instância ListModel
        lstModelCandidato = new DefaultListModel();
        lblIdCandidato = new JLabel();

        //List Candidatos
        lstCandidato = new JList(lstModelCandidato);
        lstCandidato.setBounds(230, 70, 200, 280);
        lstCandidato.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scrollCandidato = new JScrollPane(lstCandidato);
        scrollCandidato.setPreferredSize(new Dimension(200, 280));
        scrollCandidato.setBounds(230, 70, 200, 280);
        add(scrollCandidato, BorderLayout.CENTER);

        lstCandidato.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstVaga.isFocusOwner()) {
                    try {
                        CarregarCurriculoCandidato();
                    } catch (Exception ex) {
                        Logger.getLogger(VagaCandidato.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void CarregarListVaga() throws Exception {
        Empresa empresa = carregarEmpresa();

        //Zera o JList para uma nova consulta
        if (lstModelVaga != null) {
            lstModelVaga.removeAllElements();
        }
        System.out.println("-----\nIniciando busca das vagas");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        VagaInterface bean = (VagaInterface) ctx.lookup("VagaBean");
        System.out.println("Resgatando as vagas da empresa:" + empresa.getId());
        List<Vaga> lstVaga = bean.listarVagaEmpresa(empresa.getId());
        InserirListVaga(lstVaga);
    }

    private void CarregarVagaSelecionada() throws Exception {
        String[] s = lstVaga.getSelectedValue().toString().split("-");
        lblIdVaga.setText(s[0]);

        System.out.println("-----\nIniciando busca de candidatos");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        CandidatoVagaInterface bean = (CandidatoVagaInterface) ctx.lookup("CandidatoVagaBean");
        System.out.println("Resgatando os candidatos");
        List<CandidatoVaga> lstCandidatosVaga = bean.listarCandidatoVaga(Long.parseLong(lblIdVaga.getText()));
        InserirListCandidatos(lstCandidatosVaga);
    }

    private void CarregarCurriculoCandidato() throws Exception {
        String[] s = lstCandidato.getSelectedValue().toString().split("-");
        lblIdCandidato.setText(s[0]);
        System.out.println("Encaminhando para a janela do currículo");
        CurriculoCandidato curriculo = new CurriculoCandidato();
        System.out.println("Carregando o currículo com o candidato:" + lblIdCandidato.getText());
        curriculo.CarregarCurriculo(Long.parseLong(lblIdCandidato.getText()));
        System.out.println("Tornando a janela visivel");
        curriculo.setVisible(true);
    }

    private void InserirListCandidatos(List<CandidatoVaga> lstCandidatos) throws Exception {
        //Zera o JList para uma nova consulta
        if (lstModelCandidato != null) {
            lstModelCandidato.removeAllElements();
        }

        for (Iterator iter = lstCandidatos.iterator(); iter.hasNext();) {
            CandidatoVaga candidatoVaga = (CandidatoVaga) iter.next();
            if (candidatoVaga != null) {
                Candidato c = carregarCandidato(candidatoVaga.getIdCandidato());
                if (c != null) {
                    lstModelCandidato.addElement(c.getId() + "-" + c.getNome());
                }
            }
        }
    }

    private Candidato carregarCandidato(Long idCandidato) throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        CandidatoInterface bean = (CandidatoInterface) ctx.lookup("CandidatoBean");
        return bean.procurarCandidato(idCandidato);
    }

    private Empresa carregarEmpresa() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ControleSessaoInterface bean = (ControleSessaoInterface) ctx.lookup("ControleSessaoBean");
        ControleSessaoBean sessao = ControleSessaoBean.getInstance();
        return sessao.getUsuario();
    }

    private void InserirListVaga(List<Vaga> lstVaga) {
        for (Iterator iter = lstVaga.iterator(); iter.hasNext();) {
            Vaga vaga = (Vaga) iter.next();
            if (vaga != null) {
                lstModelVaga.addElement(vaga.getId() + "-" + vaga.getTitulo());
            }
        }
    }
}
