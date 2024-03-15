package StagistiLinks.Sistema.di.Prenotazioni.Repositories;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.PrenotazioniEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepository extends JpaRepository <PrenotazioniEntity, Long> {
}

