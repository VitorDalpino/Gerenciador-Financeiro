package View;

import Model.UsuarioModel;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private UsuarioModel usuario;

    public MainFrame(UsuarioModel usuario) {
        this.usuario = usuario;
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Gestão Financeira Pessoal - Bem-vindo " + usuario.getNome());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // painel para exibir os botões
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton btnCadastroTransacao = new JButton("Cadastro Transação");
        JButton btnCategorias = new JButton("Gerenciamento Categorias");
        JButton btnHistorico = new JButton("Histórico Transações");
        JButton btnResumo = new JButton("Resumo Financeiro");

        // abrir as telas
        btnCadastroTransacao.addActionListener(e -> new CadastroTransacaoFrame(usuario).setVisible(true));
        btnCategorias.addActionListener(e -> new GerenciamentoCategoriasFrame().setVisible(true));
        btnHistorico.addActionListener(e -> new HistoricoTransacoesFrame(usuario).setVisible(true));
        btnResumo.addActionListener(e -> new ResumoFinanceiroFrame(usuario).setVisible(true));

        panel.add(btnCadastroTransacao);
        panel.add(btnCategorias);
        panel.add(btnHistorico);
        panel.add(btnResumo);

        add(panel, BorderLayout.CENTER);
    }
}
