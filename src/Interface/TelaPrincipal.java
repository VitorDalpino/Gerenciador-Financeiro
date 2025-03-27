package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame {
    private JLabel saldoLabel;

    public TelaPrincipal() {
        setTitle("Gerenciador de Finanças Pessoais");
        setSize(1080, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Menu lateral com BoxLayout para melhor ajuste
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(4, 41, 89));

        // Adicionando a logo corretamente
        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon logoIcon = new ImageIcon("G:\\Meu Drive\\Faculdade\\arquivos.java\\Atividade_Parcial\\imagens\\logo\\logo.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImage);
        logoLabel.setIcon(logoIcon);
        menuPanel.add(logoLabel);

        // Criando botões
        JButton btnCadastrar = criarBotao("Cadastrar Transação");
        JButton btnHistorico = criarBotao("Consultar Histórico");
        JButton btnResumo = criarBotao("Resumo Financeiro");
        JButton btnCategorias = criarBotao("Gerenciar Categorias");

        // Adicionando botões no menu lateral
        menuPanel.add(Box.createVerticalStrut(20)); // Espaçamento
        menuPanel.add(btnCadastrar);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnHistorico);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnResumo);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnCategorias);

        // Criando painel central para melhor visualização do saldo
        JPanel centerPanel = new JPanel(new GridBagLayout());
        saldoLabel = new JLabel("Saldo: R$ 0.00");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        centerPanel.add(saldoLabel);

        // Ajustando layout principal
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    // Método para criar botões formatados
    private JButton criarBotao(String texto) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(8, 66, 140));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        button.setPreferredSize(new Dimension(180, 40));

        // Adicionando efeito de hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 51, 102)); // Cor de hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(8, 66, 140)); // Cor original
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
