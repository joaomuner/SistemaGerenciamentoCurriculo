/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.Empresa;
import interfaces.EmpresaInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.*;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

/**
 *
 * @author tuca
 */
public class EditarEmpresa extends Janela {

    JLabel jltitulo, jlempresa, jlidEmpresa, jlNome, jlDescricao, jlEmail, jlTelefone, jlEndereco;
    JTextField jtfNome, jtfDescricao, jtfEmail, jtfTelefone, jtfEndereco;
    JButton jbAlterar;
    JList lstEmpresa;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlEmpresa, pnlEmpresaVisivel;
    Border border = BorderFactory.createLineBorder(Color.black);

    public EditarEmpresa() throws Exception {
        super("Empresa", new Dimension(460, 400));

        jltitulo = new JLabel("Empresa");
        jltitulo.setBounds(180, 10, 200, 18);
        add(jltitulo);

        //Instância ListModel
        lstModel = new DefaultListModel();

        //List Empresa
        lstEmpresa = new JList(lstModel);
        lstEmpresa.setBounds(10, 50, 200, 300);
        lstEmpresa.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scroll = new JScrollPane(lstEmpresa);
        scroll.setPreferredSize(new Dimension(200, 300));
        scroll.setBounds(10, 50, 200, 300);
        add(scroll, BorderLayout.CENTER);
        CarregarListEmpresa();

        lstEmpresa.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstEmpresa.isFocusOwner()) {
                    try {
                        CarregarEmpresaSelecionada();
                    } catch (Exception ex) {
                        Logger.getLogger(EditarEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        pnlEmpresa = new JPanel();
        pnlEmpresa.setBounds(230, 50, 200, 300);
        pnlEmpresa.setBorder(border);
        pnlEmpresa.setLayout(null);
        add(pnlEmpresa);

        pnlEmpresaVisivel = new JPanel();
        pnlEmpresaVisivel.setBounds(0, 0, 200, 300);
        pnlEmpresaVisivel.setLayout(null);
        pnlEmpresaVisivel.setBorder(border);
        pnlEmpresa.add(pnlEmpresaVisivel);
        pnlEmpresaVisivel.setVisible(false);



        jlempresa = new JLabel("Nome Empresa:");
        jlempresa.setBounds(5, 0, 187, 20);
        jlempresa.setLayout(null);
        pnlEmpresaVisivel.add(jlempresa);

        jtfNome = new JTextField();
        jtfNome.setBounds(5, 25, 187, 20);
        jtfNome.setLayout(null);
        pnlEmpresaVisivel.add(jtfNome);

        jlDescricao = new JLabel("Descrição:");
        jlDescricao.setBounds(5, 50, 187, 20);
        jlDescricao.setLayout(null);
        pnlEmpresaVisivel.add(jlDescricao);

        jtfDescricao = new JTextField();
        jtfDescricao.setBounds(5, 75, 187, 20);
        jtfDescricao.setLayout(null);
        pnlEmpresaVisivel.add(jtfDescricao);

        jlEmail = new JLabel("Email:");
        jlEmail.setBounds(5, 100, 187, 20);
        jlEmail.setLayout(null);
        pnlEmpresaVisivel.add(jlEmail);

        jtfEmail = new JTextField();
        jtfEmail.setBounds(5, 125, 187, 20);
        jtfEmail.setLayout(null);
        jtfEmail.enable(false);
        jtfEmail.setDisabledTextColor(Color.gray);
        pnlEmpresaVisivel.add(jtfEmail);

        jlTelefone = new JLabel("Telefone:");
        jlTelefone.setBounds(5, 150, 187, 20);
        jlTelefone.setLayout(null);
        pnlEmpresaVisivel.add(jlTelefone);

        jtfTelefone = new JTextField();
        jtfTelefone.setBounds(5, 175, 187, 20);
        jtfTelefone.setLayout(null);
        pnlEmpresaVisivel.add(jtfTelefone);

        jlEndereco = new JLabel("Endereço:");
        jlEndereco.setBounds(5, 200, 187, 20);
        jlEndereco.setLayout(null);
        pnlEmpresaVisivel.add(jlEndereco);

        jtfEndereco = new JTextField();
        jtfEndereco.setBounds(5, 225, 187, 20);
        jtfEndereco.setLayout(null);
        pnlEmpresaVisivel.add(jtfEndereco);

        jlidEmpresa = new JLabel();
        jlidEmpresa.setBounds(5, 0, 187, 20);
        jlidEmpresa.setLayout(null);
        pnlEmpresaVisivel.add(jlidEmpresa);
        jlidEmpresa.setVisible(false);

        jbAlterar = new JButton("Alterar");
        jbAlterar.setBounds(60, 260, 80, 20);
        jbAlterar.setLayout(null);
        jbAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Alterar();
                } catch (Exception ex) {
                    Logger.getLogger(EditarEmpresa.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação desejada!");
                }
            }
        });
        jbAlterar.setMnemonic('A');
        pnlEmpresaVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {
        System.out.println("-----\nIniciando busca da empresa");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        System.out.println("Resgatando a empresa");
        Empresa empresa = bean.procurarEmpresa(Long.parseLong(jlidEmpresa.getText()));
        System.out.println("Settando variaveis");
        empresa.setNome(jtfNome.getText());
        empresa.setDescricao(jtfDescricao.getText());
        empresa.setEmail(jtfEmail.getText());
        empresa.setTelefone(jtfTelefone.getText());
        empresa.setEndereco(jtfEndereco.getText());
        System.out.println("Atualizando empresa");
        bean.atualizar(empresa);

        //Recarrega o list atualizado
        limparRegistro();
        CarregarListEmpresa();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Empresa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limparRegistro() {
        jtfNome.setText(null);
        jtfDescricao.setText(null);
        jtfEmail.setText(null);
        jtfTelefone.setText(null);
        jtfEndereco.setText(null);
    }

    private void CarregarEmpresaSelecionada() throws Exception {
        pnlEmpresaVisivel.setVisible(true);
        String[] s = lstEmpresa.getSelectedValue().toString().split("-");
        jlidEmpresa.setText(s[0]);

        System.out.println("-----\nIniciando busca da empresa");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        System.out.println("Resgatando a empresa");
        Empresa empresa = bean.procurarEmpresa(Long.parseLong(jlidEmpresa.getText()));
        jtfNome.setText(empresa.getNome());
        jtfDescricao.setText(empresa.getDescricao());
        jtfEmail.setText(empresa.getEmail());
        jtfTelefone.setText(empresa.getTelefone());
        jtfEndereco.setText(empresa.getEndereco());
    }

    private void CarregarListEmpresa() throws Exception {
        //Zera o JList para uma nova consulta
        if (lstModel != null) {
            lstModel.removeAllElements();
        }

        System.out.println("-----\nIniciando busca das empresas");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        System.out.println("Resgatando as empresas");
        List<Empresa> lstEmpresa = bean.listarEmpresa();
        InserirListEmpresa(lstEmpresa);
    }

    private void InserirListEmpresa(List<Empresa> empresa) {
        for (Iterator iter = empresa.iterator(); iter.hasNext();) {

            Empresa empr = (Empresa) iter.next();
            if (empr != null) {
                lstModel.addElement(empr.getId() + "-" + empr.getNome());
            }
        }
    }

}
