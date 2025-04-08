package View;

import Controller.TransacaoController;
import Model.UsuarioModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ResumoFinanceiroFrame extends JFrame {

    private UsuarioModel usuarioLogado;
    private TransacaoController transacaoController;

    private JLabel lblTotalReceitas;
    private JLabel lblTotalDespesas;
    private JLabel lblSaldo;
    private JTextField txtDataInicio;
    private JTextField txtDataFim;
    private JButton btnCalcular;

    public ResumoFinanceiroFrame(UsuarioModel usuario) {
        this.usuarioLogado = usuario;
        this.transacaoController = new TransacaoController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Resumo Financeiro");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblTotalReceitas = new JLabel("Total de Receitas: R$ 0.00");
        lblTotalDespesas = new JLabel("Total de Despesas: R$ 0.00");
        lblSaldo = new JLabel("Saldo: R$ 0.00");

        txtDataInicio = new JTextField(10);
        txtDataFim = new JTextField(10);

        btnCalcular = new JButton("Calcular");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Data Início (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        panel.add(txtDataInicio, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Data Fim (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        panel.add(txtDataFim, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(btnCalcular, gbc);

        gbc.gridy = 3;
        panel.add(lblTotalReceitas, gbc);

        gbc.gridy = 4;
        panel.add(lblTotalDespesas, gbc);

        gbc.gridy = 5;
        panel.add(lblSaldo, gbc);

        add(panel);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResumo();
            }
        });
    }

    private void calcularResumo() {
        try {
            LocalDate dataInicio = txtDataInicio.getText().isEmpty() ? null : LocalDate.parse(txtDataInicio.getText());
            LocalDate dataFim = txtDataFim.getText().isEmpty() ? null : LocalDate.parse(txtDataFim.getText());

            double totalReceitas = transacaoController.calcularTotalReceitas(usuarioLogado, dataInicio, dataFim);
            double totalDespesas = transacaoController.calcularTotalDespesas(usuarioLogado, dataInicio, dataFim);
            double saldo = transacaoController.calcularSaldo(usuarioLogado, dataInicio, dataFim);

            lblTotalReceitas.setText(String.format("Total de Receitas: R$ %.2f", totalReceitas));
            lblTotalDespesas.setText(String.format("Total de Despesas: R$ %.2f", totalDespesas));
            lblSaldo.setText(String.format("Saldo: R$ %.2f", saldo));

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use yyyy-MM-dd.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular resumo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
