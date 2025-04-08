package DAO;

import Model.CategoriaModel;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    //acessar metodos das categorias
    private static List<CategoriaModel> categorias = new ArrayList<>();

    public static void adicionarCategoria(CategoriaModel categoria) {
        categorias.add(categoria);
    }

    public static List<CategoriaModel> listarCategorias() {
        return categorias;
    }

    public static CategoriaModel buscarCategoriaPorId(int id) {
        for (CategoriaModel c : categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public static void atualizarCategoria(CategoriaModel categoria) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() == categoria.getId()) {
                categorias.set(i, categoria);
                break;
            }
        }
    }

    public static void removerCategoria(int id) {
        categorias.removeIf(c -> c.getId() == id);
    }
}