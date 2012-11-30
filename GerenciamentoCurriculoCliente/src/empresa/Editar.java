/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

/**
 *
 * @author thaiane
 */
import Util.Janela;
import bean.ControleSessaoBean;
import entidade.Empresa;
import interfaces.ControleSessaoInterface;
import interfaces.EmpresaInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.Border;

public class Editar extends Janela {

    JLabel jltitulo, jlempresa, jlsenha, jlNome, jlDescricao, jlEmail, jlTelefone, jlEndereco, jlidempresa;
    JTextField jtfNome, jtfSenha, jtfEmail, jtfTelefone, jtfEndereco;
    JTextArea jtfDescricao;
    JButton jbAlterar;
    JList lstEmpresa;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlEmpresa, pnlEmpresaVisivel;
    Border border = BorderFactory.createLineBorder(Color.gray);

    public Editar() throws Exception {
        super("Empresa", new Dimension(340, 330));

        Empresa empresa = carregarEmpresa();

        jltitulo = new JLabel("Empresa");
        jltitulo.setBounds(130, 10, 200, 18);
        add(jltitulo);

        jlempresa = new JLabel("Nome:");
        jlempresa.setBounds(10, 35, 150, 20);
        jlempresa.setLayout(null);
        add(jlempresa);

        jtfNome = new JTextField(empresa.getNome());
        jtfNome.setBounds(120, 35, 187, 20);
        jtfNome.setLayout(null);
        add(jtfNome);

        jlsenha = new JLabel("Senha:");
        jlsenha.setBounds(10, 65, 150, 20);
        jlsenha.setLayout(null);
        add(jlsenha);

        jtfSenha = new JTextField(empresa.getSenha());
        jtfSenha.setBounds(120, 65, 187, 20);
        jtfSenha.setLayout(null);
        add(jtfSenha);

        jlDescricao = new JLabel("Descrição:");
        jlDescricao.setBounds(10, 92, 187, 20);
        jlDescricao.setLayout(null);
        add(jlDescricao);

        jtfDescricao = new JTextArea(empresa.getDescricao());
        jtfDescricao.setBounds(10, 112, 295, 40);
        jtfDescricao.setLayout(null);
        jtfDescricao.setBorder(border);
        add(jtfDescricao);

        jlEmail = new JLabel("Email:");
        jlEmail.setBounds(10, 165, 187, 20);
        jlEmail.setLayout(null);
        add(jlEmail);

        jtfEmail = new JTextField(empresa.getEmail());
        jtfEmail.setBounds(120, 165, 187, 20);
        jtfEmail.setLayout(null);
        jtfEmail.enable(false);
        jtfEmail.setDisabledTextColor(Color.gray);
        add(jtfEmail);

        jlTelefone = new JLabel("Telefone:");
        jlTelefone.setBounds(10, 195, 187, 20);
        jlTelefone.setLayout(null);
        add(jlTelefone);

        jtfTelefone = new JTextField(empresa.getTelefone());
        jtfTelefone.setBounds(120, 195, 187, 20);
        jtfTelefone.setLayout(null);
        add(jtfTelefone);

        jlEndereco = new JLabel("Endereço:");
        jlEndereco.setBounds(10, 225, 187, 20);
        jlEndereco.setLayout(null);
        add(jlEndereco);

        jtfEndereco = new JTextField(empresa.getEndereco());
        jtfEndereco.setBounds(120, 225, 187, 20);
        jtfEndereco.setLayout(null);
        add(jtfEndereco);

        jbAlterar = new JButton("Alterar");
        jbAlterar.setBounds(115, 260, 100, 20);
        jbAlterar.setLayout(null);
        jbAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Alterar();
                } catch (Exception ex) {
                    Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação desejada!");
                }
            }
        });
        jbAlterar.setMnemonic('A');
        add(jbAlterar);

    }

    private void Alterar() throws Exception {
        Empresa empresa = carregarEmpresa();

        System.out.println("-----\nIniciando busca da empresa");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        EmpresaInterface bean = (EmpresaInterface) ctx.lookup("EmpresaBean");
        System.out.println("Settando variaveis");
        empresa.setNome(jtfNome.getText());
        empresa.setSenha(jtfSenha.getText());
        empresa.setDescricao(jtfDescricao.getText());
        empresa.setEmail(jtfEmail.getText());
        empresa.setTelefone(jtfTelefone.getText());
        empresa.setEndereco(jtfEndereco.getText());
        System.out.println("Atualizando empresa");
        bean.atualizar(empresa);

        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Empresa", JOptionPane.INFORMATION_MESSAGE);
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
