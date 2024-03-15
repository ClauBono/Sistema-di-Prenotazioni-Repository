package StagistiLinks.TaskList.Repository;

import StagistiLinks.TaskList.Entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskList, Long> {

}


