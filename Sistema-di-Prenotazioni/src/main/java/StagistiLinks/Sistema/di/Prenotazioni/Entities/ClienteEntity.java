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
    private String nome;

    @Column
    @NotNull
    private String cognome;

    @Column
    @NotNull
    private String ruolo;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, @NotNull String nome, @NotNull String cognome, @NotNull String ruolo) {
        this.id = id;
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

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getCognome() {
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
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }
}
