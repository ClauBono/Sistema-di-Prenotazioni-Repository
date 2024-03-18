package StagistiLinks.Sistema.di.Prenotazioni.Repositories;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.StatoPrenotazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoPrenotazioneRepository extends JpaRepository <StatoPrenotazioneEntity, Long> {
}
