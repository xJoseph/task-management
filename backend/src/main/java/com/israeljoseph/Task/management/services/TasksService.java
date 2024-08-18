package com.israeljoseph.Task.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.israeljoseph.Task.management.models.Tasks;
import com.israeljoseph.Task.management.repositories.TasksRepository;

@Service
public class TasksService {
    private TasksRepository tasksRepository = null;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> create(Tasks task) {
        tasksRepository.save(task);

        return this.list();
    }

    public List<Tasks> list() {
        Sort sort = Sort.by("id").descending();

        return this.tasksRepository.findAll(sort);
    }

    public Optional<Tasks> listByID(Long id) {
        return this.tasksRepository.findById(id);
    }

    public List<Tasks> update(Tasks task) {
        this.tasksRepository.save(task);

        return this.list();
    }

    public List<Tasks> delete(Long id) {
        this.tasksRepository.deleteById(id);
        
        return this.list();
    }
}
