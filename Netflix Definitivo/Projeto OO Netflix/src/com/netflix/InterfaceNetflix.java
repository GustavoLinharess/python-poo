package com.netflix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import main.CadastroLoginUsuario;
import model.Catalogo;
import model.Filme;
import model.Serie;

public class InterfaceNetflix extends JFrame {
    private Catalogo catalogo;
    private CadastroLoginUsuario cadastroLogin;
    private JTextField txtUsuario, txtEmail;
    private JPasswordField txtSenha;
    private JPanel panelPrincipal;

    public InterfaceNetflix() {
        catalogo = new Catalogo(); // Inicializa o catálogo
        cadastroLogin = new CadastroLoginUsuario(); // Inicializa a classe de cadastro e login
        setTitle("Netflix Interface");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        panelPrincipal = new JPanel(new CardLayout());
        add(panelPrincipal);

        mostrarTelaLogin();
    }

    private void mostrarTelaLogin() {
        JPanel panelLogin = new JPanel(new GridLayout(4, 2));

        txtUsuario = new JTextField();
        txtSenha = new JPasswordField();

        panelLogin.add(new JLabel("Usuário ou E-mail:"));
        panelLogin.add(txtUsuario);
        panelLogin.add(new JLabel("Senha:"));
        panelLogin.add(txtSenha);

        JButton btnLogin = new JButton("Fazer Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fazerLogin();
            }
        });
        panelLogin.add(btnLogin);

        JButton btnCadastrar = new JButton("Cadastrar Usuário");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaCadastro();
            }
        });
        panelLogin.add(btnCadastrar);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
                System.exit(0);
            }
        });
        panelLogin.add(btnSair);

        panelPrincipal.add(panelLogin, "login");
        mostrarPanel("login");
    }

    private void mostrarTelaCadastro() {
        JPanel panelCadastro = new JPanel(new GridLayout(5, 2));

        txtUsuario = new JTextField();
        txtSenha = new JPasswordField();
        txtEmail = new JTextField();

        panelCadastro.add(new JLabel("Nome de Usuário:"));
        panelCadastro.add(txtUsuario);
        panelCadastro.add(new JLabel("Senha:"));
        panelCadastro.add(txtSenha);
        panelCadastro.add(new JLabel("E-mail:"));
        panelCadastro.add(txtEmail);

        JButton btnCadastrarUsuario = new JButton("Cadastrar");
        btnCadastrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });
        panelCadastro.add(btnCadastrarUsuario);

        JButton btnVoltarCadastro = new JButton("Voltar");
        btnVoltarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaLogin();
            }
        });
        panelCadastro.add(btnVoltarCadastro);

        panelPrincipal.add(panelCadastro, "cadastro");
        mostrarPanel("cadastro");
    }

    private void mostrarTelaPrincipal() {
        JPanel panelPrincipalTela = new JPanel(new GridLayout(4, 1));

        JButton btnFilmes = new JButton("Ver Filmes");
        btnFilmes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaFilmes();
            }
        });
        panelPrincipalTela.add(btnFilmes);

        JButton btnSeries = new JButton("Ver Séries");
        btnSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaSeries();
            }
        });
        panelPrincipalTela.add(btnSeries);

        JButton btnUsuario = new JButton("Minha Conta");
        btnUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaUsuario();
            }
        });
        panelPrincipalTela.add(btnUsuario);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
                mostrarTelaLogin();
            }
        });
        panelPrincipalTela.add(btnLogout);

        panelPrincipal.add(panelPrincipalTela, "principal");
        mostrarPanel("principal");
    }

    private void mostrarTelaUsuario() {
        JPanel panelUsuario = new JPanel(new GridLayout(5, 1)); // Alterado para 5 linhas para incluir o botão de
                                                                // excluir conta

        JButton btnAlterarSenha = new JButton("Alterar Senha");
        btnAlterarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarSenha();
            }
        });
        panelUsuario.add(btnAlterarSenha);

        JButton btnAlterarEmail = new JButton("Alterar E-mail");
        btnAlterarEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarEmail();
            }
        });
        panelUsuario.add(btnAlterarEmail);

        // Adiciona o botão de excluir conta
        JButton btnExcluirConta = new JButton("Excluir Conta");
        btnExcluirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirConta();
            }
        });
        panelUsuario.add(btnExcluirConta);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaPrincipal();
            }
        });
        panelUsuario.add(btnVoltar);

        panelPrincipal.add(panelUsuario, "usuario");
        mostrarPanel("usuario");
    }

    private void mostrarListaFilmes() {
        JPanel panelFilmes = new JPanel();
        panelFilmes.setLayout(new BoxLayout(panelFilmes, BoxLayout.Y_AXIS));

        for (Filme filme : catalogo.getFilmes()) {
            JPanel panelFilme = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton btnFilme = new JButton(filme.getNome());
            btnFilme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(InterfaceNetflix.this, "Assistindo " + filme.getNome(), "Assistindo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            panelFilme.add(btnFilme);
            panelFilmes.add(panelFilme);
        }

        JScrollPane scrollPane = new JScrollPane(panelFilmes);
        JPanel panelPrincipalFilmes = new JPanel(new BorderLayout());
        panelPrincipalFilmes.add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaPrincipal();
            }
        });
        panelPrincipalFilmes.add(btnVoltar, BorderLayout.SOUTH);

        panelPrincipal.add(panelPrincipalFilmes, "filmes");
        mostrarPanel("filmes");
    }

    private void mostrarListaSeries() {
        JPanel panelSeries = new JPanel();
        panelSeries.setLayout(new BoxLayout(panelSeries, BoxLayout.Y_AXIS));

        for (Serie serie : catalogo.getSeries()) {
            JPanel panelSerie = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton btnSerie = new JButton(serie.getNome());
            btnSerie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(InterfaceNetflix.this, "Assistindo " + serie.getNome(), "Assistindo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            panelSerie.add(btnSerie);
            panelSeries.add(panelSerie);
        }

        JScrollPane scrollPane = new JScrollPane(panelSeries);
        JPanel panelPrincipalSeries = new JPanel(new BorderLayout());
        panelPrincipalSeries.add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaPrincipal();
            }
        });
        panelPrincipalSeries.add(btnVoltar, BorderLayout.SOUTH);

        panelPrincipal.add(panelPrincipalSeries, "series");
        mostrarPanel("series");
    }

    private void mostrarPanel(String nomePanel) {
        CardLayout cl = (CardLayout) panelPrincipal.getLayout();
        cl.show(panelPrincipal, nomePanel);
    }

    private void fazerLogin() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        if (cadastroLogin.fazerLogin(usuario, senha)) {
            mostrarTelaPrincipal();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastrarUsuario() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        String email = txtEmail.getText();
        if (cadastroLogin.cadastrarUsuario(usuario, senha, email)) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso.", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            mostrarTelaLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarSenha() {
        String senhaAtual = JOptionPane.showInputDialog(this, "Digite a senha atual:");
        String novaSenha = JOptionPane.showInputDialog(this, "Digite a nova senha:");

        if (senhaAtual != null && novaSenha != null) {
            if (cadastroLogin.alterarSenha(senhaAtual, novaSenha)) {
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar senha. Verifique a senha atual.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void alterarEmail() {
        String novoEmail = JOptionPane.showInputDialog(this, "Digite o novo e-mail:");
        if (cadastroLogin.alterarEmail(novoEmail)) {
            JOptionPane.showMessageDialog(this, "E-mail alterado com sucesso.", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar e-mail.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirConta() {
        String senhaExcluir = JOptionPane.showInputDialog(this, "Digite a senha para confirmar a exclusão da conta:");
        if (cadastroLogin.excluirConta(senhaExcluir)) {
            JOptionPane.showMessageDialog(this, "Conta excluída com sucesso.", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            mostrarTelaLogin(); // Redireciona para a tela de login após a exclusão
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir conta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarDados() {
        try (FileWriter writer = new FileWriter("dados.txt", true)) {
            writer.write("Dados salvos\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceNetflix().setVisible(true);
            }
        });
    }
}
