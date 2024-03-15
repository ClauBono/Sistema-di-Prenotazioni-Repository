package StagistiLinks.TaskList.Controller;

import StagistiLinks.TaskList.Entities.TaskList;
import StagistiLinks.TaskList.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class WebController {

    private final TaskService taskService;

    public WebController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("titoloPagina", "Pagina Principale");
        model.addAttribute("descrizionePagina", "Ciao, da qui puoi vedere la tabella delle Task");
        return "Index";
    }

    @GetMapping("/visualizzaListaTask")
    public String visualizzaListaTask(Model model) {
        model.addAttribute("titoloPagina", "Lista Task");
        model.addAttribute("taskList", taskService.getAllTaskList());
        return "ListaTask";
    }

    @GetMapping("/aggiungiTaskList")
    public String aggiungiTask(Model model) {
        model.addAttribute("titoloPagina", "Inserisci una nuova Task");
        model.addAttribute("mode", "INSERIMENTO");
        model.addAttribute("titoloInserimento", "Riempi i campi della nuova configurazione");
        return "AggiungiModificaTask";
    }

    @GetMapping("/modificaTask/{id}")
    public String modificaTask(@PathVariable Long id, Model model) {
        TaskList taskList = taskService.getTaskListById(id)
                .orElseThrow(() -> new RuntimeException("Task non trovata con ID: " + id));

        model.addAttribute("taskList", taskList);
        model.addAttribute("titoloPagina", "Modifica Riga selezionata");
        model.addAttribute("mode", "MODIFICA");
        model.addAttribute("titoloModifica", "Riempi i campi per modificare la configurazione: " + id);
        return "AggiungiModificaTask";
    }
}

