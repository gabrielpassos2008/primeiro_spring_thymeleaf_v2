package com.example.primeiro_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.primeiro_thymeleaf.Service.TaskService;
import com.example.primeiro_thymeleaf.model.Task;

// Indica que essa classe é um controller do Spring MVC
@Controller
public class TaskController {

    // Declaração do service que será usado para acessar a lógica de negócio
    private final TaskService taskService;

    // Construtor com injeção de dependência
    // O Spring automaticamente injeta o TaskService aqui
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Mapeia a rota principal "/"
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("totalTasks", taskService.getTotalTasks());
        mv.addObject("completedTasks", taskService.getCompletedTasks());
        mv.addObject("pendingTasks", taskService.getpendingTasks());
        // Retorna a página home.html
        return mv;
    }

    // Rota GET para abrir a página de criação
    @GetMapping("/create")
    public String getCreate() {
        return "create";
    }

    // Rota POST responsável por salvar (criar ou editar) uma tarefa
    @PostMapping("/create")
    public String postCreate(Task task) {

        // Chama o service para salvar a tarefa
        // A lógica de salvar (criar ou editar) está na camada de Service
        taskService.saveTask(task);

        // Redireciona para a página de listagem após salvar
        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView getList(@RequestParam(required = false) String search) {

        ModelAndView mv = new ModelAndView("list");
        // Verifica se o parâmetro foi enviado (não é null)
        // e se contém texto válido (não está vazio nem só com espaços)
        if (search != null && !search.isBlank()) {
            // Se existe um valor de busca válido, chama o service para retornar tarefas
            // filtradas
            mv.addObject("tasks", taskService.searchTasks(search));
        } else {
            // Se não existe busca, retorna todas as tarefas
            mv.addObject("tasks", taskService.returnList());
        }
        return mv;
    }

    @GetMapping("create/edit/{id}")
    public ModelAndView GetEditId(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("task", taskService.editTask(id));
        return mv;
    }

    // Rota GET para deletar uma tarefa
    @GetMapping("delete/{id}")
    public String getDeletar(@PathVariable("id") Long id) {

        // Chama o service para excluir a tarefa pelo ID
        taskService.deleteTask(id);

        // Redireciona para a home após deletar
        return "redirect:/";
    }

}
