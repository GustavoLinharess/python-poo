package main; // Declara que este código pertence ao pacote "main".

import java.util.List; // Importa a interface List do pacote java.util.
import java.util.Scanner; // Importa a classe Scanner do pacote java.util para leitura de dados do usuário.
import dados.Dados; // Importa a classe Dados do pacote dados, que gerencia os dados dos usuários.
import dados.Usuario; // Importa a classe Usuario do pacote dados, que representa um usuário.
import model.Catalogo; // Importa a classe Catalogo do pacote model, que gerencia o catálogo de filmes e séries.
import model.Filme; // Importa a classe Filme do pacote model, que representa um filme.
import model.Serie; // Importa a classe Serie do pacote model, que representa uma série.

public class CadastroLoginUsuario { // Declara a classe CadastroLoginUsuario, que lida com cadastro, login e
                                    // gerenciamento de usuários.
    private boolean usuarioLogado; // Variável que indica se o usuário está logado ou não.
    private Usuario usuarioLogadoObj; // Objeto que representa o usuário atualmente logado.
    private Catalogo catalogo; // Objeto que representa o catálogo de filmes e séries.

    public CadastroLoginUsuario() { // Construtor da classe.
        this.usuarioLogado = false; // Inicializa a variável usuarioLogado como falsa.
        this.usuarioLogadoObj = null; // Inicializa o objeto usuarioLogadoObj como nulo.
        this.catalogo = new Catalogo(); // Inicializa o objeto catalogo como um novo Catalogo.
    }

    public boolean cadastrarUsuario(String usuario, String senha, String email) { // Método para cadastrar um novo
                                                                                  // usuário.
        if (Dados.existeUsuario(usuario) || Dados.existeEmail(email)) { // Verifica se o usuário ou e-mail já existem.
            System.out.println("Erro: Usuário ou e-mail já cadastrado."); // Mensagem de erro.
            return false; // Retorna falso, indicando que o cadastro falhou.
        } else {
            Dados.cadastrarUsuario(usuario, senha, email); // Cadastra o novo usuário.
            System.out.println("Usuário cadastrado com sucesso!"); // Mensagem de sucesso.
            return true; // Retorna verdadeiro, indicando que o cadastro foi bem-sucedido.
        }
    }

    public boolean fazerLogin(String usuarioOuEmail, String senha) { // Método para fazer login.
        Usuario usuario = Dados.buscarUsuarioPorNomeOuEmail(usuarioOuEmail); // Busca o usuário pelo nome ou e-mail.
        if (usuario != null && usuario.getSenha().equals(senha)) { // Verifica se o usuário existe e a senha está
                                                                   // correta.
            System.out.println("Login bem-sucedido!"); // Mensagem de sucesso.
            this.usuarioLogado = true; // Define usuarioLogado como verdadeiro.
            this.usuarioLogadoObj = usuario; // Define o usuário logado.
            return true; // Retorna verdadeiro, indicando que o login foi bem-sucedido.
        } else {
            System.out.println("Erro: Usuário ou senha incorretos. Tente novamente."); // Mensagem de erro.
            this.usuarioLogado = false; // Define usuarioLogado como falso.
            return false; // Retorna falso, indicando que o login falhou.
        }
    }

    public boolean excluirConta(String senha) { // Método para excluir a conta do usuário.
        if (usuarioLogado) { // Verifica se o usuário está logado.
            if (usuarioLogadoObj.getSenha().equals(senha)) { // Verifica se a senha fornecida está correta.
                Dados.excluirUsuario(usuarioLogadoObj.getUsuario()); // Exclui o usuário.
                this.usuarioLogado = false; // Define usuarioLogado como falso.
                this.usuarioLogadoObj = null; // Define o usuário logado como nulo.
                System.out.println("Conta excluída com sucesso!"); // Mensagem de sucesso.
                return true; // Retorna verdadeiro, indicando que a exclusão foi bem-sucedida.
            } else {
                System.out.println("Erro: Senha incorreta. Não foi possível excluir a conta."); // Mensagem de erro.
                return false; // Retorna falso, indicando que a exclusão falhou.
            }
        } else {
            System.out.println("Erro: Você precisa fazer login primeiro."); // Mensagem de erro.
            return false; // Retorna falso, indicando que o usuário precisa estar logado.
        }
    }

    public boolean alterarSenha(String senhaAtual, String novaSenha) { // Método para alterar a senha do usuário.
        if (usuarioLogado) { // Verifica se o usuário está logado.
            if (usuarioLogadoObj.getSenha().equals(senhaAtual)) { // Verifica se a senha atual está correta.
                usuarioLogadoObj.setSenha(novaSenha); // Define a nova senha.
                Dados.atualizarUsuario(usuarioLogadoObj); // Atualiza o usuário no banco de dados.
                System.out.println("Senha alterada com sucesso!"); // Mensagem de sucesso.
                return true; // Retorna verdadeiro, indicando que a senha foi alterada.
            } else {
                System.out.println("Erro: Senha atual incorreta. Não foi possível alterar a senha."); // Mensagem de
                                                                                                      // erro.
                return false; // Retorna falso, indicando que a alteração de senha falhou.
            }
        } else {
            System.out.println("Erro: Você precisa fazer login primeiro."); // Mensagem de erro.
            return false; // Retorna falso, indicando que o usuário precisa estar logado.
        }
    }

    public void alterarNome(String novoNome) { // Método para alterar o nome do usuário.
        if (usuarioLogado) { // Verifica se o usuário está logado.
            usuarioLogadoObj.setUsuario(novoNome); // Define o novo nome.
            Dados.atualizarUsuario(usuarioLogadoObj); // Atualiza o usuário no banco de dados.
            System.out.println("Nome alterado com sucesso!"); // Mensagem de sucesso.
        } else {
            System.out.println("Erro: Você precisa fazer login primeiro."); // Mensagem de erro.
        }
    }

    public boolean alterarEmail(String novoEmail) { // Método para alterar o e-mail do usuário.
        if (usuarioLogado) { // Verifica se o usuário está logado.
            if (Dados.existeEmail(novoEmail)) { // Verifica se o e-mail já está em uso.
                System.out.println("Erro: O e-mail já está em uso por outro usuário."); // Mensagem de erro.
                return false; // Retorna falso, indicando que a alteração do e-mail falhou.
            }
            usuarioLogadoObj.setEmail(novoEmail); // Define o novo e-mail.
            Dados.atualizarUsuario(usuarioLogadoObj); // Atualiza o usuário no banco de dados.
            System.out.println("E-mail alterado com sucesso!"); // Mensagem de sucesso.
            return true; // Retorna verdadeiro, indicando que o e-mail foi alterado.
        } else {
            System.out.println("Erro: Você precisa fazer login primeiro."); // Mensagem de erro.
            return false; // Retorna falso, indicando que o usuário precisa estar logado.
        }
    }

    public void exibirCatalogo() { // Método para exibir o catálogo de filmes e séries.
        System.out.println("Exibindo catálogo..."); // Mensagem indicando que o catálogo está sendo exibido.
        for (Filme filme : catalogo.getFilmes()) { // Itera sobre todos os filmes no catálogo.
            System.out.println(filme); // Exibe as informações do filme.
        }
        for (Serie serie : catalogo.getSeries()) { // Itera sobre todas as séries no catálogo.
            System.out.println(serie); // Exibe as informações da série.
        }
    }

    public boolean isUsuarioLogado() { // Método para verificar se o usuário está logado.
        return usuarioLogado; // Retorna o estado da variável usuarioLogado.
    }

    public List<String> getUsuarios() { // Método para obter a lista de usuários.
        return Dados.getUsuarios(); // Retorna a lista de usuários obtida da classe Dados.
    }

    public static void main(String[] args) { // Método principal que inicia a aplicação.
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para leitura de dados do usuário.
        CadastroLoginUsuario cadastroLogin = new CadastroLoginUsuario(); // Cria um objeto CadastroLoginUsuario.

        while (true) { // Loop infinito para manter a aplicação em execução.
            if (!cadastroLogin.isUsuarioLogado()) { // Verifica se o usuário não está logado.
                System.out.println("Escolha uma opção:"); // Exibe as opções disponíveis.
                System.out.println("1. Cadastrar Usuário");
                System.out.println("2. Fazer Login");
                System.out.println("3. Sair");

                int opcao = scanner.nextInt(); // Lê a opção escolhida pelo usuário.
                scanner.nextLine(); // Consome a nova linha após a leitura do inteiro.

                switch (opcao) { // Executa a ação baseada na opção escolhida.
                    case 1:
                        System.out.print("Digite o nome de usuário: ");
                        String novoUsuario = scanner.nextLine(); // Lê o nome de usuário.
                        System.out.print("Digite a senha: ");
                        String novaSenha = scanner.nextLine(); // Lê a senha.
                        System.out.print("Digite o e-mail: ");
                        String novoEmail = scanner.nextLine(); // Lê o e-mail.
                        cadastroLogin.cadastrarUsuario(novoUsuario, novaSenha, novoEmail); // Chama o método para
                                                                                           // cadastrar o usuário.
                        break;

                    case 2:
                        System.out.print("Digite o nome de usuário ou e-mail: ");
                        String usuarioLogin = scanner.nextLine(); // Lê o nome de usuário ou e-mail para login.
                        System.out.print("Digite a senha: ");
                        String senhaLogin = scanner.nextLine(); // Lê a senha para login.
                        cadastroLogin.fazerLogin(usuarioLogin, senhaLogin); // Chama o método para fazer login.
                        break;

                    case 3:
                        System.out.println("Saindo..."); // Mensagem indicando que o programa está saindo.
                        scanner.close(); // Fecha o objeto Scanner.
                        System.exit(0); // Encerra a aplicação.
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente."); // Mensagem de erro para opções
                                                                                // inválidas.
                        break;
                }
            } else { // Se o usuário estiver logado.
                System.out.println("Escolha uma opção:"); // Exibe as opções disponíveis.
                System.out.println("1. Alterar Senha");
                System.out.println("2. Alterar Nome");
                System.out.println("3. Alterar E-mail");
                System.out.println("4. Excluir Conta");
                System.out.println("5. Exibir Catálogo");
                System.out.println("6. Sair");

                int opcao = scanner.nextInt(); // Lê a opção escolhida pelo usuário.
                scanner.nextLine(); // Consome a nova linha após a leitura do inteiro.

                switch (opcao) { // Executa a ação baseada na opção escolhida.
                    case 1:
                        System.out.print("Digite a senha atual: ");
                        String senhaAtual = scanner.nextLine(); // Lê a senha atual.
                        System.out.print("Digite a nova senha: ");
                        String novaSenha = scanner.nextLine(); // Lê a nova senha.
                        cadastroLogin.alterarSenha(senhaAtual, novaSenha); // Chama o método para alterar a senha.
                        break;

                    case 2:
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine(); // Lê o novo nome.
                        cadastroLogin.alterarNome(novoNome); // Chama o método para alterar o nome.
                        break;

                    case 3:
                        System.out.print("Digite o novo e-mail: ");
                        String novoEmail = scanner.nextLine(); // Lê o novo e-mail.
                        cadastroLogin.alterarEmail(novoEmail); // Chama o método para alterar o e-mail.
                        break;

                    case 4:
                        System.out.print("Digite a senha para confirmar a exclusão da conta: ");
                        String senhaExcluir = scanner.nextLine(); // Lê a senha para confirmação da exclusão.
                        cadastroLogin.excluirConta(senhaExcluir); // Chama o método para excluir a conta.
                        break;

                    case 5:
                        cadastroLogin.exibirCatalogo(); // Chama o método para exibir o catálogo.
                        break;

                    case 6:
                        System.out.println("Saindo..."); // Mensagem indicando que o programa está saindo.
                        scanner.close(); // Fecha o objeto Scanner.
                        System.exit(0); // Encerra a aplicação.
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente."); // Mensagem de erro para opções
                                                                                // inválidas.
                        break;
                }
            }
        }
    }
}
