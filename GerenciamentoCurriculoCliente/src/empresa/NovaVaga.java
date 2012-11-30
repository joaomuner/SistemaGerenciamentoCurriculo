/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import Util.Janela;
import bean.ControleSessaoBean;
import entidade.AreaAtuacao;
import entidade.Empresa;
import entidade.Vaga;
import interfaces.AreaAtuacaoInterface;
import interfaces.ControleSessaoInterface;
import interfaces.VagaInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author tuca
 */
public class NovaVaga extends Janela {

    JLabel lblTitulo, lblTituloVaga, lblLocalizacao, lblDescricao, lblSalario, lblRequisitos, lblAreaAtuacao, lblIdAreaAtuacao;
    JTextField txtTitulo, txtSalario, txtLocalizacao;
    JComboBox cmbAreaAtuacao;
    JTextArea txtRequisitos, txtDescricao;
    JButton btnSalvar;
    Border border = BorderFactory.createLineBorder(Color.gray);
    DefaultComboBoxModel lstModel;

    public NovaVaga() throws Exception {
        super("Vaga", new Dimension(400, 450));

        lblTitulo = new JLabel("Vaga");
        lblTitulo.setBounds(170, 10, 200, 18);
        add(lblTitulo);

        //Label Título
        lblTituloVaga = new JLabel("Titulo");
        lblTituloVaga.setBounds(20, 45, 50, 18);
        add(lblTituloVaga);

        //Text Titulo
        txtTitulo = new JTextField();
        txtTitulo.setBounds(80, 45, 285, 20);
        add(txtTitulo);

        //Label Localização
        lblLocalizacao = new JLabel("Localização");
        lblLocalizacao.setBounds(20, 80, 80, 18);
        add(lblLocalizacao);

        //Text Localização
        txtLocalizacao = new JTextField();
        txtLocalizacao.setBounds(130, 80, 235, 20);
        add(txtLocalizacao);

        //Label Descricao
        lblDescricao = new JLabel("Descrição");
        lblDescricao.setBounds(20, 110, 100, 18);
        add(lblDescricao);

        //Text Descricao
        txtDescricao = new JTextArea();
        txtDescricao.setBounds(20, 135, 345, 60);
        txtDescricao.setBorder(border);
        add(txtDescricao);

        //Label Salário
        lblSalario = new JLabel("Salário");
        lblSalario.setBounds(20, 210, 100, 18);
        add(lblSalario);

        //Text Salário
        txtSalario = new JTextField();
        txtSalario.setBounds(80, 210, 285, 20);
        add(txtSalario);

        //Label Requisitos
        lblRequisitos = new JLabel("Requisitos");
        lblRequisitos.setBounds(20, 240, 100, 18);
        add(lblRequisitos);

        //Text Requisitos
        txtRequisitos = new JTextArea();
        txtRequisitos.setBounds(20, 265, 345, 60);
        txtRequisitos.setBorder(border);
        add(txtRequisitos);


        //Label Área de Atuação
        lblAreaAtuacao = new JLabel("Área de Atuação");
        lblAreaAtuacao.setBounds(20, 340, 100, 18);
        add(lblAreaAtuacao);

        //Instância ListModel
        lstModel = new DefaultComboBoxModel();
        lblIdAreaAtuacao = new JLabel();

        //Text Salário
        cmbAreaAtuacao = new JComboBox(lstModel);
        cmbAreaAtuacao.setBounds(130, 340, 235, 20);
        add(cmbAreaAtuacao);
        CarregarListAreaAtuacao();


        //Button Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(155, 375, 80, 23);
        btnSalvar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            inserir();
                        } catch (Exception ex) {
                            Logger.getLogger(NovaVaga.class.getName()).log(Level.SEVERE,
                                    null, ex);
                        }
                    }
                });
        btnSalvar.setMnemonic('S');
        add(btnSalvar);

    }

    private void CarregarListAreaAtuacao() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        AreaAtuacaoInterface bean = (AreaAtuacaoInterface) ctx.lookup("AreaAtuacaoBean");
        List<AreaAtuacao> lstAreaAtuacao = bean.listarAreaAtuacao();
        InserirListAreaAtuacao(lstAreaAtuacao);
    }

    private void InserirListAreaAtuacao(List<AreaAtuacao> lstAreaAtuacao) {
        for (Iterator iter = lstAreaAtuacao.iterator(); iter.hasNext();) {
            AreaAtuacao area = (AreaAtuacao) iter.next();
            if (area != null) {
                lstModel.addElement(area.getId() + "-" + area.getDescricao());
            }
        }
    }

    public void inserir() throws Exception {
        String[] s = cmbAreaAtuacao.getSelectedItem().toString().split("-");
        lblIdAreaAtuacao.setText(s[0]);

        System.out.println("-----\nIniciando inserção da vaga");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        VagaInterface bean = (VagaInterface) ctx.lookup("VagaBean");
        Vaga vaga = new Vaga();
        System.out.println("Setando as variaveis");
        vaga.setTitulo(txtTitulo.getText());
        vaga.setLocalizacao(txtLocalizacao.getText());
        vaga.setDescricao(txtDescricao.getText());
        vaga.setRequisitos(txtRequisitos.getText());
        vaga.setSalario(Double.parseDouble(txtSalario.getText()));
        vaga.setAreaatuacao(Long.parseLong(lblIdAreaAtuacao.getText()));
        vaga.setEmpresa(carregarEmpresa().getId());
        bean.inserir(vaga);
        System.out.println("Vaga inserida!\n-----");

        JOptionPane.showMessageDialog(null, "Vaga cadastrada com sucesso!", "Vaga", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
    }

    public void limpaRegistros() {
        txtTitulo.setText(null);
        txtSalario.setText(null);
        txtLocalizacao.setText(null);
        txtRequisitos.setText(null);
        txtDescricao.setText(null);
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
