package StagistiLinks.Sistema.di.Prenotazioni.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class StatoPrenotazioneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String stato;

    @Column
    @NotNull
    private String descrizione;

    public StatoPrenotazioneEntity() {
    }

    public StatoPrenotazioneEntity(Long id, @NotNull String stato, @NotNull String descrizione) {
        this.id = id;
        this.stato = stato;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getStato() {
        return stato;
    }

    public void setStato(@NotNull String stato) {
        this.stato = stato;
    }

    public @NotNull String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(@NotNull String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "StatoPrenotazioneEntity{" +
                "id=" + id +
                ", stato='" + stato + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
