package DAO;

import Model.UsuarioModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    //acessar metodos dos usuarios
    private static List<UsuarioModel> usuarios = new ArrayList<>();

    public static void adicionarUsuario(UsuarioModel usuario) {
        usuarios.add(usuario);
    }

    public static List<UsuarioModel> listarUsuarios() {
        return usuarios;
    }

    public static UsuarioModel buscarUsuarioPorId(int id) {
        for (UsuarioModel u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public static UsuarioModel buscarUsuarioPorLogin(String login) {
        for (UsuarioModel u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(login)) {
                return u;
            }
        }
        return null;
    }

    public static void atualizarUsuario(UsuarioModel usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    public static void removerUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }

    public static UsuarioModel autenticarUsuario(String login, String senha) {
        for (UsuarioModel u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(login) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
