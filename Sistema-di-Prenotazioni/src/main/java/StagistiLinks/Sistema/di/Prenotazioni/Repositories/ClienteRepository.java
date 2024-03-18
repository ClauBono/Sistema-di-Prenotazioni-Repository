package StagistiLinks.Sistema.di.Prenotazioni.Repositories;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <ClienteEntity, Long> {
}
