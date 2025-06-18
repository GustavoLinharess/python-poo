// Arquivo: Dados.java
package dados;

import java.io.*;
import java.util.*;

public class Dados {
    private static final String FILE_NAME = "usuarios.txt";
    private static Map<String, Usuario> usuarios = new HashMap<>();

    static {
        carregarUsuarios();
    }

    public static boolean validarCredenciais(String usuarioOuEmail, String senha) {
        Usuario usuario = buscarUsuarioPorNomeOuEmail(usuarioOuEmail);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public static void cadastrarUsuario(String usuario, String senha, String email) {
        Usuario novoUsuario = new Usuario(usuario, senha, email);
        usuarios.put(usuario, novoUsuario);
        salvarUsuarios();
    }

    public static void excluirUsuario(String usuario) {
        usuarios.remove(usuario);
        salvarUsuarios();
    }

    public static boolean existeUsuario(String usuario) {
        return usuarios.containsKey(usuario);
    }

    public static boolean existeEmail(String email) {
        return usuarios.values().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    public static Usuario buscarUsuarioPorNomeOuEmail(String usuarioOuEmail) {
        return usuarios.values().stream()
                .filter(u -> u.getUsuario().equals(usuarioOuEmail) || u.getEmail().equals(usuarioOuEmail))
                .findFirst()
                .orElse(null);
    }

    public static void atualizarUsuario(Usuario usuarioAtualizado) {
        usuarios.put(usuarioAtualizado.getUsuario(), usuarioAtualizado);
        salvarUsuarios();
    }

    private static void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    String usuario = dados[0];
                    String senha = dados[1];
                    String email = dados[2];
                    usuarios.put(usuario, new Usuario(usuario, senha, email));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private static void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.getUsuario() + "," + usuario.getSenha() + "," + usuario.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static List<String> getUsuarios() {
        return new ArrayList<>(usuarios.keySet());
    }
}
