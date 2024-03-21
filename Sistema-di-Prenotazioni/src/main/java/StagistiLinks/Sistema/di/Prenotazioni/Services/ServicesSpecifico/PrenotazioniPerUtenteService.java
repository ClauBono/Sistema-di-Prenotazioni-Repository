package StagistiLinks.Sistema.di.Prenotazioni.Services.ServicesSpecifico;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.StatoPrenotazioneEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.ClienteRepository;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.PrenotazioniRepository;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.StatoPrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioniPerUtenteService {

    private final ClienteRepository clienteRepository;
    private final StatoPrenotazioneRepository statoPrenotazioneRepository;
    private final PrenotazioniRepository prenotazioniRepository;

    public PrenotazioniPerUtenteService(ClienteRepository clienteRepository, StatoPrenotazioneRepository statoPrenotazioneRepository, PrenotazioniRepository prenotazioniRepository) {
        this.clienteRepository = clienteRepository;
        this.statoPrenotazioneRepository = statoPrenotazioneRepository;
        this.prenotazioniRepository = prenotazioniRepository;
    }

    public boolean aggiungiPrenotazionePerUtente(PrenotazioniEntity prenotazione, Authentication authentication) {
        try {
            if (prenotazione != null) {
                // Ottieni il nome utente dell'utente loggato
                String username = authentication.getName();

                // Cerca il cliente corrispondente dal repository
                ClienteEntity cliente = clienteRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con questo username: " + username));

                // Imposta lo stato della prenotazione su "In Attesa"
                StatoPrenotazioneEntity statoInAttesa = new StatoPrenotazioneEntity();
                statoInAttesa.setStato("In Attesa");
                statoInAttesa.setDescrizione("La prenotazione è in attesa di conferma");
                prenotazione.setStatoPrenotazione(statoInAttesa);

                statoPrenotazioneRepository.save(statoInAttesa);

                // Collega la prenotazione al cliente
                prenotazione.setCliente(cliente);

                // Salvare la nuova prenotazione nel repository
                prenotazioniRepository.save(prenotazione);

                log.info("Prenotazione salvata con successo");
                return true;


            }else {

                log.error("La prenotazione non può essere inserita perché alcuni campi sono nulli o la data non è nel formato corretto");
                return false;

            }

        } catch (Exception e) {

            log.error("La prenotazione " + prenotazione + " non può essere inserita");
            return false;
        }
    }
}
