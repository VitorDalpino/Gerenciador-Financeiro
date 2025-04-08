package Controller;

import DAO.TransacaoDAO;
import Model.CategoriaModel;
import Model.TransacaoModel;
import Model.TransacaoModel.TipoTransacao;
import Model.UsuarioModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoController {
    //crud transacoes
    public void adicionarTransacao(double valor,
                                   CategoriaModel categoria,
                                   LocalDate data,
                                   String descricao,
                                   TipoTransacao tipo,
                                   UsuarioModel usuario) {
        TransacaoModel transacao = new TransacaoModel(
                valor, categoria, data, descricao, tipo, usuario
        );
        TransacaoDAO.adicionarTransacao(transacao);
    }

    public List<TransacaoModel> listarTransacoes() {
        return TransacaoDAO.listarTransacoes();
    }

    public List<TransacaoModel> listarTransacoesPorUsuario(UsuarioModel usuario) {
        return TransacaoDAO.listarTransacoesPorUsuario(usuario);
    }

    public TransacaoModel buscarTransacaoPorId(int id) {
        return TransacaoDAO.buscarTransacaoPorId(id);
    }

    public void atualizarTransacao(int id,
                                   double valor,
                                   CategoriaModel categoria,
                                   LocalDate data,
                                   String descricao,
                                   TipoTransacao tipo) {
        TransacaoModel transacao = TransacaoDAO.buscarTransacaoPorId(id);
        if (transacao != null) {
            transacao.setValor(valor);
            transacao.setCategoria(categoria);
            transacao.setData(data);
            transacao.setDescricao(descricao);
            transacao.setTipo(tipo);
            TransacaoDAO.atualizarTransacao(transacao);
        }
    }

    public void removerTransacao(int id) {
        TransacaoDAO.removerTransacao(id);
    }
    //metodos transacoes
    public List<TransacaoModel> filtrarTransacoes(UsuarioModel usuario,
                                                  LocalDate dataInicio,
                                                  LocalDate dataFim,
                                                  TipoTransacao tipo,
                                                  CategoriaModel categoria) {
        List<TransacaoModel> todasTransacoes = listarTransacoesPorUsuario(usuario);
        List<TransacaoModel> filtradas = new ArrayList<>();

        for (TransacaoModel t : todasTransacoes) {
            boolean match = true;

            if (dataInicio != null && t.getData().isBefore(dataInicio)) {
                match = false;
            }
            if (dataFim != null && t.getData().isAfter(dataFim)) {
                match = false;
            }
            if (tipo != null && t.getTipo() != tipo) {
                match = false;
            }
            if (categoria != null && t.getCategoria() != null
                    && t.getCategoria().getId() != categoria.getId()) {
                match = false;
            }

            if (match) {
                filtradas.add(t);
            }
        }

        return filtradas;
    }

    public double calcularTotalReceitas(UsuarioModel usuario, LocalDate dataInicio, LocalDate dataFim) {
        double total = 0.0;
        List<TransacaoModel> transacoes = filtrarTransacoes(usuario, dataInicio, dataFim, null, null);
        for (TransacaoModel t : transacoes) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                total += t.getValor();
            }
        }
        return total;
    }

    //filtro despesas por data
    public double calcularTotalDespesas(UsuarioModel usuario, LocalDate dataInicio, LocalDate dataFim) {
        double total = 0.0;
        List<TransacaoModel> transacoes = filtrarTransacoes(usuario, dataInicio, dataFim, null, null);
        for (TransacaoModel t : transacoes) {
            if (t.getTipo() == TipoTransacao.DESPESA) {
                total += t.getValor();
            }
        }
        return total;
    }
    //filtro do saldo com base na data
    public double calcularSaldo(UsuarioModel usuario, LocalDate dataInicio, LocalDate dataFim) {
        return calcularTotalReceitas(usuario, dataInicio, dataFim)
                - calcularTotalDespesas(usuario, dataInicio, dataFim);
    }
}