package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame {
    private JLabel saldoLabel;
    private boolean saldoVisivel = false;

    public TelaPrincipal() {
        setTitle("Gerenciador de Finanças Pessoais");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(4, 41, 89));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel do saldo
        JPanel saldoPanel = new JPanel();
        saldoPanel.setBackground(Color.WHITE);
        saldoPanel.setPreferredSize(new Dimension(300, 80));
        saldoPanel.setMaximumSize(new Dimension(300, 80));
        saldoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel saldoTexto = new JLabel("Saldo disponível");
        saldoTexto.setFont(new Font("Arial", Font.BOLD, 14));

        saldoLabel = new JLabel("R$ ******");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel olhoIcone = new JLabel(new ImageIcon("olho.png")); // Substituir pelo ícone real
        olhoIcone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        olhoIcone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saldoVisivel = !saldoVisivel;
                saldoLabel.setText(saldoVisivel ? "R$ 1.250,00" : "R$ ******");
            }
        });

        saldoPanel.add(saldoTexto);
        saldoPanel.add(saldoLabel);
        saldoPanel.add(olhoIcone);

        // Botões estilizados
        JButton btnCadastrar = criarBotao("Cadastrar Transação", "icon_cadastro.png");
        JButton btnHistorico = criarBotao("Consultar Histórico", "icon_historico.png");
        JButton btnResumo = criarBotao("Resumo Financeiro", "icon_resumo.png");
        JButton btnCategoria = criarBotao("Categoria Financeiro", "icon_categoria.png");
        btnCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionando componentes ao painel principal
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(saldoPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(btnCadastrar);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnHistorico);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnResumo);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnCategoria);
        mainPanel.add(Box.createVerticalStrut(20));


        add(mainPanel);
    }

    private JButton criarBotao(String texto, String iconePath) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(8, 66, 140));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(8, 66, 140)));
        button.setPreferredSize(new Dimension(280, 50));
        button.setMaximumSize(new Dimension(280, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon(iconePath); // Adicionar ícones reais
        button.setIcon(icon);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
