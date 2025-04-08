package View;

import Controller.CategoriaController;
import Controller.TransacaoController;
import Model.CategoriaModel;
import Model.TransacaoModel.TipoTransacao;
import Model.UsuarioModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class CadastroTransacaoFrame extends JFrame {

    private UsuarioModel usuarioLogado;
    private TransacaoController transacaoController;
    private CategoriaController categoriaController;

    // componentes de tela
    private JTextField txtValor;
    private JComboBox<CategoriaModel> cbCategoria;
    private JComboBox<TipoTransacao> cbTipo;
    private JTextField txtData;  // ou use um componente de data
    private JTextField txtDescricao;
    private JButton btnSalvar;

    public CadastroTransacaoFrame(UsuarioModel usuario) {
        this.usuarioLogado = usuario;
        this.transacaoController = new TransacaoController();
        this.categoriaController = new CategoriaController();

        initComponents();
        carregarCategorias();
    }

    private void initComponents() {
        setTitle("Cadastro de Transação");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Criação dos componentes
        JLabel lblValor = new JLabel("Valor:");
        JLabel lblCategoria = new JLabel("Categoria:");
        JLabel lblTipo = new JLabel("Tipo:");
        JLabel lblData = new JLabel("Data (yyyy-MM-dd):");
        JLabel lblDescricao = new JLabel("Descrição:");

        txtValor = new JTextField(10);
        cbCategoria = new JComboBox<>();
        cbTipo = new JComboBox<>(TipoTransacao.values());
        txtData = new JTextField(10);
        txtDescricao = new JTextField(20);

        btnSalvar = new JButton("Salvar");

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelForm.add(lblValor, gbc);
        gbc.gridx = 1; panelForm.add(txtValor, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelForm.add(lblCategoria, gbc);
        gbc.gridx = 1; panelForm.add(cbCategoria, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelForm.add(lblTipo, gbc);
        gbc.gridx = 1; panelForm.add(cbTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panelForm.add(lblData, gbc);
        gbc.gridx = 1; panelForm.add(txtData, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panelForm.add(lblDescricao, gbc);
        gbc.gridx = 1; panelForm.add(txtDescricao, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(btnSalvar, gbc);

        add(panelForm, BorderLayout.CENTER);

        // botão de Salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarTransacao();
            }
        });
    }

    private void carregarCategorias() {
        List<CategoriaModel> categorias = categoriaController.listarCategorias();
        for (CategoriaModel cat : categorias) {
            cbCategoria.addItem(cat);
        }
    }

    private void salvarTransacao() {
        try {
            double valor = Double.parseDouble(txtValor.getText().trim());
            CategoriaModel categoria = (CategoriaModel) cbCategoria.getSelectedItem();
            TipoTransacao tipo = (TipoTransacao) cbTipo.getSelectedItem();
            LocalDate data = LocalDate.parse(txtData.getText().trim());
            String descricao = txtDescricao.getText().trim();

            transacaoController.adicionarTransacao(
                    valor,
                    categoria,
                    data,
                    descricao,
                    tipo,
                    usuarioLogado
            );

            JOptionPane.showMessageDialog(this, "Transação salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // limpar campos ou fechar janela
            txtValor.setText("");
            txtDescricao.setText("");
            txtData.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar transação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}