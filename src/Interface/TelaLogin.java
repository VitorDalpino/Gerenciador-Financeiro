package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class telaLogin extends JFrame {
    public telaLogin() {
        setTitle("Gerenciador de Finanças Pessoais");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(4, 41, 89));

        // titulo
        JLabel titulo = new JLabel("Realize seu Login");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titulo);

        // entrada
        JTextField loginField = criarCampoTexto("Login");
        JPasswordField senhaField = criarCampoSenha("Senha");

        // quando clica no login ele desaparece
        loginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (loginField.getText().equals("Login")) {
                    loginField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (loginField.getText().isEmpty()) {
                    loginField.setText("Login");
                }
            }
        });

        // quando clica na senha ela desaparece
        senhaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(senhaField.getPassword()).equals("Senha")) {
                    senhaField.setText("");
                    senhaField.setForeground(Color.BLACK);
                    senhaField.setEchoChar('*'); // Ativa os asteriscos ao digitar
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (senhaField.getPassword().length == 0) {
                    senhaField.setText("Senha");
                    senhaField.setForeground(Color.GRAY);
                    senhaField.setEchoChar((char) 0); // Remove os asteriscos e exibe o placeholder
                }
            }
        });

        // botao pra mostrar e ocultar senha
        JCheckBox mostrarSenhaCheckBox = new JCheckBox("Mostrar senha");
        mostrarSenhaCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        mostrarSenhaCheckBox.setForeground(Color.BLACK);
        mostrarSenhaCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mostrar e ocultar senha
        mostrarSenhaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarSenhaCheckBox.isSelected()) {
                    senhaField.setEchoChar((char) 0);
                } else if (!new String(senhaField.getPassword()).equals("Senha")) {
                    senhaField.setEchoChar('*');
                }
            }
        });

        // botao de entrar
        JButton btnSalvar = new JButton("Entrar");
        btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalvar.setBackground(Color.WHITE);
        btnSalvar.setForeground(new Color(8, 66, 140));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorder(BorderFactory.createLineBorder(new Color(8, 66, 140)));
        btnSalvar.setPreferredSize(new Dimension(280, 50));
        btnSalvar.setMaximumSize(new Dimension(280, 50));
        btnSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // painel principal
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(loginField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(senhaField);
        mainPanel.add(mostrarSenhaCheckBox);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(btnSalvar);

        SwingUtilities.invokeLater(() -> mainPanel.requestFocusInWindow());
        add(mainPanel);
    }

    private JTextField criarCampoTexto(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setMaximumSize(new Dimension(280, 30));
        campo.setForeground(Color.BLACK);
        return campo;
    }

    private JPasswordField criarCampoSenha(String placeholder) {
        JPasswordField campo = new JPasswordField(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setMaximumSize(new Dimension(280, 30));
        campo.setEchoChar((char) 0); // desaparece ao clicar
        campo.setForeground(Color.GRAY);
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new telaLogin().setVisible(true));
    }
}
