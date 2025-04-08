package Controller;

import DAO.CategoriaDAO;
import Model.CategoriaModel;
import java.util.List;

public class CategoriaController {
    //crud categoria
    public void adicionarCategoria(String nome, String descricao) {
        CategoriaModel categoria = new CategoriaModel(nome, descricao);
        CategoriaDAO.adicionarCategoria(categoria);
    }

    public List<CategoriaModel> listarCategorias() {
        return CategoriaDAO.listarCategorias();
    }

    public CategoriaModel buscarCategoriaPorId(int id) {
        return CategoriaDAO.buscarCategoriaPorId(id);
    }

    public void atualizarCategoria(int id, String novoNome, String novaDescricao) {
        CategoriaModel categoria = CategoriaDAO.buscarCategoriaPorId(id);
        if (categoria != null) {
            categoria.setNome(novoNome);
            categoria.setDescricao(novaDescricao);
            CategoriaDAO.atualizarCategoria(categoria);
        }
    }

    public void removerCategoria(int id) {
        CategoriaDAO.removerCategoria(id);
    }
}