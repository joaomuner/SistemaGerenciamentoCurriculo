/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.Administrador;
import interfaces.AdministradorInterface;
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
public class EditarAdministrador extends Janela {

    JLabel jltitulo, jlNome, jlidAdmin, jlemail, jlsenha;
    JTextField jtfnome, jtfemail, jtfsenha;
    JButton jbAlterar;
    JList lstAdmin;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlAdmin, pnlAdminVisivel;
    Border border = BorderFactory.createLineBorder(Color.black);

    public EditarAdministrador() throws Exception {
        super("Administrador", new Dimension(460, 250));

        jltitulo = new JLabel("Administrador");
        jltitulo.setBounds(160, 10, 200, 18);
        add(jltitulo);

        //Instância ListModel
        lstModel = new DefaultListModel();

        //List Atuacao
        lstAdmin = new JList(lstModel);
        lstAdmin.setBounds(10, 50, 200, 150);
        lstAdmin.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Scroll para o List
        scroll = new JScrollPane(lstAdmin);
        scroll.setPreferredSize(new Dimension(200, 150));
        scroll.setBounds(10, 50, 200, 150);
        add(scroll, BorderLayout.CENTER);
        CarregarListAdministrador();
        lstAdmin.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (lstAdmin.isFocusOwner()) {
                    try {
                        CarregarAdminSelecionado();
                    } catch (Exception ex) {
                        Logger.getLogger(EditarAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        pnlAdmin = new JPanel();
        pnlAdmin.setBounds(230, 50, 200, 150);
        pnlAdmin.setBorder(border);
        pnlAdmin.setLayout(null);
        add(pnlAdmin);

        pnlAdminVisivel = new JPanel();
        pnlAdminVisivel.setBounds(0, 0, 200, 150);
        pnlAdminVisivel.setLayout(null);
        pnlAdminVisivel.setBorder(border);
        pnlAdmin.add(pnlAdminVisivel);
        pnlAdminVisivel.setVisible(false);

        jlNome = new JLabel("Nome:");
        jlNome.setBounds(5, 0, 187, 20);
        jlNome.setLayout(null);
        pnlAdminVisivel.add(jlNome);

        jtfnome = new JTextField();
        jtfnome.setBounds(5, 25, 187, 20);
        jtfnome.setLayout(null);
        pnlAdminVisivel.add(jtfnome);
        
        jlemail = new JLabel("Email:");
        jlemail.setBounds(5, 50, 187, 20);
        jlemail.setLayout(null);
        pnlAdminVisivel.add(jlemail);

        jtfemail = new JTextField();
        jtfemail.setBounds(5, 75, 187, 20);
        jtfemail.setLayout(null);
        jtfemail.enable(false);
        jtfemail.setDisabledTextColor(Color.gray);
        pnlAdminVisivel.add(jtfemail);

        jlidAdmin = new JLabel();
       
        jbAlterar = new JButton("Alterar");
        jbAlterar.setBounds(65, 110, 80, 20);
        jbAlterar.setLayout(null);
        jbAlterar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            Alterar();
                        } catch (Exception ex) {
                            Logger.getLogger(EditarAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        jbAlterar.setMnemonic('A');
        pnlAdminVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {

        System.out.println("-----\nIniciando busca do administrador");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        System.out.println("Resgatando o administrador");
        Administrador administrador = bean.procurarAdministrador(Long.parseLong(jlidAdmin.getText()));
        System.out.println("Settando novo nome");
        administrador.setNome(jtfnome.getText());
        administrador.setEmail(jtfemail.getText());
        System.out.println("Atualizando administrador");
        bean.atualizar(administrador);

        //Recarrega o list atualizado
        limparRegistro();
        CarregarListAdministrador();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Administrador", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limparRegistro(){
        jtfnome.setText(null);
        jtfemail.setText(null);
    }
   
    private void CarregarAdminSelecionado() throws Exception {
        pnlAdminVisivel.setVisible(true);
        String[] s = lstAdmin.getSelectedValue().toString().split("-");
        jtfnome.setText(s[1]);
        jlidAdmin.setText(s[0]);
        Administrador administrador = buscarAdministrador();
        jtfemail.setText(administrador.getEmail());
    }

     private Administrador buscarAdministrador() throws Exception {
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        System.out.println("Procurando a empresa");
        Administrador administrador = bean.procurarAdministrador(Long.parseLong(jlidAdmin.getText()));
        return administrador;
    }

    private void CarregarListAdministrador() throws Exception {
        //Zera o JList para uma nova consulta
        if (lstModel != null) {
            lstModel.removeAllElements();
        }

        System.out.println("-----\nIniciando busca dos administradores");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AdministradorInterface bean = (AdministradorInterface) ctx.lookup("AdministradorBean");
        System.out.println("Resgatando os administradores");
        List<Administrador> lstAdministrador = bean.listarAdministrador();
        InserirListAdministrador(lstAdministrador);
    }

    private void InserirListAdministrador(List<Administrador> lstAdmin) {
        for (Iterator iter = lstAdmin.iterator(); iter.hasNext();) {

            Administrador admin = (Administrador) iter.next();
            if (admin != null) {
                lstModel.addElement(admin.getId() + "-" + admin.getNome());
            }
        }
    }

    
}
