package StagistiLinks.Sistema.di.Prenotazioni.DTO;
import org.springframework.stereotype.Component;

@Component
public class StatoPrenotazioneDTO {


    private Long id;

    private String stato;

    private String descrizione;


    public StatoPrenotazioneDTO() {
    }

    public StatoPrenotazioneDTO(Long id, String stato, String descrizione) {
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

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "StatoPrenotazioneDTO{" +
                "id=" + id +
                ", stato='" + stato + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
