package StagistiLinks.Sistema.di.Prenotazioni.Controller.ControllerSpecifico;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.ClienteDTO;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Services.AllConverterToDtoAndEntityService;
import StagistiLinks.Sistema.di.Prenotazioni.Services.ServicesSpecifico.ClientePerUtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/I miei Clienti")
public class ClientePerUtenteController {

    private final ClientePerUtenteService clientePerUtenteService;
    private final AllConverterToDtoAndEntityService allConverterToDtoAndEntityService;

    public ClientePerUtenteController(ClientePerUtenteService clientePerUtenteService, AllConverterToDtoAndEntityService allConverterToDtoAndEntityService) {
        this.clientePerUtenteService = clientePerUtenteService;
        this.allConverterToDtoAndEntityService = allConverterToDtoAndEntityService;
    }

    @PostMapping("/Registrazione utente")
    public ResponseEntity<String> registrazioneCliente(@RequestBody ClienteEntity cliente) {
        if (clientePerUtenteService.registraCliente(cliente)) {

            ClienteDTO clienteAggiunto = allConverterToDtoAndEntityService.convertClienteToDTO(cliente);

            return ResponseEntity.ok("Registrazione avvenuta con successo " + clienteAggiunto );

        } else {

            return ResponseEntity.badRequest().body("Errore durante la registrazione del cliente");
        }
    }

}
