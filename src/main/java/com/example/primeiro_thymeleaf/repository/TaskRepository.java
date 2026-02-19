package com.example.primeiro_thymeleaf.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.primeiro_thymeleaf.model.Task;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> FindAll() {
        return tasks;
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst()
                .get();
    }

    public void save(Task task) {
        tasks.add(task);
    }

    public void update(Task task) {
        Task existing = findById(task.getId());
        tasks.set(tasks.indexOf(existing), task);
    }

    public void deleteById(long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
