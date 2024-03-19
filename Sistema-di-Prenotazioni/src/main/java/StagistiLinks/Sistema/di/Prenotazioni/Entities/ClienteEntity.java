package StagistiLinks.Sistema.di.Prenotazioni.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private String cognome;

    @Column
    @NotNull
    private String ruolo;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, @NotNull String username, @NotNull String password, @NotNull String nome, @NotNull String cognome, @NotNull String ruolo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(@NotNull String cognome) {
        this.cognome = cognome;
    }

    public @NotNull String getRuolo() {
        return ruolo;
    }

    public void setRuolo(@NotNull String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }
}
