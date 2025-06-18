package model;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    // Atributos privados: nome do filme, gênero do filme e uma lista de streams
    // (plataformas de streaming)
    private String nome;
    private String genero;
    private List<Stream> streams;

    // Construtor que inicializa o nome e o gênero do filme, e cria uma nova lista
    // vazia de streams
    public Filme(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;
        this.streams = new ArrayList<>();
    }

    // Método para obter o nome do filme
    public String getNome() {
        return nome;
    }

    // Método para obter o gênero do filme
    public String getGenero() {
        return genero;
    }

    // Método para obter a lista de streams disponíveis para o filme
    public List<Stream> getStreams() {
        return streams;
    }

    // Método para adicionar um novo stream (plataforma de streaming) à lista de
    // streams do filme
    public void addStream(Stream stream) {
        this.streams.add(stream);
    }
}
