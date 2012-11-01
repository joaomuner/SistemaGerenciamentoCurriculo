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
import conexao.entity.Empresa;
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
        jltitulo.setBounds(170, 10, 200, 18);
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
                    CarregarEmpresaSelecionada();
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
        
        //Moor...rapidinho, posso atrapalhar?
        //logico... tivpooce..voce nunca atrapalhaaa
        //acessa meu team viewer?
        //acesso.. rapidinho voce agora... olha aqui...
        
        jlidEmpresa = new JLabel();
        jlidEmpresa.setBounds(5, 0, 187, 20);
        jlidEmpresa.setLayout(null);
        pnlEmpresaVisivel.add(jlidEmpresa);
        jlidEmpresa.setVisible(false);

        jbAlterar = new JButton("Alterar");
        jbAlterar.setBounds(60, 250, 80, 20);
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
        pnlEmpresaVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure(
                "/hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Empresa empresa = (Empresa) session.get(Empresa.class, Long.parseLong(jlidEmpresa.getText()));

        empresa.setNome(jtfNome.getText());
        empresa.setDescricao(jtfDescricao.getText());
        empresa.setEmail(jtfEmail.getText());
        empresa.setTelefone(jtfTelefone.getText());
        empresa.setEndereco(jtfEndereco.getText());

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
        
        //Atualiza o list
        CarregarListEmpresa();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
    }

    private void CarregarEmpresaSelecionada() {
        pnlEmpresaVisivel.setVisible(true);
        String[] s = lstEmpresa.getSelectedValue().toString().split("-");
        jlidEmpresa.setText(s[0]);
        
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure(
                "/hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Empresa emp = (Empresa) session.get(Empresa.class, Long.parseLong(jlidEmpresa.getText()));
        
        jtfNome.setText(emp.getNome());
        jtfDescricao.setText(emp.getDescricao());
        jtfEmail.setText(emp.getEmail());
        jtfTelefone.setText(emp.getTelefone());
        jtfEndereco.setText(emp.getEndereco());

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
        
    }

    private void CarregarListEmpresa() throws Exception {
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

        List<Empresa> lstEmpr = (List<Empresa>) session.createCriteria(Empresa.class).list();

        InserirListEmpresa(lstEmpr);

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
    }

    private void InserirListEmpresa(List<Empresa> empresa) {
        for (Iterator iter = empresa.iterator(); iter.hasNext();) {

            Empresa empr = (Empresa) iter.next();
            if (empr != null) {
                lstModel.addElement(empr.getId() + "-" + empr.getNome());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        new EditarEmpresa().setVisible(true);
    }
}
