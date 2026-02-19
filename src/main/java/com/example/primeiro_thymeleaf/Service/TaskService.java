package com.example.primeiro_thymeleaf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.primeiro_thymeleaf.model.Task;
import com.example.primeiro_thymeleaf.repository.TaskRepository;

// Indica que essa classe é um Service do Spring
// Ou seja, ela representa a camada de regra de negócio
@Service
public class TaskService {

    // Repositório responsável por acessar os dados
    // Aqui é feita a comunicação com a "camada de persistência"
    // (no seu caso, ainda pode estar em memória)
    private final TaskRepository repository;

    // Construtor com injeção de dependência
    // O Spring automaticamente injeta o TaskRepository aqui
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Método responsável por salvar uma tarefa
    // Pode ser tanto criação quanto edição
    public void saveTask(Task task) {

        // Se o ID não for nulo, significa que estamos editando
        if (task.getId() != null) {

            // Editar tarefa existente
            // Chama o método update do repository
            repository.update(task);

        } else {

            // Caso o ID seja nulo, estamos criando uma nova tarefa

            // Gera um novo ID baseado no tamanho da lista atual
            Long id = repository.FindAll().size() + 1L;

            // Define o ID na tarefa antes de salvar
            task.setId(id);

            // Salva a nova tarefa no repositório
            repository.save(task);
        }
    }

    // Método responsável por deletar uma tarefa pelo ID
    public void deleteTask(Long id) {

        // Chama o repository para remover pelo ID
        repository.deleteById(id);
    }

    // Método que retorna todas as tarefas
    public List<Task> returnList() {

        // Delegando a responsabilidade para o repository
        return repository.FindAll();
    }

    // Método que busca uma tarefa específica pelo ID
    public Task EdirTask(Long id) {

        // Procura a tarefa pelo ID recebido
        // Utilizado para preencher o formulário de edição
        return repository.findById(id);
    }
}
