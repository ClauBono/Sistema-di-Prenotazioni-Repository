package StagistiLinks.Sistema.di.Prenotazioni.Controller.ControllerCrudGenerico;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.ClienteDTO;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Services.AllConverterToDtoAndEntityService;
import StagistiLinks.Sistema.di.Prenotazioni.Services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Clienti")
public class ClienteController {

    private final ClienteService clienteService;
    private final AllConverterToDtoAndEntityService allConverterToDtoAndEntityService;

    public ClienteController(ClienteService clienteService, AllConverterToDtoAndEntityService allConverterToDtoAndEntityService) {
        this.clienteService = clienteService;
        this.allConverterToDtoAndEntityService = allConverterToDtoAndEntityService;
    }

    @GetMapping("/Visualizza tutti i Clienti")
    public List<ClienteDTO> ottieniTuttiClienti(){
        List<ClienteEntity> tuttiClienti = clienteService.ottieniTuttiClienti();
        List<ClienteDTO> tuttiClientiDTO;
        tuttiClientiDTO = tuttiClienti.stream()
                .map(allConverterToDtoAndEntityService::convertClienteToDTO)
                .collect(Collectors.toList());
        return tuttiClientiDTO;
    }



    @PostMapping("/Aggiungi un Cliente")
    public ResponseEntity<String> aggiungiCliente(@RequestBody ClienteEntity clienteEntity) {

        if (clienteService.aggiungiCliente(clienteEntity)){

            ClienteDTO clienteAggiunto = allConverterToDtoAndEntityService.convertClienteToDTO(clienteEntity);

            return ResponseEntity.ok("Cliente salvato con successo " + clienteAggiunto );
        }
        return ResponseEntity.badRequest().body("Il Cliente non puo' essere inserito perche' alcuni campi sono nulli o perche' completamente vuoti");
    }

    @DeleteMapping("/Elimina un Cliente/{id}")
    public ResponseEntity<String> eliminaCliente(@PathVariable Long id){

        if (clienteService.eliminaCliente(id)){

            return ResponseEntity.ok("Eliminazione riuscita del Cliente con ID: " + id);
        }
        return ResponseEntity.badRequest().body("Eliminazione fallita, il Cliente con ID " + id + " non esiste");
    }

    @PatchMapping("/Modifica un Cliente/{id}")
    public ResponseEntity<String> modificaCliente(@PathVariable Long id, @RequestBody ClienteEntity clienteEntity){

        if (clienteService.modificaCliente(id, clienteEntity)){

            ClienteDTO clienteModificato = allConverterToDtoAndEntityService.convertClienteToDTO(clienteEntity);

            return ResponseEntity.ok("Modifica del Cliente riuscita: " + clienteModificato);
        }
        return ResponseEntity.badRequest().body("Modifica fallita, il Cliente con l'id: " + id + " non esiste.");
    }


}
