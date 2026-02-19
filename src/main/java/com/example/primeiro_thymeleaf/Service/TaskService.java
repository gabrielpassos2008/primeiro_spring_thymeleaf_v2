package com.example.primeiro_thymeleaf.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.primeiro_thymeleaf.model.Task;

@Service
public class TaskService {
    // Lista que armazena as tarefas em memória (simulando um banco de dados)
    List<Task> tasks = new ArrayList<>();

    public void saveTask(Task task) {
        // Se o ID não for nulo, significa que estamos EDITANDO
        if (task.getId() != null) {

            // Procura na lista a tarefa com o mesmo ID
            Task taskFind = tasks.stream()
                    .filter(taskItem -> task.getId().equals(taskItem.getId()))
                    .findFirst()
                    .get();

            // Substitui a tarefa antiga pela nova versão editada
            tasks.set(tasks.indexOf(taskFind), task);

        } else {

            // Se o ID for nulo, significa que estamos CRIANDO uma nova tarefa

            // Gera um ID automático baseado no tamanho da lista
            Long id = tasks.size() + 1L;

            // Cria uma nova Task e adiciona na lista
            tasks.add(new Task(id, task.getName(), task.getStatus(), task.getDate()));
        }
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public List<Task> returnList() {
        return tasks;
    }

    public Task EdirTask(Long id) {
        // Procura a tarefa na lista pelo ID recebido na URL
        Task taskFind = tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .get();
        return taskFind;
    }
}
