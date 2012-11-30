/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manutencao;

import Util.Janela;
import entidade.AreaAtuacao;
import interfaces.AreaAtuacaoInterface;
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
public class EditarAreaAtuacao extends Janela {

    JLabel jltitulo, jlarea, jlidArea;
    JTextField jtfarea;
    JButton jbAlterar, jbExcluir;
    JList lstAtuacao;
    JScrollPane scroll;
    DefaultListModel lstModel;
    JPanel pnlAtuacao, pnlAtuacaoVisivel;
    Border border = BorderFactory.createLineBorder(Color.black);

    public EditarAreaAtuacao() throws Exception {
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
        jbAlterar.setBounds(65, 80, 80, 20);
        jbAlterar.setLayout(null);
        jbAlterar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            Alterar();
                        } catch (Exception ex) {
                            Logger.getLogger(NovaAreaAtuacao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        jbAlterar.setMnemonic('A');
        pnlAtuacaoVisivel.add(jbAlterar);
    }

    private void Alterar() throws Exception {

        System.out.println("-----\nIniciando busca da área de atuação");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AreaAtuacaoInterface bean = (AreaAtuacaoInterface) ctx.lookup("AreaAtuacaoBean");
        System.out.println("Resgatando a área de atuação");
        AreaAtuacao areaAtuacao = bean.procurarAreaAtuacao(Long.parseLong(jlidArea.getText()));
        System.out.println("Settando novo nome");
        areaAtuacao.setDescricao(jtfarea.getText().toUpperCase());
        System.out.println("Atualizando área de atuação");
        bean.atualizar(areaAtuacao);

        //Recarrega o list atualizado
        limparRegistro();
        CarregarListAtuacao();
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Área de Atuação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limparRegistro(){
        jtfarea.setText(null);
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

        System.out.println("-----\nIniciando busca das áreas de atuação");
        Properties props = new Properties();
        props.load(new java.io.FileInputStream("jndi.properties"));
        InitialContext ctx = new InitialContext(props);
        System.out.println("Procurando a classe bean");
        AreaAtuacaoInterface bean = (AreaAtuacaoInterface) ctx.lookup("AreaAtuacaoBean");
        System.out.println("Resgatando as áreas de atuação");
        List<AreaAtuacao> lstAreaAtuacao = bean.listarAreaAtuacao();
        InserirListAtuacao(lstAreaAtuacao);
    }

    private void InserirListAtuacao(List<AreaAtuacao> areaAtuacao) {
        for (Iterator iter = areaAtuacao.iterator(); iter.hasNext();) {

            AreaAtuacao area = (AreaAtuacao) iter.next();
            if (area != null) {
                lstModel.addElement(area.getId() + "-" + area.getDescricao());
            }
        }
    }

}
