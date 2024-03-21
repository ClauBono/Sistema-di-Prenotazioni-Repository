package StagistiLinks.Sistema.di.Prenotazioni.Controller.ControllerCrudGenerico;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.StatoPrenotazioneDTO;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.StatoPrenotazioneEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Services.AllConverterToDtoAndEntityService;
import StagistiLinks.Sistema.di.Prenotazioni.Services.StatoPrenotazioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Stato Prenotazioni")
public class StatoPrenotazioneController {

    private final StatoPrenotazioneService statoPrenotazioneService;
    private final AllConverterToDtoAndEntityService allConverterToDtoAndEntityService;

    public StatoPrenotazioneController(StatoPrenotazioneService statoPrenotazioneService, AllConverterToDtoAndEntityService allConverterToDtoAndEntityService) {
        this.statoPrenotazioneService = statoPrenotazioneService;
        this.allConverterToDtoAndEntityService = allConverterToDtoAndEntityService;
    }

    @GetMapping("/Visualizza tutti gli Stati Prenotazioni")
    public List<StatoPrenotazioneDTO> ottieniTuttiStatiPrenotazione(){
        List<StatoPrenotazioneEntity> tuttiGliStatiPrenotazione = statoPrenotazioneService.ottieniTuttiGliStatiPrenotazioni();
        List<StatoPrenotazioneDTO> tuttiGliStatiPrenotazioneDTO;
        tuttiGliStatiPrenotazioneDTO = tuttiGliStatiPrenotazione.stream()
                .map(allConverterToDtoAndEntityService::convertStatoPrenotazioneToDTO)
                .collect(Collectors.toList());
        return tuttiGliStatiPrenotazioneDTO;
    }


    @PostMapping("/Aggiungi uno Stato di Prenotazione")
    public ResponseEntity<String> aggiungiStatoPrenotazione(@RequestBody StatoPrenotazioneEntity statoPrenotazioneEntity) {

        if (statoPrenotazioneService.aggiungiStatoPrenotazione(statoPrenotazioneEntity)){

            StatoPrenotazioneDTO statoPrenotazioneAggiunto = allConverterToDtoAndEntityService.convertStatoPrenotazioneToDTO(statoPrenotazioneEntity);

            return ResponseEntity.ok("Stato Prenotazione salvato con successo " + statoPrenotazioneAggiunto );
        }
        return ResponseEntity.badRequest().body("Lo Stato di Prenotazione non puo' essere inserito perche' alcuni campi sono nulli o perche' completamente vuoti");
    }

    @DeleteMapping("/Elimina uno Stato di Prenotazione/{id}")
    public ResponseEntity<String> eliminaStatoPrenotazione(@PathVariable Long id){

        if (statoPrenotazioneService.eliminaStatoPrenotazione(id)){

            return ResponseEntity.ok("Eliminazione riuscita dello Stato di prenotazione con ID: " + id);
        }
        return ResponseEntity.badRequest().body("Eliminazione fallita, lo Stato di Prenotazione con ID " + id + " non esiste");
    }

    @PatchMapping("/Modifica uno Stato di Prenotazione/{id}")
    public ResponseEntity<String> modificaCliente(@PathVariable Long id, @RequestBody StatoPrenotazioneEntity statoPrenotazioneEntity){

        if (statoPrenotazioneService.modificaStatoPrenotazione(id, statoPrenotazioneEntity)){

            StatoPrenotazioneDTO statoPrenotazioneModificato = allConverterToDtoAndEntityService.convertStatoPrenotazioneToDTO(statoPrenotazioneEntity);

            return ResponseEntity.ok("Modifica dello Stato di Prenotazione riuscita: " + statoPrenotazioneModificato);
        }
        return ResponseEntity.badRequest().body("Modifica fallita, lo Stato di Prenotazione con l'id: " + id + " non esiste.");
    }

}
