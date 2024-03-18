package StagistiLinks.Sistema.di.Prenotazioni.Services;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.ClienteDTO;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.PrenotazioniDTO;
import StagistiLinks.Sistema.di.Prenotazioni.DTO.StatoPrenotazioneDTO;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.StatoPrenotazioneEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AllConverterToDtoAndEntityService {


    private final ClienteDTO clienteDTO;
    private final PrenotazioniDTO prenotazioniDTO;
    private final StatoPrenotazioneDTO statoPrenotazioneDTO;

    public AllConverterToDtoAndEntityService(ClienteDTO clienteDTO, PrenotazioniDTO prenotazioniDTO, StatoPrenotazioneDTO statoPrenotazioneDTO) {
        this.clienteDTO = clienteDTO;
        this.prenotazioniDTO = prenotazioniDTO;
        this.statoPrenotazioneDTO = statoPrenotazioneDTO;
    }





    // Converter delle Prenotazioni
    public PrenotazioniDTO convertPrenotazioneToDTO(PrenotazioniEntity prenotazione) {

        PrenotazioniDTO dto = new PrenotazioniDTO();

        dto.setId(prenotazione.getId());
        dto.setDataPrenotazione(prenotazione.getDataPrenotazione());
        dto.setTipoServizio(prenotazione.getTipoServizio());
        dto.setCliente(convertClienteToDTO(prenotazione.getCliente()));
        dto.setStatoPrenotazione(convertStatoPrenotazioneToDTO(prenotazione.getStatoPrenotazione()));

        return dto;
    }

    public  PrenotazioniEntity convertPrenotazioneToEntity(PrenotazioniDTO prenotazioniDTO) {

        PrenotazioniEntity prenotazioniEntity = new PrenotazioniEntity();

        prenotazioniEntity.setId(prenotazioniDTO.getId());
        prenotazioniEntity.setDataPrenotazione(prenotazioniDTO.getDataPrenotazione());
        prenotazioniEntity.setTipoServizio(prenotazioniDTO.getTipoServizio());

        return prenotazioniEntity;
    }






    // Converter del Cliente
    public ClienteDTO convertClienteToDTO(ClienteEntity clienteEntity) {

        ClienteDTO dto = new ClienteDTO();

        dto.setId(clienteEntity.getId());
        dto.setNome(clienteEntity.getNome());
        dto.setCognome(clienteEntity.getCognome());
        dto.setRuolo(clienteEntity.getRuolo());

        return dto;
    }

    public ClienteEntity convertClienteToEntity(ClienteDTO clienteDTO) {

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId(clienteDTO.getId());
        clienteEntity.setNome(clienteDTO.getNome());
        clienteEntity.setCognome(clienteDTO.getCognome());
        clienteEntity.setRuolo(clienteDTO.getRuolo());

        return clienteEntity;
    }









    // Converter dello Stato Prenotazione
    public StatoPrenotazioneDTO convertStatoPrenotazioneToDTO(StatoPrenotazioneEntity statoPrenotazioneEntity) {

        StatoPrenotazioneDTO dto = new StatoPrenotazioneDTO();

        dto.setId(statoPrenotazioneEntity.getId());
        dto.setStato(statoPrenotazioneEntity.getStato());
        dto.setDescrizione(statoPrenotazioneEntity.getDescrizione());

        return dto;
    }

    public StatoPrenotazioneEntity convertStatoPrenotazioneToEntity(StatoPrenotazioneDTO statoPrenotazioneDTO) {

        StatoPrenotazioneEntity statoPrenotazioneEntity = new StatoPrenotazioneEntity();

        statoPrenotazioneEntity.setId(statoPrenotazioneDTO.getId());
        statoPrenotazioneEntity.setStato(statoPrenotazioneDTO.getStato());
        statoPrenotazioneEntity.setDescrizione(statoPrenotazioneDTO.getDescrizione());

        return statoPrenotazioneEntity;
    }
}
