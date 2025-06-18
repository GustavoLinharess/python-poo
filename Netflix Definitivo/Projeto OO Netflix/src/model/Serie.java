package model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    // Atributos privados: nome da série, gênero da série e uma lista de streams
    // (plataformas de streaming)
    private String nome;
    private String genero;
    private List<Stream> streams;

    // Construtor que inicializa o nome e o gênero da série, e cria uma nova lista
    // vazia de streams
    public Serie(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;
        this.streams = new ArrayList<>();
    }

    // Método para obter o nome da série
    public String getNome() {
        return nome;
    }

    // Método para obter o gênero da série
    public String getGenero() {
        return genero;
    }

    // Método para obter a lista de streams disponíveis para a série
    public List<Stream> getStreams() {
        return streams;
    }

    // Método para adicionar um novo stream (plataforma de streaming) à lista de
    // streams da série
    public void addStream(Stream stream) {
        this.streams.add(stream);
    }
}
