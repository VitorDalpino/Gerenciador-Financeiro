package View;

import Controller.TransacaoController;
import Model.TransacaoModel;
import Model.UsuarioModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoricoTransacoesFrame extends JFrame {

    private UsuarioModel usuarioLogado;
    private TransacaoController transacaoController;
    private JTable tabelaTransacoes;
    private DefaultTableModel tableModel;

    public HistoricoTransacoesFrame(UsuarioModel usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.transacaoController = new TransacaoController();
        initComponents();
        carregarTransacoes();
    }

    private void initComponents() {
        setTitle("Histórico de Transações - " + usuarioLogado.getNome());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Valor", "Tipo", "Categoria", "Data", "Descrição"}, 0);
        tabelaTransacoes = new JTable(tableModel);

        // Adiciona a tabela a um JScrollPane para permitir a rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaTransacoes);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarTransacoes() {
        tableModel.setRowCount(0);
        List<TransacaoModel> transacoes = transacaoController.listarTransacoesPorUsuario(usuarioLogado);

        for (TransacaoModel t : transacoes) {
            Object[] row = new Object[6];
            row[0] = t.getId();
            row[1] = t.getValor();
            row[2] = t.getTipo();
            row[3] = (t.getCategoria() != null) ? t.getCategoria().getNome() : "";
            row[4] = t.getData();
            row[5] = t.getDescricao();

            tableModel.addRow(row);
        }
    }
}
