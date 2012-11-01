/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.JOptionPane;
import menu.Janela;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class JanelaUniversidade extends Janela {
    
     JLabel jltitulo,jlnome, jlDescricao;
    JTextField jtfnome;
    JButton jbInserir;
    JTextArea jTextArea1;
    
    public JanelaUniversidade () throws Exception{
        super("Universidade", new Dimension(400, 300));
             
                 
        jltitulo = new JLabel("Universidade");
        jltitulo.setBounds(170, 10, 200, 18);
        
        add(jltitulo);
        
        //nome
        jlnome= new JLabel("Nome");
        jlnome.setBounds(20,60,100,18);
        jlnome.setHorizontalAlignment(SwingConstants.LEFT);
        add(jlnome);
        jtfnome = new JTextField();

        jtfnome.setBounds(140, 60, 200, 20);
        jtfnome.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jtfnome.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtfnome.setBackground(Color.WHITE);
            }
        });
        add(jtfnome);
        
         //Descricao
        jlDescricao = new JLabel("Descricao:");
        jlDescricao.setBounds(20, 100, 120, 18);
        jlDescricao.setHorizontalAlignment(SwingConstants.LEFT);
       // add(jlDescricao);


        jTextArea1 = new JTextArea();
        jTextArea1.setBounds(140, 100, 200, 80);
        JScrollPane scrollPane = new JScrollPane(jTextArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jTextArea1.setLineWrap(true);
    
        jTextArea1.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jTextArea1.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jTextArea1.setBackground(Color.WHITE);
            }
        });
       // add(jTextArea1);
        
        
         
      
        
        //Inserir
        jbInserir = new JButton("Inserir");
       
        jbInserir.setBounds(240, 200,100, 23);
        jbInserir.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        inserir();
                    }
                });
            
        jbInserir.setMnemonic('i');
        add(jbInserir);
        
        
        
    }
      private void centralizar() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension janela = getSize();
        if (janela.height > screen.height) {
            setSize(janela.width, screen.height);
        }

        if (janela.width > screen.width) {
            setSize(screen.width, janela.height);
        }
        setLocation((screen.width - janela.width) / 2, (screen.height - janela.height) / 2);


    }
       public void inserir(){
         Configuration cfg = new AnnotationConfiguration();
         cfg.configure(
        "/hibernate.cfg.xml");
         SessionFactory sf = cfg.buildSessionFactory();
         Session session = sf.openSession();
         Transaction tx = session.beginTransaction();
        
        
         
        conexao.entity.Universidade uni = new conexao.entity.Universidade();
        uni.setNome(jtfnome.getText());
        
        
        session.save(uni); // Realiza persistência

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
        JOptionPane.showMessageDialog(null, "Universidade cadastrada com sucesso!", "Universidade", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
     }
       public void limpaRegistros() {
        jtfnome.setText(null);
        jTextArea1.setText(null);
       }
       
       public static void main(String[] args)throws Exception  {
        // TODO code application logic here
        new JanelaUniversidade().setVisible(true);
        }
}