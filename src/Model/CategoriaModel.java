package Model;

public class CategoriaModel {
    private static int nextId = 1;

    private int id;
    private String nome;
    private String descricao;

    public CategoriaModel(String nome, String descricao) {
        this.id = nextId++;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return nome;
    }
}
