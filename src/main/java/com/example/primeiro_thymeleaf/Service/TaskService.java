package com.example.primeiro_thymeleaf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.primeiro_thymeleaf.model.Task;
import com.example.primeiro_thymeleaf.repository.TaskRepository;

@Service
public class TaskService {
    // Lista que armazena as tarefas em memória (simulando um banco de dados)
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void saveTask(Task task) {

        if (task.getId() != null) {
            // Editar
            repository.update(task);

        } else {
            // Criar
            Long id = repository.FindAll().size() + 1L;
            task.setId(id);
            repository.save(task);
        }
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public List<Task> returnList() {
        return repository.FindAll();
    }

    public Task EdirTask(Long id) {
        // Procura a tarefa na lista pelo ID recebido na URL
        return repository.findById(id);
    }
}
