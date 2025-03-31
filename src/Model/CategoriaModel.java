package Model;

public class CategoriaModel {
    public int id;
    public String nome;

    // Construtor vazio
    public CategoriaModel() {
    }

    // Construtor com parâmetros
    public CategoriaModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
