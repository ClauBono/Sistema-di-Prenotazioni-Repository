package StagistiLinks.Sistema.di.Prenotazioni.Services.ServicesSpecifico;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClientePerUtenteService {

    private final ClienteRepository clienteRepository;

    public ClientePerUtenteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public boolean controllaUsername(String username) {
        Optional<ClienteEntity> usernameEsistente = clienteRepository.findByUsername(username);
        return usernameEsistente.isPresent();
    }

    public boolean registraCliente(ClienteEntity cliente) {
        try {

            if (cliente != null && cliente.getUsername() != null && cliente.getPassword() != null && cliente.getNome() != null && cliente.getCognome() != null && cliente.getRuolo() != null) {


                // Controlla se lo username esiste già nel database
                if (controllaUsername(cliente.getUsername())) {
                    log.error("Username non disponibile");
                    return false;
                }

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


}
