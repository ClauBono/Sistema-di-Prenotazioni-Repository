package StagistiLinks.Sistema.di.Prenotazioni.Services;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.StatoPrenotazioneEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.StatoPrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StatoPrenotazioneService {

    private final StatoPrenotazioneRepository statoPrenotazioneRepository;

    public StatoPrenotazioneService(StatoPrenotazioneRepository statoPrenotazioneRepository) {
        this.statoPrenotazioneRepository = statoPrenotazioneRepository;
    }


    public List<StatoPrenotazioneEntity> ottieniTuttiGliStatiPrenotazioni() {
        return statoPrenotazioneRepository.findAll();
    }

    public boolean aggiungiStatoPrenotazione(StatoPrenotazioneEntity statoPrenotazione) {
        try {

            if (statoPrenotazione != null && statoPrenotazione.getStato() != null && statoPrenotazione.getDescrizione() != null) {

                statoPrenotazioneRepository.save(statoPrenotazione);

                log.info("Stato Prenotazione salvato con successo");
                return true;



            } else {

                log.error("Lo Stato Della Prenotazione non può essere inserito perché alcuni campi sono nulli o non nel formato corretto");
                return false;

            }

        } catch (Exception e) {

            log.error("Lo Stato Prenotazione " + statoPrenotazione + " non può essere inserito");
            return false;
        }
    }


    public boolean eliminaStatoPrenotazione(Long id){

        Optional<StatoPrenotazioneEntity> statoPrenotazioneDaEliminare = statoPrenotazioneRepository.findById(id);

        try {
            if (statoPrenotazioneDaEliminare.isPresent()){

                statoPrenotazioneRepository.deleteById(id);

                log.info("Eliminazione riuscita dello Stato Prenotazione con ID: {}", id);
                return true;

            }else {

                log.error("Eliminazione fallita, lo Stato Prenotazione con ID {}, non esiste", id);
                return false;

            }


        } catch (Exception e) {

            log.error("Processo eliminazione fallito, forse si è verificato un errore durante l'eliminazione");
            return false;
        }
    }


    public boolean modificaStatoPrenotazione(Long id, StatoPrenotazioneEntity statoPrenotazioneModificato){

        Optional<StatoPrenotazioneEntity> statoPrenotazioneTemp = statoPrenotazioneRepository.findById(id);

        try {
            if (statoPrenotazioneTemp.isPresent()){

                StatoPrenotazioneEntity statoPrenotazioneDaModificare = statoPrenotazioneTemp.get();

                statoPrenotazioneDaModificare.setStato(statoPrenotazioneModificato.getStato());
                statoPrenotazioneDaModificare.setDescrizione(statoPrenotazioneModificato.getDescrizione());

                statoPrenotazioneRepository.save(statoPrenotazioneDaModificare);

                log.info("Modifica dello Stato Prenotazione con id: {} riuscita.", id);
                return true;

            }else {

                log.error("Modifica fallita, lo Stato Prenotazione con l'id {} non esiste.", id);
                return false;
            }

        }catch (Exception e){

            log.error("Processo eliminazione fallito, forse si è verificato un errore durante la modifica.");
            return false;
        }
    }
}
