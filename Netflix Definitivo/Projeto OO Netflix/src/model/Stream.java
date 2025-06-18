package model;

public class Stream {
    // Atributos privados: nome da plataforma de streaming e o link (URL) para
    // acessar o stream
    private String nome;
    private String link;

    // Construtor que inicializa o nome da plataforma de streaming e o link
    public Stream(String nome, String link) {
        this.nome = nome;
        this.link = link;
    }

    // Método para obter o nome da plataforma de streaming
    public String getNome() {
        return nome;
    }

    // Método para obter o link (URL) do stream
    public String getLink() {
        return link;
    }

    // Método para converter as informações do stream em uma string formatada
    @Override
    public String toString() {
        return "Stream{" +
                "nome='" + nome + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
