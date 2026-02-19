package com.example.primeiro_thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.primeiro_thymeleaf.Service.TaskService;
import com.example.primeiro_thymeleaf.model.Task;

// Indica que essa classe é um controller
@Controller
public class TaskController {
    private final TaskService taskService = new TaskService();

    // Mapeia a rota principal "/"
    @GetMapping("/")
    public String home() {
        // Retorna a página home.html
        return "home";
    }

    // Rota GET para abrir a página de criação REFATORADO
    @GetMapping("/create")
    public ModelAndView getCreate() {
        // Cria um ModelAndView apontando para a página create.html
        ModelAndView mv = new ModelAndView("create");

        // Envia um objeto Task vazio para o formulário
        // Isso é necessário para o Thymeleaf conseguir fazer o binding
        mv.addObject("task", new Task());

        return mv;
    }

    // Rota POST para salvar a tarefa (criar ou editar) REFATORADO
    @PostMapping("/create")
    public String postCreate(Task task) {
        taskService.saveTask(task);
        // Redireciona para a página de listagem
        return "redirect:/list";
    }

    // Rota GET para listar todas as tarefas REFATORADO
    @GetMapping("/list")
    public ModelAndView getList() {

        // Cria ModelAndView apontando para list.html
        ModelAndView mv = new ModelAndView("list");

        // Envia a lista de tarefas para a view
        mv.addObject("tasks", taskService.returnList());

        return mv;
    }

    // Rota GET para editar uma tarefa específica REFATORADO
    @GetMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable("id") Long id) {
        // Reutiliza a página create.html para edição
        ModelAndView mv = new ModelAndView("create");
        // Envia a tarefa encontrada para o formulário (já preenchido)
        mv.addObject("task", taskService.EdirTask(id));
        return mv;
    }

    // REFATORADO
    @GetMapping("delete/{id}")
    public String getDeletar(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
