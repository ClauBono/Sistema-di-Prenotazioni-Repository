package StagistiLinks.Sistema.di.Prenotazioni.Controller;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.PrenotazioniDTO;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Services.AllConverterToDtoAndEntityService;
import StagistiLinks.Sistema.di.Prenotazioni.Services.PrenotazioniService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;
    private final AllConverterToDtoAndEntityService allConverterToDtoAndEntityService;

    public PrenotazioniController(PrenotazioniService prenotazioniService, AllConverterToDtoAndEntityService allConverterToDtoAndEntityService) {
        this.prenotazioniService = prenotazioniService;
        this.allConverterToDtoAndEntityService = allConverterToDtoAndEntityService;
    }


    @GetMapping("/Visualizza tutte le Prenotazioni")
    public List<PrenotazioniDTO> ottieniTuttePrenotazioni(){
        List<PrenotazioniEntity> tuttePrenotazioni = prenotazioniService.ottieniTuttePrenotazioniEntity();
        List<PrenotazioniDTO> tuttePrenotazioniDTO;
        tuttePrenotazioniDTO = tuttePrenotazioni.stream()
                .map(allConverterToDtoAndEntityService::convertPrenotazioneToDTO)
                .collect(Collectors.toList());
        return tuttePrenotazioniDTO;
    }


    @PostMapping("/Aggiungi una Prenotazione")
    public ResponseEntity<String> aggiungiPrenotazione(@RequestBody PrenotazioniEntity prenotazioniEntity) {

        if (prenotazioniService.aggiungiPrenotazione(prenotazioniEntity)){

            PrenotazioniDTO prenotazioneAggiunta = allConverterToDtoAndEntityService.convertPrenotazioneToDTO(prenotazioniEntity);

            return ResponseEntity.ok("Prenotazione salvata con successo " + prenotazioneAggiunta );
        }
        return ResponseEntity.badRequest().body("La prenotazione non può essere inserita perché alcuni campi sono nulli o perché completamente vuota");
    }

    @DeleteMapping("/Elimina una Prenotazione/{id}")
    public ResponseEntity<String> eliminaPrenotazione(@PathVariable Long id){

        if (prenotazioniService.eliminaPrenotazione(id)){

            return ResponseEntity.ok("Eliminazione riuscita della prenotazione con ID: " + id);
        }
        return ResponseEntity.badRequest().body("Eliminazione fallita, la prenotazione con ID " + id + " non esiste");
    }

    @PatchMapping("/Modifica una Prenotazione/{id}")
    public ResponseEntity<String> modificaPrenotazione(@PathVariable Long id, @RequestBody PrenotazioniEntity prenotazioneEntity){

        if (prenotazioniService.modificaPrenotazione(id, prenotazioneEntity)){

            PrenotazioniDTO prenotazioneModificata = allConverterToDtoAndEntityService.convertPrenotazioneToDTO(prenotazioneEntity);

            return ResponseEntity.ok("Modifica della prenotazione riuscita: " + prenotazioneModificata);
        }
        return ResponseEntity.badRequest().body("Modifica fallita, la prenotazione con l'id: " + id + " non esiste.");
    }
}
