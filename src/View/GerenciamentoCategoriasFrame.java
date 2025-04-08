package View;

import Controller.CategoriaController;
import Model.CategoriaModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GerenciamentoCategoriasFrame extends JFrame {

    private CategoriaController categoriaController;
    private JTable tableCategorias;
    private DefaultTableModel tableModel;
    private JTextField txtNome;
    private JTextField txtDescricao;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnRemover;

    public GerenciamentoCategoriasFrame() {
        categoriaController = new CategoriaController();
        initComponents();
        carregarCategorias();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Categorias");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Painel para o formulário de cadastro/edição
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblDescricao = new JLabel("Descrição:");

        txtNome = new JTextField(20);
        txtDescricao = new JTextField(20);

        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(lblNome, gbc);
        gbc.gridx = 1;
        panelForm.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(lblDescricao, gbc);
        gbc.gridx = 1;
        panelForm.add(txtDescricao, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(btnAdicionar, gbc);
        gbc.gridx = 1;
        panelForm.add(btnEditar, gbc);
        gbc.gridx = 2;
        panelForm.add(btnRemover, gbc);

        // Configuração da tabela de categorias
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição"}, 0);
        tableCategorias = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCategorias);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Ação dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCategoria();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCategoria();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCategoria();
            }
        });

        // Ao clicar na tabela, preenche os campos com os dados da categoria selecionada
        tableCategorias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableCategorias.getSelectedRow();
                if (selectedRow >= 0) {
                    txtNome.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtDescricao.setText(tableModel.getValueAt(selectedRow, 2).toString());
                }
            }
        });
    }

    private void carregarCategorias() {
        tableModel.setRowCount(0);
        List<CategoriaModel> categorias = categoriaController.listarCategorias();
        for (CategoriaModel cat : categorias) {
            Object[] row = new Object[3];
            row[0] = cat.getId();
            row[1] = cat.getNome();
            row[2] = cat.getDescricao();
            tableModel.addRow(row);
        }
    }

    private void adicionarCategoria() {
        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome da categoria é obrigatório", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        categoriaController.adicionarCategoria(nome, descricao);
        carregarCategorias();
        limparCampos();
    }

    private void editarCategoria() {
        int selectedRow = tableCategorias.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String novoNome = txtNome.getText().trim();
        String novaDescricao = txtDescricao.getText().trim();
        if (novoNome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome da categoria é obrigatório", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        categoriaController.atualizarCategoria(id, novoNome, novaDescricao);
        carregarCategorias();
        limparCampos();
    }

    private void removerCategoria() {
        int selectedRow = tableCategorias.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para remover", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        categoriaController.removerCategoria(id);
        carregarCategorias();
        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtDescricao.setText("");
    }
}
