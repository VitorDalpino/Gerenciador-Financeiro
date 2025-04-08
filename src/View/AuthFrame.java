package View;

import Controller.UsuarioController;
import Model.UsuarioModel;
import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel loginPanel;
    private JPanel cadastroPanel;

    // componentes para Login
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    // componentes para Cadastro
    private JTextField txtNomeCadastro;
    private JTextField txtLoginCadastro;
    private JPasswordField txtSenhaCadastro;
    private JButton btnCadastrar;

    private UsuarioController usuarioController;

    public AuthFrame() {
        usuarioController = new UsuarioController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Autenticação - Login e Cadastro");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        // configuração do painel de Login
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");
        txtLogin = new JTextField(20);
        txtSenha = new JPasswordField(20);
        btnLogin = new JButton("Entrar");

        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(lblLogin, gbc);
        gbc.gridx = 1;
        loginPanel.add(txtLogin, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(lblSenha, gbc);
        gbc.gridx = 1;
        loginPanel.add(txtSenha, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(btnLogin, gbc);

        // configuração do painel de Cadastro
        cadastroPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5,5,5,5);
        gbc2.anchor = GridBagConstraints.WEST;

        JLabel lblNomeCadastro = new JLabel("Nome:");
        JLabel lblLoginCadastro = new JLabel("Login:");
        JLabel lblSenhaCadastro = new JLabel("Senha:");
        txtNomeCadastro = new JTextField(20);
        txtLoginCadastro = new JTextField(20);
        txtSenhaCadastro = new JPasswordField(20);
        btnCadastrar = new JButton("Cadastrar");

        gbc2.gridx = 0; gbc2.gridy = 0;
        cadastroPanel.add(lblNomeCadastro, gbc2);
        gbc2.gridx = 1;
        cadastroPanel.add(txtNomeCadastro, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 1;
        cadastroPanel.add(lblLoginCadastro, gbc2);
        gbc2.gridx = 1;
        cadastroPanel.add(txtLoginCadastro, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 2;
        cadastroPanel.add(lblSenhaCadastro, gbc2);
        gbc2.gridx = 1;
        cadastroPanel.add(txtSenhaCadastro, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 3; gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        cadastroPanel.add(btnCadastrar, gbc2);

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Cadastro", cadastroPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // botões
        btnLogin.addActionListener(e -> realizarLogin());
        btnCadastrar.addActionListener(e -> realizarCadastro());
    }

    private void realizarLogin() {
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        if (login.isEmpty() || senha.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        UsuarioModel usuario = usuarioController.autenticarUsuario(login, senha);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new MainFrame(usuario).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarCadastro() {
        String nome = txtNomeCadastro.getText().trim();
        String login = txtLoginCadastro.getText().trim();
        String senha = new String(txtSenhaCadastro.getPassword()).trim();
        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioController.cadastrarUsuario(nome, login, senha);
        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso! Agora efetue o login.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // limpa os campos e muda para o Login
        txtNomeCadastro.setText("");
        txtLoginCadastro.setText("");
        txtSenhaCadastro.setText("");
        tabbedPane.setSelectedIndex(0);
    }
}