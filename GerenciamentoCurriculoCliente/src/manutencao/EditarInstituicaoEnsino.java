/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.Universidade;
import interfaces.UniversidadeInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class EditarInstituicaoEnsino extends Janela {

    JLabel jltitulo, jluniversidade, jliduniversidade;
    JTextField jtfuniversidade;
    JButton jbAlterar;
    JList lstUniversidade;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlUniversidade, pnlUniversidadeVisivel;
    Border border = BorderFactory.createLineBorder(Color.black);

    public EditarInstituicaoEnsino() throws Exception {
        super("Instituição de Ensino", new Dimension(460, 250));

        jltitulo = new JLabel("Instituição de Ensino");
        jltitulo.setBounds(150, 10, 200, 18);
        add(jltitulo);

        //Instância ListModel
        lstModel = new DefaultListModel();

        //List Universidade
        lstUniversidade = new JList(lstModel);
        lstUniversidade.setBounds(10, 50, 200, 150);
        lstUniversidade.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scroll = new JScrollPane(lstUniversidade);
        scroll.setPreferredSize(new Dimension(200, 150));
        scroll.setBounds(10, 50, 200, 150);
        add(scroll, BorderLayout.CENTER);
        CarregarListUniversidade();

        lstUniversidade.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstUniversidade.isFocusOwner()) {
                    CarregarAreaAtuacaoSelecionada();
                }
            }
        });

        pnlUniversidade = new JPanel();
        pnlUniversidade.setBounds(230, 50, 200, 150);
        pnlUniversidade.setBorder(border);
        pnlUniversidade.setLayout(null);
        add(pnlUniversidade);

        pnlUniversidadeVisivel = new JPanel();
        pnlUniversidadeVisivel.setBounds(0, 0, 200, 150);
        pnlUniversidadeVisivel.setLayout(null);
        pnlUniversidadeVisivel.setBorder(border);
        pnlUniversidade.add(pnlUniversidadeVisivel);
        pnlUniversidadeVisivel.setVisible(false);

        jluniversidade = new JLabel("Instituição:");
        jluniversidade.setBounds(5, 0, 187, 20);
        jluniversidade.setLayout(null);
        pnlUniversidadeVisivel.add(jluniversidade);

        jtfuniversidade = new JTextField();
        jtfuniversidade.setBounds(5, 25, 187, 20);
        jtfuniversidade.setLayout(null);
        pnlUniversidadeVisivel.add(jtfuniversidade);

        jliduniversidade = new JLabel();
        jliduniversidade.setBounds(5, 0, 187, 20);
        jliduniversidade.setLayout(null);
        pnlUniversidadeVisivel.add(jliduniversidade);
        jliduniversidade.setVisible(false);

        jbAlterar = new JButton("Alterar");
        jbAlterar.setBounds(60, 80, 80, 20);
        jbAlterar.setLayout(null);
        jbAlterar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Alterar();
                } catch (Exception ex) {
                    Logger.getLogger(EditarInstituicaoEnsino.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação desejada!");
                }
            }
        });
        jbAlterar.setMnemonic('A');
        pnlUniversidadeVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {

        System.out.println("-----\nIniciando busca da universidade");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        UniversidadeInterface bean = (UniversidadeInterface) ctx.lookup("UniversidadeBean");
        System.out.println("Resgatando a universidade");
        Universidade universidade = bean.procurarUniversidade(Long.parseLong(jliduniversidade.getText()));
        System.out.println("Settando novo nome");
        universidade.setNome(jtfuniversidade.getText().toUpperCase());
        System.out.println("Atualizando a universidade");
        bean.atualizar(universidade);

        //Recarrega o list atualizado
        limparRegistro();
        CarregarListUniversidade();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Instituição de Ensino", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limparRegistro() {
        jtfuniversidade.setText(null);
    }

    private void CarregarAreaAtuacaoSelecionada() {
        pnlUniversidadeVisivel.setVisible(true);
        String[] s = lstUniversidade.getSelectedValue().toString().split("-");
        jtfuniversidade.setText(s[1]);
        jliduniversidade.setText(s[0]);
    }

    private void CarregarListUniversidade() throws Exception {
        //Zera o JList para uma nova consulta
        if (lstModel != null) {
            lstModel.removeAllElements();
        }

        System.out.println("-----\nIniciando busca das instituições");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        UniversidadeInterface bean = (UniversidadeInterface) ctx.lookup("UniversidadeBean");
        System.out.println("Resgatando as instituições");
        List<Universidade> lstUniversidade = bean.listarUniversidade();
        InserirListUniversidade(lstUniversidade);
    }

    private void InserirListUniversidade(List<Universidade> lstUniversidade) {
        for (Iterator iter = lstUniversidade.iterator(); iter.hasNext();) {

            Universidade universidade = (Universidade) iter.next();
            if (universidade != null) {
                lstModel.addElement(universidade.getId() + "-" + universidade.getNome());
            }
        }
    }

}
