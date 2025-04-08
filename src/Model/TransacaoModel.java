package Model;

import java.time.LocalDate;

public class TransacaoModel {
    public enum TipoTransacao {
        RECEITA, DESPESA
    }

    private static int nextId = 1;

    private int id;
    private double valor;
    private CategoriaModel categoria;
    private LocalDate data;
    private String descricao;
    private TipoTransacao tipo;
    private UsuarioModel usuario;

    public TransacaoModel(double valor,
                          CategoriaModel categoria,
                          LocalDate data,
                          String descricao,
                          TipoTransacao tipo,
                          UsuarioModel usuario) {
        this.id = nextId++;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
