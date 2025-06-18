package model;

import java.util.ArrayList;
import java.util.List;

// A classe Catalogo representa uma coleção de filmes e séries.
public class Catalogo {
    // Listas para armazenar objetos do tipo Filme e Serie.
    private List<Filme> filmes;
    private List<Serie> series;

    // Construtor da classe, que inicializa as listas e preenche o catálogo com
    // alguns títulos pré-definidos.
    public Catalogo() {
        filmes = new ArrayList<>();
        series = new ArrayList<>();
        inicializarCatalogo(); // Chama o método para adicionar filmes e séries às listas.
    }

    // Método privado responsável por preencher o catálogo com uma lista de filmes e
    // séries.
    private void inicializarCatalogo() {
        // Adicionando filmes de terror à lista de filmes.
        filmes.add(new Filme("O Chamado", "Terror"));
        filmes.add(new Filme("It: A Coisa", "Terror"));
        filmes.add(new Filme("O Exorcista", "Terror"));
        filmes.add(new Filme("Atividade Paranormal", "Terror"));
        filmes.add(new Filme("Psicose", "Terror"));

        // Adicionando filmes de comédia à lista de filmes.
        filmes.add(new Filme("Se Beber, Não Case", "Comédia"));
        filmes.add(new Filme("Superbad", "Comédia"));
        filmes.add(new Filme("A Mentira", "Comédia"));
        filmes.add(new Filme("As Branquelas", "Comédia"));
        filmes.add(new Filme("Escola de Rock", "Comédia"));

        // Adicionando filmes de romance à lista de filmes.
        filmes.add(new Filme("Orgulho e Preconceito", "Romance"));
        filmes.add(new Filme("Titanic", "Romance"));
        filmes.add(new Filme("A Culpa é das Estrelas", "Romance"));
        filmes.add(new Filme("Diário de uma Paixão", "Romance"));
        filmes.add(new Filme("500 Dias com Ela", "Romance"));

        // Adicionando filmes de ação à lista de filmes.
        filmes.add(new Filme("Duro de Matar", "Ação"));
        filmes.add(new Filme("Matrix", "Ação"));
        filmes.add(new Filme("Mad Max: Estrada da Fúria", "Ação"));
        filmes.add(new Filme("Velozes e Furiosos", "Ação"));
        filmes.add(new Filme("Homem de Ferro", "Ação"));

        // Adicionando séries de terror à lista de séries.
        series.add(new Serie("Stranger Things", "Terror"));
        series.add(new Serie("American Horror Story", "Terror"));
        series.add(new Serie("The Haunting of Hill House", "Terror"));
        series.add(new Serie("Bates Motel", "Terror"));
        series.add(new Serie("The Walking Dead", "Terror"));

        // Adicionando séries de comédia à lista de séries.
        series.add(new Serie("Friends", "Comédia"));
        series.add(new Serie("The Office", "Comédia"));
        series.add(new Serie("Brooklyn Nine-Nine", "Comédia"));
        series.add(new Serie("How I Met Your Mother", "Comédia"));
        series.add(new Serie("Parks and Recreation", "Comédia"));

        // Adicionando séries de romance à lista de séries.
        series.add(new Serie("Outlander", "Romance"));
        series.add(new Serie("Grey's Anatomy", "Romance"));
        series.add(new Serie("The Crown", "Romance"));
        series.add(new Serie("Modern Love", "Romance"));
        series.add(new Serie("To All the Boys I've Loved Before", "Romance"));

        // Adicionando séries de ação à lista de séries.
        series.add(new Serie("Breaking Bad", "Ação"));
        series.add(new Serie("Game of Thrones", "Ação"));
        series.add(new Serie("The Witcher", "Ação"));
        series.add(new Serie("Marvel's Daredevil", "Ação"));
        series.add(new Serie("Arrow", "Ação"));
    }

    // Método público que retorna a lista de filmes.
    public List<Filme> getFilmes() {
        return filmes;
    }

    // Método público que retorna a lista de séries.
    public List<Serie> getSeries() {
        return series;
    }

    // Método para buscar um filme pelo nome.
    // Ele percorre a lista de filmes e retorna o filme cujo nome corresponde ao
    // nome informado, ignorando maiúsculas/minúsculas.
    public Filme buscarFilmePorNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        return null; // Retorna null se o filme não for encontrado.
    }

    // Método para buscar uma série pelo nome.
    // Similar ao método buscarFilmePorNome, ele percorre a lista de séries e
    // retorna a série cujo nome corresponde ao nome informado.
    public Serie buscarSeriePorNome(String nome) {
        for (Serie serie : series) {
            if (serie.getNome().equalsIgnoreCase(nome)) {
                return serie;
            }
        }
        return null; // Retorna null se a série não for encontrada.
    }
}
