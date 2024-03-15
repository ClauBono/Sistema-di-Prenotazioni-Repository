package StagistiLinks.TaskList.Services;

import StagistiLinks.TaskList.Entities.TaskList;
import StagistiLinks.TaskList.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Create
    public TaskList createTaskList(TaskList taskList) {
        return taskRepository.save(taskList);
    }

    //Read
    public List<TaskList> getAllTaskList() {
        return taskRepository.findAll();
    }

    //ReadById (evita NullPointerException)
    public Optional<TaskList> getTaskListById(Long id) {
        return taskRepository.findById(id);
    }

    //Update
    public TaskList updateTaskList(Long id, TaskList updatedTaskList) {
        Optional<TaskList> existingTaskList = taskRepository.findById(id);
        if (existingTaskList.isPresent()) {
            TaskList taskList = existingTaskList.get();
            taskList.setNome(updatedTaskList.getNome());
            taskList.setDescrizione(updatedTaskList.getDescrizione());
            taskList.setDataScadenza(updatedTaskList.getDataScadenza());
            return taskRepository.save(taskList);
        } else {
            throw new RuntimeException("Non trovata task con id: " + id);
        }
    }

    //Delete
    public void deleteTaskList(Long id) {
        taskRepository.deleteById(id);
    }

}
