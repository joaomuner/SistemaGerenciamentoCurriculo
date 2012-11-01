/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import menu.Janela;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import conexao.entity.Universidade;

/**
 *
 * @author tuca
 */
public class EditarUniversidade extends Janela {
    
    JLabel jltitulo, jluniversidade, jliduniversidade;
    JTextField jtfuniversidade;
    JButton jbAlterar;
    JList lstUniversidade;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlUniversidade, pnlUniversidadeVisivel;
    Border border = BorderFactory.createLineBorder(Color.black);

    public EditarUniversidade() throws Exception {
        super("Universidade", new Dimension(460, 250));

        jltitulo = new JLabel("Universidade");
        jltitulo.setBounds(170, 10, 200, 18);
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

        jluniversidade = new JLabel("Universidade:");
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
                    Logger.getLogger(EditarAtuacao.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação desejada!");
                }
            }
        });
        pnlUniversidadeVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure(
                "/hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Universidade universidade = (Universidade) session.get(Universidade.class, Long.parseLong(jliduniversidade.getText()));

        universidade.setNome(jtfuniversidade.getText());

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
        
        //Atualiza o list
        CarregarListUniversidade();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
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

        Configuration cfg = new AnnotationConfiguration();
        cfg.configure(
                "/hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        List<Universidade> lstUniversidade = (List<Universidade>) session.createCriteria(Universidade.class).list();

        InserirListUniversidade(lstUniversidade);

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
    }

    private void InserirListUniversidade(List<Universidade> areaAtuacao) {
        for (Iterator iter = areaAtuacao.iterator(); iter.hasNext();) {

            Universidade universidade = (Universidade) iter.next();
            if (universidade != null) {
                lstModel.addElement(universidade.getId() + "-" + universidade.getNome());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        new EditarUniversidade().setVisible(true);
    }
}
