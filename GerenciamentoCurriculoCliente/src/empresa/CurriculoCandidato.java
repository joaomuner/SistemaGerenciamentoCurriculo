/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import Util.Janela;
import entidade.Candidato;
import entidade.ExperienciaAnterior;
import entidade.Temp;
import interfaces.CandidatoInterface;
import interfaces.ExperienciaInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author tuca
 */
public class CurriculoCandidato extends Janela {

    Border border = BorderFactory.createLineBorder(Color.gray);
    JLabel lblTitulo, lblNome, lblEmail, lblCPF, lblTel, lblRG, lblDataNascimento, lblEndereco, lblBairro, lblCidade, lblCEP, lblSexo, lblObjProfissional, lblSalario, lblAreaAtuacaoDesejada, lblPerfilProfissional, lblGrauEscolaridade, lblInstituicaoEnsino, lblAnoConclusao, lblExperiencias;
    JTextArea txtNome, txtEmail, txtCPF, txtTel, txtRG, txtDataNascimento, txtEndereco, txtBairro, txtCidade, txtCEP, txtSexo, txtObjProfissional, txtSalario, txtAreaAtuacaoDesejada, txtPerfilProfissional, txtGrauEscolaridade, txtInstituicaoEnsino, txtAnoConclusao;
    JPanel pnlLstExperiencias;
    JScrollPane scrollExperiencias;
    int alturaPnl;

    public CurriculoCandidato() throws Exception {
        super("Currículo Candidato", new Dimension(800, 550));


        lblTitulo = new JLabel("Currículo Candidato");
        lblTitulo.setBounds(150, 10, 200, 18);
        add(lblTitulo);

        //Label Nome
        lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 45, 150, 18);
        add(lblNome);

        //Text Nome
        txtNome = new JTextArea();
        txtNome.setBounds(160, 45, 285, 20);
        txtNome.setEnabled(false);
        txtNome.setDisabledTextColor(Color.gray);
        txtNome.setBorder(border);
        add(txtNome);

        //Label Email
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 70, 150, 18);
        add(lblEmail);

        //Text Email
        txtEmail = new JTextArea();
        txtEmail.setBounds(160, 70, 285, 20);
        txtEmail.setEnabled(false);
        txtEmail.setDisabledTextColor(Color.gray);
        txtEmail.setBorder(border);
        add(txtEmail);

        //Label CPF
        lblCPF = new JLabel("CPF");
        lblCPF.setBounds(10, 95, 150, 18);
        add(lblCPF);

        //Text CPF
        txtCPF = new JTextArea();
        txtCPF.setBounds(160, 95, 285, 20);
        txtCPF.setEnabled(false);
        txtCPF.setDisabledTextColor(Color.gray);
        txtCPF.setBorder(border);
        add(txtCPF);

        //Label Telefone
        lblTel = new JLabel("Tel.");
        lblTel.setBounds(10, 120, 150, 18);
        add(lblTel);

        //Text Telefone
        txtTel = new JTextArea();
        txtTel.setBounds(160, 120, 285, 20);
        txtTel.setEnabled(false);
        txtTel.setDisabledTextColor(Color.gray);
        txtTel.setBorder(border);
        add(txtTel);


        //Label RG
        lblRG = new JLabel("RG");
        lblRG.setBounds(10, 145, 150, 18);
        add(lblRG);

        //Text RG
        txtRG = new JTextArea();
        txtRG.setBounds(160, 145, 285, 20);
        txtRG.setEnabled(false);
        txtRG.setDisabledTextColor(Color.gray);
        txtRG.setBorder(border);
        add(txtRG);

        //Label Data Nascimento
        lblDataNascimento = new JLabel("Data Nascimento");
        lblDataNascimento.setBounds(10, 170, 150, 18);
        add(lblDataNascimento);

        //Text Data Nascimento
        txtDataNascimento = new JTextArea();
        txtDataNascimento.setBounds(160, 170, 285, 20);
        txtDataNascimento.setEnabled(false);
        txtDataNascimento.setDisabledTextColor(Color.gray);
        txtDataNascimento.setBorder(border);
        add(txtDataNascimento);

        //Label Endereco
        lblEndereco = new JLabel("Endereco");
        lblEndereco.setBounds(10, 195, 150, 18);
        add(lblEndereco);

        //Text Endereco
        txtEndereco = new JTextArea();
        txtEndereco.setBounds(160, 195, 285, 20);
        txtEndereco.setEnabled(false);
        txtEndereco.setDisabledTextColor(Color.gray);
        txtEndereco.setBorder(border);
        add(txtEndereco);

        //Label Bairro
        lblBairro = new JLabel("Bairro");
        lblBairro.setBounds(20, 220, 150, 18);
        add(lblBairro);

        //Text Bairro
        txtBairro = new JTextArea();
        txtBairro.setBounds(160, 220, 285, 20);
        txtBairro.setEnabled(false);
        txtBairro.setDisabledTextColor(Color.gray);
        txtBairro.setBorder(border);
        add(txtBairro);

        //Label Cidade
        lblCidade = new JLabel("Cidade");
        lblCidade.setBounds(10, 245, 150, 18);
        add(lblCidade);

        //Text Cidade
        txtCidade = new JTextArea();
        txtCidade.setBounds(160, 245, 285, 20);
        txtCidade.setEnabled(false);
        txtCidade.setDisabledTextColor(Color.gray);
        txtCidade.setBorder(border);
        add(txtCidade);
        
        //Label CEP
        lblCEP = new JLabel("CEP");
        lblCEP.setBounds(10, 270, 150, 18);
        add(lblCEP);

        //Text CEP
        txtCEP = new JTextArea();
        txtCEP.setBounds(160, 270, 285, 20);
        txtCEP.setEnabled(false);
        txtCEP.setDisabledTextColor(Color.gray);
        txtCEP.setBorder(border);
        add(txtCEP);

        //Label Sexo
        lblSexo = new JLabel("Sexo");
        lblSexo.setBounds(10, 295, 150, 18);
        add(lblSexo);

        //Text Sexo
        txtSexo = new JTextArea();
        txtSexo.setBounds(160, 295, 285, 20);
        txtSexo.setEnabled(false);
        txtSexo.setDisabledTextColor(Color.gray);
        txtSexo.setBorder(border);
        add(txtSexo);

        //Label Objetivo Profissional
        lblObjProfissional = new JLabel("Objetivo Profissional");
        lblObjProfissional.setBounds(10, 320, 150, 18);
        add(lblObjProfissional);

        //Text Objetivo Profissional
        txtObjProfissional = new JTextArea();
        txtObjProfissional.setBounds(160, 320, 285, 20);
        txtObjProfissional.setEnabled(false);
        txtObjProfissional.setDisabledTextColor(Color.gray);
        txtObjProfissional.setBorder(border);
        add(txtObjProfissional);

        //Label Salario
        lblSalario = new JLabel("Salario");
        lblSalario.setBounds(10, 345, 150, 18);
        add(lblSalario);

        //Text Salario
        txtSalario = new JTextArea();
        txtSalario.setBounds(160, 345, 285, 20);
        txtSalario.setEnabled(false);
        txtSalario.setDisabledTextColor(Color.gray);
        txtSalario.setBorder(border);
        add(txtSalario);

        //Label Area Atuacao
        lblAreaAtuacaoDesejada = new JLabel("Area de Atuacao");
        lblAreaAtuacaoDesejada.setBounds(10, 370, 150, 18);
        add(lblAreaAtuacaoDesejada);

        //Text Area Atuacao
        txtAreaAtuacaoDesejada = new JTextArea();
        txtAreaAtuacaoDesejada.setBounds(160, 370, 285, 20);
        txtAreaAtuacaoDesejada.setEnabled(false);
        txtAreaAtuacaoDesejada.setDisabledTextColor(Color.gray);
        txtAreaAtuacaoDesejada.setBorder(border);
        add(txtAreaAtuacaoDesejada);

        //Label Perfil Profissional
        lblPerfilProfissional = new JLabel("Perfil Profissional");
        lblPerfilProfissional.setBounds(10, 395, 150, 18);
        add(lblPerfilProfissional);

        //Text Perfil Profissional
        txtPerfilProfissional = new JTextArea();
        txtPerfilProfissional.setBounds(160, 395, 285, 20);
        txtPerfilProfissional.setEnabled(false);
        txtPerfilProfissional.setDisabledTextColor(Color.gray);
        txtPerfilProfissional.setBorder(border);
        add(txtPerfilProfissional);

        //Label Grau Escolaridade
        lblGrauEscolaridade = new JLabel("Grau Escolaridade");
        lblGrauEscolaridade.setBounds(10, 420, 150, 18);
        add(lblGrauEscolaridade);

        //Text Grau Escolaridade
        txtGrauEscolaridade = new JTextArea();
        txtGrauEscolaridade.setBounds(160, 420, 285, 20);
        txtGrauEscolaridade.setEnabled(false);
        txtGrauEscolaridade.setDisabledTextColor(Color.gray);
        txtGrauEscolaridade.setBorder(border);
        add(txtGrauEscolaridade);

        //Label Instituicao Ensino
        lblInstituicaoEnsino = new JLabel("Instituicao de Ensino");
        lblInstituicaoEnsino.setBounds(10, 445, 150, 18);
        add(lblInstituicaoEnsino);

        //Text Instituicao Ensino
        txtInstituicaoEnsino = new JTextArea();
        txtInstituicaoEnsino.setBounds(160, 445, 285, 20);
        txtInstituicaoEnsino.setEnabled(false);
        txtInstituicaoEnsino.setDisabledTextColor(Color.gray);
        txtInstituicaoEnsino.setBorder(border);
        add(txtInstituicaoEnsino);

        //Label Ano Conclusao
        lblAnoConclusao = new JLabel("Ano Conclusao");
        lblAnoConclusao.setBounds(10, 470, 150, 18);
        add(lblAnoConclusao);

        //Text Ano Conclusao
        txtAnoConclusao = new JTextArea();
        txtAnoConclusao.setBounds(160, 470, 285, 20);
        txtAnoConclusao.setEnabled(false);
        txtAnoConclusao.setDisabledTextColor(Color.gray);
        txtAnoConclusao.setBorder(border);
        add(txtAnoConclusao);

        //Label Experiencias
        lblExperiencias = new JLabel("Experiências Anteriores");
        lblExperiencias.setBounds(460, 40, 150, 20);
        add(lblExperiencias);

        pnlLstExperiencias = new JPanel();
        pnlLstExperiencias.setBounds(460, 70, 297, 420);
        pnlLstExperiencias.setLayout(null);
        pnlLstExperiencias.setBackground(Color.white);
        pnlLstExperiencias.setPreferredSize(new Dimension(297, 420));
        //Scroll para o Panel
        scrollExperiencias = new JScrollPane(pnlLstExperiencias);
        scrollExperiencias.setPreferredSize(new Dimension(315, 420));
        scrollExperiencias.setBounds(460, 70, 315, 420);
        scrollExperiencias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollExperiencias.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollExperiencias);

    }

    public void CarregarCurriculo(Long idCandidato) throws Exception {
        Candidato c = carregarCandidato(idCandidato);

        txtNome.setText(c.getEmail());
        txtNome.setText(c.getNome());
        txtEmail.setText(c.getEmail());
        if (c.getCPF() != null) {
            txtCPF.setText(c.getCPF().toString());
        }
        txtTel.setText(c.getTelefone());
        if (c.getRG() != null) {
            txtRG.setText(c.getRG().toString());
        }
        txtDataNascimento.setText(c.getDatanascimento());
        txtEndereco.setText(c.getEndereco());
        txtBairro.setText(c.getBairro());
        txtCidade.setText(c.getCidade());
        if (c.getCEP() != null) {
            txtCEP.setText(c.getCEP().toString());
        }
        txtSexo.setText(c.getSexo());
        txtObjProfissional.setText(c.getObjetivoprofissional());
        txtSalario.setText(String.valueOf(c.getSalario()));
        txtAreaAtuacaoDesejada.setText(c.getAreaatuacao());
        txtPerfilProfissional.setText(c.getPerfilprofissional());
        txtGrauEscolaridade.setText(c.getGrauensino());
        txtInstituicaoEnsino.setText(c.getInstitutoensino());
        txtAnoConclusao.setText(c.getConclusao());

        //Carregar experiências
        List<ExperienciaAnterior> lstExperiencias = carregarExperiencias(c.getId());

        if (lstExperiencias != null) {
            int posicaoPainelY = 5;
            for (ExperienciaAnterior experiencia : lstExperiencias) {
                int posicaoY = 5;

                JPanel pnlExperiencias = new JPanel();
                pnlExperiencias.setBounds(5, posicaoPainelY, 285, 165);
                pnlExperiencias.setBorder(border);
                pnlExperiencias.setLayout(null);
                pnlLstExperiencias.add(pnlExperiencias);

                //Experiencia Anterior - Nome Empresa
                JLabel lblNomeEmpresa = new JLabel("Empresa: ");
                lblNomeEmpresa.setBounds(5, posicaoY, 100, 20);
                pnlExperiencias.add(lblNomeEmpresa);

                JTextArea txtNomeEmpresa = new JTextArea(experiencia.getIdEmpresa().toString());
                txtNomeEmpresa.setBounds(124, posicaoY, 153, 20);
                txtNomeEmpresa.setEnabled(false);
                txtNomeEmpresa.setDisabledTextColor(Color.gray);
                txtNomeEmpresa.setBorder(border);
                pnlExperiencias.add(txtNomeEmpresa);

                posicaoY += 20;
                //Experiencia Anterior - Descrição de Atividade
                JLabel lblDescricaoAtv = new JLabel("Descrição de Atividade: ");
                lblDescricaoAtv.setBounds(5, posicaoY, 150, 20);
                pnlExperiencias.add(lblDescricaoAtv);

                posicaoY += 20;
                //Experiencia Anterior - Text Descrição Atv
                JTextArea txtDescricaoAtv = new JTextArea(experiencia.getDescricaoAtv());
                txtDescricaoAtv.setBounds(5, posicaoY, 271, 60);
                txtDescricaoAtv.setEnabled(false);
                txtDescricaoAtv.setDisabledTextColor(Color.gray);
                txtDescricaoAtv.setBorder(border);
                pnlExperiencias.add(txtDescricaoAtv);

                posicaoY += 65;
                //Experiencia Anterior - Data Incio
                JLabel lblDataInicio = new JLabel("Data Contratação");
                lblDataInicio.setBounds(5, posicaoY, 100, 20);
                pnlExperiencias.add(lblDataInicio);

                JTextArea txtDataInicio = new JTextArea(experiencia.getDataInicio());
                txtDataInicio.setBounds(124, posicaoY, 153, 20);
                txtDataInicio.setEnabled(false);
                txtDataInicio.setDisabledTextColor(Color.gray);
                txtDataInicio.setBorder(border);
                pnlExperiencias.add(txtDataInicio);

                posicaoY += 25;
                //Experiencia Anterior - Data Fim
                JLabel lblDataFim = new JLabel("Data Final");
                lblDataFim.setBounds(5, posicaoY, 100, 20);
                pnlExperiencias.add(lblDataFim);

                JTextArea txtDataFim = new JTextArea(experiencia.getDataFim());
                txtDataFim.setBounds(124, posicaoY, 153, 20);
                txtDataFim.setEnabled(false);
                txtDataFim.setDisabledTextColor(Color.gray);
                txtDataFim.setBorder(border);
                pnlExperiencias.add(txtDataFim);

                posicaoPainelY += 175;

                alturaPnl += 180;
            }

            if (alturaPnl > 400) {
                System.out.println("altura: " + alturaPnl);
                pnlLstExperiencias.setPreferredSize(new Dimension(297, alturaPnl));
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

    private List<ExperienciaAnterior> carregarExperiencias(Long idCandidato) throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        ExperienciaInterface bean = (ExperienciaInterface) ctx.lookup("ExperienciaBean");
        System.out.println("IdCandidato:" + idCandidato);
        return bean.listarExperienciasCandidato(idCandidato);
    }
}
