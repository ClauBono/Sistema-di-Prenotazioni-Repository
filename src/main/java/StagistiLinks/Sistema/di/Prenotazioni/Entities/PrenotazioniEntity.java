package StagistiLinks.Sistema.di.Prenotazioni.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Entity
public class PrenotazioniEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String nomeCliente;

    @Column
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd") // Formattazione per l'output JSON
    private LocalDate dataPrenotazione;

    @Column
    @NotNull
    private String tipoServizio;

    public PrenotazioniEntity(Long id, @NotNull String nomeCliente, @NotNull LocalDate dataPrenotazione, @NotNull String tipoServizio) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.dataPrenotazione = dataPrenotazione;
        this.tipoServizio = tipoServizio;
    }

    public PrenotazioniEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(@NotNull String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    public void setTipoServizio(@NotNull String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }

    @Override
    public String toString() {
        return "PrenotazioniEntity{" +
                "id=" + id +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", dataPrenotazione=" + dataPrenotazione +
                ", tipoServizio='" + tipoServizio + '\'' +
                '}';
    }
}