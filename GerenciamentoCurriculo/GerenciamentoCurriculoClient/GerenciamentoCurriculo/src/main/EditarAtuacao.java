/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import menu.Janela;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import conexao.entity.AreaAtuacao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

/**
 *
 * @author tuca
 */
public class EditarAtuacao extends Janela {

    JLabel jltitulo, jlarea, jlidArea;
    JTextField jtfarea;
    JButton jbAlterar;
    JList lstAtuacao;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlAtuacao, pnlAtuacaoVisivel;
    Border border = BorderFactory.createLineBorder(Color.black);

    public EditarAtuacao() throws Exception {
        super("Área de Atuação", new Dimension(460, 250));

        jltitulo = new JLabel("Área de atuação");
        jltitulo.setBounds(170, 10, 200, 18);
        add(jltitulo);

        //Instância ListModel
        lstModel = new DefaultListModel();

        //List Atuacao
        lstAtuacao = new JList(lstModel);
        lstAtuacao.setBounds(10, 50, 200, 150);
        lstAtuacao.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scroll = new JScrollPane(lstAtuacao);
        scroll.setPreferredSize(new Dimension(200, 150));
        scroll.setBounds(10, 50, 200, 150);
        add(scroll, BorderLayout.CENTER);
        CarregarListAtuacao();

        lstAtuacao.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstAtuacao.isFocusOwner()) {
                    CarregarAreaAtuacaoSelecionada();
                }
            }
        });

        pnlAtuacao = new JPanel();
        pnlAtuacao.setBounds(230, 50, 200, 150);
        pnlAtuacao.setBorder(border);
        pnlAtuacao.setLayout(null);
        add(pnlAtuacao);

        pnlAtuacaoVisivel = new JPanel();
        pnlAtuacaoVisivel.setBounds(0, 0, 200, 150);
        pnlAtuacaoVisivel.setLayout(null);
        pnlAtuacaoVisivel.setBorder(border);
        pnlAtuacao.add(pnlAtuacaoVisivel);
        pnlAtuacaoVisivel.setVisible(false);

        jlarea = new JLabel("Área de Atuação:");
        jlarea.setBounds(5, 0, 187, 20);
        jlarea.setLayout(null);
        pnlAtuacaoVisivel.add(jlarea);

        jtfarea = new JTextField();
        jtfarea.setBounds(5, 25, 187, 20);
        jtfarea.setLayout(null);
        pnlAtuacaoVisivel.add(jtfarea);
        
        jlidArea = new JLabel();
        jlidArea.setBounds(5, 0, 187, 20);
        jlidArea.setLayout(null);
        pnlAtuacaoVisivel.add(jlidArea);
        jlidArea.setVisible(false);

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
        pnlAtuacaoVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure(
                "/hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        AreaAtuacao areaAtuacao = (AreaAtuacao) session.get(AreaAtuacao.class, Long.parseLong(jlidArea.getText()));

        areaAtuacao.setDescricao(jtfarea.getText());

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
        
        //Atualiza o list
        CarregarListAtuacao();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
    }

    private void CarregarAreaAtuacaoSelecionada() {
        pnlAtuacaoVisivel.setVisible(true);
        String[] s = lstAtuacao.getSelectedValue().toString().split("-");
        jtfarea.setText(s[1]);
        jlidArea.setText(s[0]);
    }

    private void CarregarListAtuacao() throws Exception {
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

        List<AreaAtuacao> lstAreaAtuacao = (List<AreaAtuacao>) session.createCriteria(AreaAtuacao.class).list();

        InserirListAtuacao(lstAreaAtuacao);

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
    }

    private void InserirListAtuacao(List<AreaAtuacao> areaAtuacao) {
        for (Iterator iter = areaAtuacao.iterator(); iter.hasNext();) {

            AreaAtuacao area = (AreaAtuacao) iter.next();
            if (area != null) {
                lstModel.addElement(area.getId() + "-" + area.getDescricao());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        new EditarAtuacao().setVisible(true);
    }
}
