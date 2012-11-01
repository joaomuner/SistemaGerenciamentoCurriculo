/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Jota
 */
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import conexao.entity.Empresa;
import java.util.ArrayList;
import menu.Janela;


public class JanelaEmpresa extends Janela  {

    JLabel jlNome,jlDescricao,jlEndereco,jlTelefone,jlEmail;
    JTextField jtfidEmpresa, jtfNome,jtfDescricao,jtfEndereco,jtfTelefone,jtfEmail;
    JSeparator jSeparator01, jSeparator02,jSeparator03,jSeparator04,jSeparator05,jSeparator06;
    ButtonGroup bggrupo;
    JButton jbAtualizar, jbCadastrar, jbLimpar, jbExcluir, jbConsultar;
    

    public JanelaEmpresa() throws Exception {
        super("Empresa", new Dimension(550, 420));


        //Nome da Empresa
        jlNome = new JLabel("Nome da Empresa:");
        jlNome.setBounds(20, 60, 130, 18);
        add(jlNome);

       jtfNome = new JTextField();

        jtfNome.setBounds(140, 60, 250, 20);
        jtfNome.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jtfNome.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtfNome.setBackground(Color.WHITE);
            }
        });
        add(jtfNome);

    
        


        //Separator
        jSeparator02 = new JSeparator();
        jSeparator02.setBounds(20, 90, 500, 10);
        add(jSeparator02);


        //Descricao
        jlDescricao = new JLabel("Descricao:");
        jlDescricao.setBounds(20, 120, 120, 18);
        jlDescricao.setHorizontalAlignment(SwingConstants.LEFT);
        add(jlDescricao);


        jtfDescricao = new JTextField();
        jtfDescricao.setBounds(140, 120, 250, 20);
        jtfDescricao.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jtfDescricao.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtfDescricao.setBackground(Color.WHITE);
            }
        });
        add(jtfDescricao);

        
         //Separator
        jSeparator03 = new JSeparator();
        jSeparator03.setBounds(20, 150, 500, 10);
        add(jSeparator03);


     



        //Endereco
        jlEndereco = new JLabel("Endereco:");
        jlEndereco.setBounds(20, 180, 60, 18);
        jlEndereco.setHorizontalAlignment(SwingConstants.LEFT);
        add(jlEndereco);
        jtfEndereco = new JTextField();
        jtfEndereco.setBounds(140,180, 250, 20);
        jtfEndereco.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jtfEndereco.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtfEndereco.setBackground(Color.WHITE);
            }
        });
        add(jtfEndereco);

       


       
        //Separator
        jSeparator04 = new JSeparator();
        jSeparator04.setBounds(20, 210, 500, 10);
        add(jSeparator04);
        
         //Telefone
        jlTelefone = new JLabel("Telefone:");
        jlTelefone.setBounds(20, 240, 60, 18);
        jlTelefone.setHorizontalAlignment(SwingConstants.LEFT);
        add(jlTelefone);

       
        jtfTelefone = new JTextField();
        jtfTelefone.setBounds(140, 240, 250, 20);
        jtfTelefone.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jtfTelefone.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtfTelefone.setBackground(Color.WHITE);
            }
        });
        add(jtfTelefone);


       
        //Separator
        jSeparator05 = new JSeparator();
        jSeparator05.setBounds(20, 270, 500, 10);
        add(jSeparator05);
        
        //Email
        jlEmail = new JLabel("Email:");
        jlEmail.setBounds(20, 300, 60, 18);
        jlEmail.setHorizontalAlignment(SwingConstants.LEFT);
        add(jlEmail);

       
        jtfEmail = new JTextField();
        jtfEmail.setBounds(140, 300, 250, 20);
       jtfEmail.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                jtfEmail.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtfEmail.setBackground(Color.WHITE);
            }
        });
        add(jtfEmail);


       
        //Separator
        jSeparator06 = new JSeparator();
        jSeparator06.setBounds(20, 330, 500, 10);
        add(jSeparator02);





        jtfNome.setEnabled(true);
        jtfDescricao.setEnabled(true);
        jtfEndereco.setEnabled(true);
        jtfTelefone.setEnabled(true);
        jtfEmail.setEnabled(true);
        

        //Cadastrar
        jbCadastrar = new JButton("Inserir");
       
        jbCadastrar.setBounds(20, 350, 95, 23);
        jbCadastrar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        inserir();
                    }
                });
            
        jbCadastrar.setMnemonic('c');
        add(jbCadastrar);

        //Limpar
       jbLimpar = new JButton("Limpar");

        jbLimpar.setBounds(120, 350, 95, 23);
        jbLimpar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        //msgtext();
                        if (jtfidEmpresa.getText().trim().equals("") && jtfNome.getText().trim().equals("")
                                && jtfDescricao.getText().trim().equals("") && jtfEndereco.getText().trim().equals("")
                                && jtfTelefone.getText().trim().equals("") && jtfEmail.getText().replace("/", "").trim().equals("") ) {
                            JOptionPane.showMessageDialog(null, "Os registros já estão limpos!","Cadastro de Emprea", JOptionPane.ERROR_MESSAGE);

                        } else {
                            limpaRegistros();
                            jbAtualizar.setEnabled(true);
                            
                            jbCadastrar.setEnabled(true);
                            jtfidEmpresa.setEnabled(true);
                            jtfNome.setEnabled(true);
                            jtfDescricao.setEnabled(true);
                            jtfEndereco.setEnabled(true);
                            jtfTelefone.setEnabled(true);
                            jtfEmail.setEnabled(true);
                        }
                    }
                });
        jbLimpar.setMnemonic('l');
        //add(jbLimpar);




        
        //Atualizar
        jbAtualizar = new JButton("Atualizar");

        jbAtualizar.setBounds(220, 350, 95, 23);
        jbAtualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                              
                              atualizar();}
                   
                });
        jbAtualizar.setMnemonic('a');
          
        //add(jbAtualizar);



        //Excluir
        jbExcluir = new JButton("Excluir");

        jbExcluir.setBounds(320, 350, 95, 23);
        jbExcluir.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        excluir();

                    }
                });
        jbExcluir.setMnemonic('e');
        //add(jbExcluir);

       

    
    //Consultar
    jbConsultar = new JButton("Consultar");
    jbConsultar.setBounds(420, 350, 95, 23);
        
    jbConsultar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        mostrar();

                    }
                });
        jbConsultar.setMnemonic('l');
        //add(jbConsultar);
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
    
     public void limpaRegistros() {
        //jtfidEmpresa.setText(null);
        jtfNome.setText(null);
        jtfDescricao.setText(null);
        jtfEndereco.setText(null);
        jtfTelefone.setText(null);
        jtfEmail.setText(null);
        


    }
     
     public void inserir(){
         Configuration cfg = new AnnotationConfiguration();
         cfg.configure(
        "/hibernate.cfg.xml");
         SessionFactory sf = cfg.buildSessionFactory();
         Session session = sf.openSession();
         Transaction tx = session.beginTransaction();
        
        
         
        conexao.entity.Empresa empresa = new conexao.entity.Empresa();
        //empresa.setId(Long.parseLong(jtfidEmpresa.getText()));
        empresa.setNome(jtfNome.getText());
        empresa.setDescricao(jtfDescricao.getText());
        empresa.setEndereco(jtfEndereco.getText());
        empresa.setTelefone(jtfTelefone.getText());
        empresa.setEmail(jtfEmail.getText());
        session.save(empresa); // Realiza persistência

        tx.commit(); // Finaliza transação
        session.close(); // Fecha sessão
        JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!", "Cadastro de Empresa", JOptionPane.INFORMATION_MESSAGE);
        limpaRegistros();
     }
     
     public void atualizar(){
            
            
            Configuration cfg = new AnnotationConfiguration();
            //Informe o arquivo XML que contém a configurações
            cfg.configure(
            "/hibernate.cfg.xml");
            //Cria uma fábrica de sessões.
            //Deve existir apenas uma instância na aplicação
            SessionFactory sf = cfg.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            
            
             
            
            
            char o= JOptionPane.showInputDialog(null,"Deseja realmente atualizar os dados:\ns-sim \nn-não").charAt(0);
            if(o=='s' || o=='S'){
            Empresa empresa = (Empresa) session.get(Empresa.class, Integer.parseInt(jtfidEmpresa.getText()));
            empresa.setNome(jtfNome.getText());
            empresa.setDescricao(jtfDescricao.getText());
            empresa.setEndereco(jtfEndereco.getText());
            empresa.setTelefone(jtfTelefone.getText());
            empresa.setEmail(jtfEmail.getText());
            session.saveOrUpdate(empresa);
            tx.commit();
            session.close();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!", "Cadastro de Empresa", JOptionPane.INFORMATION_MESSAGE);
            limpaRegistros();
            }
            else{
                limpaRegistros();
            }
            
            
            
     }
     public void mostrar(){
         Configuration cfg = new AnnotationConfiguration();
            //Informe o arquivo XML que contém a configurações
            cfg.configure(
            "/hibernate.cfg.xml");
            //Cria uma fábrica de sessões.
            //Deve existir apenas uma instância na aplicação
            SessionFactory sf = cfg.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            //pera ai
            //Empresa empresa = (Empresa) session.get(Empresa.class, Integer.parseInt(jtfidEmpresa.getText()));
            ArrayList<Empresa> empresa = (ArrayList<Empresa>) session.createCriteria(Empresa.class).list();
            jtfNome.setText(empresa.get(0).getNome());
            jtfDescricao.setText(empresa.get(1).getDescricao());
            jtfEndereco.setText(empresa.get(2).getEndereco());
            jtfTelefone.setText(empresa.get(3).getTelefone());
            jtfEmail.setText(empresa.get(4).getEmail());
            //jtfDescricao.setText(empresa.getDescricao());
            //jtfEndereco.setText(empresa.getEndereco());
            //jtfTelefone.setText(empresa.getTelefone());
            //jtfEmail.setText(empresa.getEmail());
            tx.commit();
            session.close();
     }
    
     
     public void excluir(){
            Configuration cfg = new AnnotationConfiguration();
            cfg.configure(
            "/hibernate.cfg.xml");
            SessionFactory sf = cfg.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();



            Empresa empresa = (Empresa) session.get(Empresa.class, Integer.parseInt(jtfidEmpresa.getText()));
                         
            empresa.setId(Long.parseLong(jtfidEmpresa.getText()));
            char o= JOptionPane.showInputDialog(null,"Deseja realmente excluir os dados:\ns-sim \nn-não").charAt(0);
                if(o=='s' || o=='S'){
                    session.delete(empresa);
                    JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!", "Cadastro de Empresa", JOptionPane.INFORMATION_MESSAGE);
                    limpaRegistros();
                    tx.commit();
                    session.close();
                }
                else{
                    limpaRegistros();
                }
        }
        
        public static void main(String[] args)throws Exception  {
           // TODO code application logic here
           new JanelaEmpresa().setVisible(true);
       }
  
}