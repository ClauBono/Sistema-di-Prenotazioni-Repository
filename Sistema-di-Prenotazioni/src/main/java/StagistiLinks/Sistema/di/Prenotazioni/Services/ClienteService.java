package StagistiLinks.Sistema.di.Prenotazioni.Services;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteEntity> ottieniTuttiClienti() {
        return clienteRepository.findAll();
    }

    public boolean aggiungiCliente(ClienteEntity cliente) {
        try {

            if (cliente != null && cliente.getNome() != null && cliente.getCognome() != null && cliente.getRuolo() != null) {

                clienteRepository.save(cliente);

                log.info("Dati Cliente salvati con successo");
                return true;



            } else {

                log.error("Il Cliente non può essere inserito perché alcuni campi sono nulli o non nel formato corretto");
                return false;

            }

        } catch (Exception e) {

            log.error("Il Cliente " + cliente + " non può essere inserito");
            return false;
        }
    }


    public boolean eliminaCliente(Long id){

        Optional<ClienteEntity> clienteDaEliminare = clienteRepository.findById(id);

        try {
            if (clienteDaEliminare.isPresent()){

                clienteRepository.deleteById(id);

                log.info("Eliminazione riuscita del Cliente con ID: {}", id);
                return true;

            }else {

                log.error("Eliminazione fallita, il Cliente con ID {}, non esiste", id);
                return false;

            }


        } catch (Exception e) {

            log.error("Processo eliminazione fallito, forse si è verificato un errore durante l'eliminazione");
            return false;
        }
    }


    public boolean modificaCliente(Long id, ClienteEntity clienteModificato){

        Optional<ClienteEntity> clienteTemp = clienteRepository.findById(id);

        try {
            if (clienteTemp.isPresent()){

                ClienteEntity clienteDaModificare = clienteTemp.get();

                clienteDaModificare.setNome(clienteModificato.getNome());
                clienteDaModificare.setCognome(clienteModificato.getCognome());
                clienteDaModificare.setRuolo(clienteModificato.getRuolo());


                clienteRepository.save(clienteDaModificare);

                log.info("Modifica del Cliente con id: {} riuscita.", id);
                return true;

            }else {

                log.error("Modifica fallita, il Cliente con l'id {} non esiste.", id);
                return false;
            }

        }catch (Exception e){

            log.error("Processo eliminazione fallito, forse si è verificato un errore durante la modifica.");
            return false;
        }
    }
}
