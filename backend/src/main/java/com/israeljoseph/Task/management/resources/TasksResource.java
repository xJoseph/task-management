package com.israeljoseph.Task.management.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israeljoseph.Task.management.models.Tasks;
import com.israeljoseph.Task.management.services.TasksService;

@RestController
@RequestMapping(value = "/api/tasks")
@CrossOrigin(origins = "*")
public class TasksResource {
    private TasksService tasksService;

    public TasksResource(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping
    List<Tasks> create(@RequestBody Tasks task) {
        task.setCreationDate(Instant.now());
        task.setUpdateDate(Instant.now());
        return this.tasksService.create(task);
    }

    @GetMapping
    List<Tasks> list() {
        return this.tasksService.list();
    }

    @GetMapping("{id}")
    Tasks listById(@PathVariable("id") Long id) {

        if (this.tasksService.listByID(id).isEmpty()) {
            return null;
        }
        return this.tasksService.listByID(id).get();
    }

    @PutMapping("{id}")
    List<Tasks> update(@PathVariable Long id, @RequestBody Tasks task) {
        if (this.tasksService.listByID(id).isEmpty()) {
            return null;
        }
        task.setUpdateDate(Instant.now());
        return this.tasksService.update(task);
    }

    @PutMapping("{id}/complete")
    List<Tasks> completeTask(@PathVariable Long id) {
        if (this.tasksService.listByID(id).isEmpty()) {
            return null;
        }
        Tasks task = this.tasksService.listByID(id).get();
        task.setStatus(!task.getStatus());
        task.setUpdateDate(Instant.now());
        return this.tasksService.update(task);
    }

    @DeleteMapping("{id}")
    List<Tasks> delete(@PathVariable("id") Long id) {
        if (this.tasksService.listByID(id).isEmpty()) {
            return null;
        }
        return this.tasksService.delete(id);
    }
}
