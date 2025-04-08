package Model;

public class UsuarioModel {
    private static int nextId = 1;

    private int id;
    private String nome;
    private String login;
    private String senha;

    public UsuarioModel(String nome, String login, String senha) {
        this.id = nextId++;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}