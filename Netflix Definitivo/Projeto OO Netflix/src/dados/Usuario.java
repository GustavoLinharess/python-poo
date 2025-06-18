// Arquivo: Usuario.java
package dados;

public class Usuario {
    private String usuario;
    private String senha;
    private String email;

    public Usuario(String usuario, String senha, String email) {
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
