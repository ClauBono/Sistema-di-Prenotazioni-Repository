package StagistiLinks.Sistema.di.Prenotazioni.Controller.ControllerSpecifico;

import StagistiLinks.Sistema.di.Prenotazioni.DTO.PrenotazioniDTO;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.ClienteRepository;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.PrenotazioniRepository;
import StagistiLinks.Sistema.di.Prenotazioni.Services.AllConverterToDtoAndEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Le mie Prenotazioni")
public class PrenotazioniPerUtente {

    private final PrenotazioniRepository prenotazioniRepository;
    private final AllConverterToDtoAndEntityService allConverterToDtoAndEntityService;
    private final ClienteRepository clienteRepository;

    public PrenotazioniPerUtente(PrenotazioniRepository prenotazioniRepository, AllConverterToDtoAndEntityService allConverterToDtoAndEntityService, ClienteRepository clienteRepository) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.allConverterToDtoAndEntityService = allConverterToDtoAndEntityService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/ottieni le prenotazioni per utente")
    public List<PrenotazioniDTO> ottieniMiePrenotazioni(Authentication authentication) {
        // Ottieni il nome utente dell'utente loggato
        String username = authentication.getName();

        // Cerca il cliente corrispondente dal repository
        ClienteEntity cliente = clienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con questo username: " + username));

        // Recupera le prenotazioni associate al cliente corrente
        List<PrenotazioniEntity> prenotazioni = prenotazioniRepository.findByClienteId(cliente.getId());

        // Converti le entità delle prenotazioni in DTO
        return prenotazioni.stream()
                .map(allConverterToDtoAndEntityService::convertPrenotazioneToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/aggiungi prenotazione per utente")
    public ResponseEntity<String> aggiungiPrenotazione(@RequestBody PrenotazioniDTO prenotazioneDTO, Authentication authentication) {
        // Ottieni il nome utente dell'utente loggato
        String username = authentication.getName();

        // Cerca il cliente corrispondente dal repository
        ClienteEntity cliente = clienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con questo username: " + username));

        // Creare un'entità PrenotazioneEntity da prenotazioneDTO
        PrenotazioniEntity nuovaPrenotazione = new PrenotazioniEntity();
        // Assicurati di impostare altri campi dell'entità come necessario
        nuovaPrenotazione.setCliente(cliente);
        // Assicurati di impostare altri campi dell'entità come necessario

        // Salvare la nuova prenotazione nel repository
        prenotazioniRepository.save(nuovaPrenotazione);

        return new ResponseEntity<>("Prenotazione aggiunta con successo", HttpStatus.CREATED);
    }
}
