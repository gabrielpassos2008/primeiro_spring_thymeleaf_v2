package com.example.primeiro_thymeleaf.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.primeiro_thymeleaf.model.Task;

// Indica que essa classe é um Repository do Spring
// Responsável pela camada de acesso a dados
@Repository
public class TaskRepository {

    // Lista que armazena as tarefas em memória
    // Está simulando um banco de dados
    private List<Task> tasks = new ArrayList<>();

    // Retorna todas as tarefas armazenadas
    public List<Task> FindAll() {
        return tasks;
    }

    // Busca uma tarefa específica pelo ID
    public Task findById(Long id) {

        // Percorre a lista usando Stream
        // Filtra a tarefa cujo ID seja igual ao recebido
        // Pega o primeiro resultado encontrado
        // .get() retorna o objeto encontrado
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .get();
    }

    // Salva uma nova tarefa na lista
    public void save(Task task) {

        // Adiciona a tarefa no final da lista
        tasks.add(task);
    }

    // Atualiza uma tarefa existente
    public void update(Task task) {

        // Primeiro busca a tarefa já existente pelo ID
        Task existing = findById(task.getId());

        // Substitui a tarefa antiga pela nova versão
        tasks.set(tasks.indexOf(existing), task);
    }

    // Remove uma tarefa pelo ID
    public void deleteById(long id) {

        // Remove da lista a tarefa cujo ID seja igual ao recebido
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
