package StagistiLinks.Sistema.di.Prenotazioni.Controller;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Services.PrenotazioniService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @GetMapping("/Visualizza tutte le Prenotazioni")
    public List<PrenotazioniEntity> ottieniTuttePrenotazioni(){
       return prenotazioniService.ottieniTuttePrenotazioni();
    }


    @PostMapping("/Aggiungi una Prenotazione")
    public ResponseEntity<String> aggiungiPrenotazione(@RequestBody PrenotazioniEntity prenotazioniEntity) {

        if (prenotazioniService.aggiungiPrenotazione(prenotazioniEntity)){

            return ResponseEntity.ok("Prenotazione salvata con successo");
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
    public ResponseEntity<String> modificaPrenotazione(@PathVariable Long id, @RequestBody PrenotazioniEntity prenotazioneModificata){

        if (prenotazioniService.modificaPrenotazione(id, prenotazioneModificata)){

            return ResponseEntity.ok("Modifica della prenotazione con id: " + id + " riuscita.");
        }
        return ResponseEntity.badRequest().body("Modifica fallita, la prenotazione con l'id: " + id + " non esiste.");
    }
}
