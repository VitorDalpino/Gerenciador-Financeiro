package DAO;

import Model.TransacaoModel;
import Model.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    //acessar metodos das transacoes
    private static List<TransacaoModel> transacoes = new ArrayList<>();

    public static void adicionarTransacao(TransacaoModel transacao) {
        transacoes.add(transacao);
    }

    public static List<TransacaoModel> listarTransacoes() {
        return transacoes;
    }

    public static List<TransacaoModel> listarTransacoesPorUsuario(UsuarioModel usuario) {
        List<TransacaoModel> resultado = new ArrayList<>();
        for (TransacaoModel t : transacoes) {
            if (t.getUsuario().getId() == usuario.getId()) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public static TransacaoModel buscarTransacaoPorId(int id) {
        for (TransacaoModel t : transacoes) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public static void atualizarTransacao(TransacaoModel transacao) {
        for (int i = 0; i < transacoes.size(); i++) {
            if (transacoes.get(i).getId() == transacao.getId()) {
                transacoes.set(i, transacao);
                break;
            }
        }
    }

    public static void removerTransacao(int id) {
        transacoes.removeIf(t -> t.getId() == id);
    }
}