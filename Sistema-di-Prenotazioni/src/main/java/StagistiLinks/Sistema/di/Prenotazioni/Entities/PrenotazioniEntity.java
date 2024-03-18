package StagistiLinks.Sistema.di.Prenotazioni.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
public class PrenotazioniEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String tipoServizio;

    @Column
    @NotNull
    private LocalDate dataPrenotazione;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "stato_prenotazione_id")
    private StatoPrenotazioneEntity statoPrenotazione;

    public PrenotazioniEntity() {
    }

    public PrenotazioniEntity(Long id, @NotNull LocalDate dataPrenotazione, @NotNull String tipoServizio, @NotNull StatoPrenotazioneEntity statoPrenotazione, @NotNull ClienteEntity cliente) {
        this.id = id;
        this.dataPrenotazione = dataPrenotazione;
        this.tipoServizio = tipoServizio;
        this.statoPrenotazione = statoPrenotazione;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(@NotNull LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public @NotNull String getTipoServizio() {
        return tipoServizio;
    }

    public void setTipoServizio(String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }

    public @NotNull StatoPrenotazioneEntity getStatoPrenotazione() {
        return statoPrenotazione;
    }

    public void setStatoPrenotazione(@NotNull StatoPrenotazioneEntity statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }

    public @NotNull ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull ClienteEntity cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "PrenotazioniEntity{" +
                "id=" + id +
                ", dataPrenotazione=" + dataPrenotazione +
                ", tipoServizio='" + tipoServizio + '\'' +
                ", statoPrenotazione=" + statoPrenotazione +
                ", cliente=" + cliente +
                '}';
    }
}