package Controller;

import DAO.UsuarioDAO;
import Model.UsuarioModel;
import java.util.List;

public class UsuarioController {
    //crud usuario
    public boolean cadastrarUsuario(String nome, String login, String senha) {
        UsuarioModel existente = UsuarioDAO.buscarUsuarioPorLogin(login);
        if (existente != null) {
            return false;
        }
        UsuarioModel novoUsuario = new UsuarioModel(nome, login, senha);
        UsuarioDAO.adicionarUsuario(novoUsuario);
        return true;
    }

    public UsuarioModel autenticarUsuario(String login, String senha) {
        return UsuarioDAO.autenticarUsuario(login, senha);
    }

    public List<UsuarioModel> listarUsuarios() {
        return UsuarioDAO.listarUsuarios();
    }

    public UsuarioModel buscarUsuarioPorId(int id) {
        return UsuarioDAO.buscarUsuarioPorId(id);
    }

    public void atualizarUsuario(int id, String nome, String login, String senha) {
        UsuarioModel usuario = UsuarioDAO.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            UsuarioDAO.atualizarUsuario(usuario);
        }
    }

    public void removerUsuario(int id) {
        UsuarioDAO.removerUsuario(id);
    }
}