package StagistiLinks.Sistema.di.Prenotazioni.Services;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.StatoPrenotazioneEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class PrenotazioniService {

    //qui le crud

    private final PrenotazioniRepository prenotazioniRepository;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository) {
        this.prenotazioniRepository = prenotazioniRepository;
    }

    public List<PrenotazioniEntity> getPrenotazioniByCliente(Long clienteId) {
        return prenotazioniRepository.findByClienteId(clienteId);
    }

    public List<PrenotazioniEntity> ottieniTuttePrenotazioniEntity() {
        return prenotazioniRepository.findAll();
    }

    public boolean aggiungiPrenotazione(PrenotazioniEntity prenotazione) {
        try {
            if (prenotazione != null && prenotazione.getDataPrenotazione() != null && prenotazione.getStatoPrenotazione() != null && prenotazione.getStatoPrenotazione() != null && prenotazione.getCliente() != null) {

                /*// Imposta lo stato della prenotazione su "In Attesa"
                StatoPrenotazioneEntity statoInAttesa = new StatoPrenotazioneEntity();
                statoInAttesa.setStato("In Attesa");
                statoInAttesa.setDescrizione("La prenotazione è in attesa di conferma");
                prenotazione.setStatoPrenotazione(statoInAttesa);*/

                prenotazioniRepository.save(prenotazione);

                log.info("Prenotazione salvata con successo");
                return true;


            } else {

                log.error("La prenotazione non può essere inserita perché alcuni campi sono nulli o la data non è nel formato corretto");
                return false;

            }

        } catch (Exception e) {

            log.error("La prenotazione " + prenotazione + " non può essere inserita");
            return false;
        }
    }


    public boolean eliminaPrenotazione(Long id){

        Optional<PrenotazioniEntity> prenotazioniDaEliminare = prenotazioniRepository.findById(id);

            try {
                if (prenotazioniDaEliminare.isPresent()){

                    prenotazioniRepository.deleteById(id);

                    log.info("Eliminazione riuscita della prenotazione con ID: {}", id);
                    return true;

                }else {

                    log.error("Eliminazione fallita, la prenotazione con ID {}, non esiste", id);
                    return false;

                }


            } catch (Exception e) {

                log.error("Processo eliminazione fallito, forse si è verificato un errore durante l'eliminazione");
                return false;
            }
    }


    public boolean modificaPrenotazione(Long id, PrenotazioniEntity prenotazioneModificata){

        Optional<PrenotazioniEntity> prenotazioneTemp = prenotazioniRepository.findById(id);

        try {
            if (prenotazioneTemp.isPresent()){

                PrenotazioniEntity prenotazioneDaModificare = prenotazioneTemp.get();
                prenotazioneDaModificare.setDataPrenotazione(prenotazioneModificata.getDataPrenotazione());
                prenotazioneDaModificare.setTipoServizio(prenotazioneModificata.getTipoServizio());
                prenotazioniRepository.save(prenotazioneDaModificare);

                log.info("Modifica della prenotazione con id: {} riuscita.", id);
                return true;

            }else {

                log.error("Modifica fallita, la prenotazione con l'id {} non esiste.", id);
                return false;
            }

        }catch (Exception e){

            log.error("Processo eliminazione fallito, forse si è verificato un errore durante la modifica.");
            return false;
        }
    }






}