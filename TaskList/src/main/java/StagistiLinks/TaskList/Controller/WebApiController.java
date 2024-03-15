package StagistiLinks.TaskList.Controller;

import StagistiLinks.TaskList.Entities.TaskList;
import StagistiLinks.TaskList.Repository.TaskRepository;
import StagistiLinks.TaskList.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class WebApiController {

    private final TaskService taskService;

    public WebApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskList")
    public List<TaskList> getAllTaskList() {
        return taskService.getAllTaskList();
    }

    @DeleteMapping("/eliminaRiga/{id}")
    public ResponseEntity<String> eliminaRiga(@PathVariable Long id) {
        try {
            taskService.deleteTaskList(id);
            return ResponseEntity.ok("Riga con ID " + id + " eliminata con successo!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante l'eliminazione della riga.");
        }
    }

    @PatchMapping("/modificaRiga/{id}")
    public ResponseEntity<String> modificaRiga(@PathVariable Long id, @RequestBody TaskList rigaModificata) {
        try {
            TaskList updatedTaskList = taskService.updateTaskList(id, rigaModificata);
            return ResponseEntity.ok("Task modificata con successo! ID: " + updatedTaskList.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante il salvataggio della task: " + e.getMessage());
        }
    }

    @PostMapping("/inserisciRiga")
    public ResponseEntity<String> inserisciRiga(@RequestBody TaskList rigaDaAggiungere) {
        try {
            TaskList createdTaskList = taskService.createTaskList(rigaDaAggiungere);
            return ResponseEntity.ok("Inserimento avvenuto con successo. ID: " + createdTaskList.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante il salvataggio: " + e.getMessage());
        }
    }
}

